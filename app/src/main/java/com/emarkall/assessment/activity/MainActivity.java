package com.emarkall.assessment.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emarkall.assessment.R;
import com.emarkall.assessment.adapter.UserAdapter;
import com.emarkall.assessment.domain.User;
import com.emarkall.assessment.domain.dto.UserDto;
import com.emarkall.assessment.viewModel.MainViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private MainViewModel mainViewModel;
    private FloatingActionButton fltAdd;

    private  int TOTAL_PAGES= 0;
    private int current= 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    private void initialization() {
        mainViewModel= ViewModelProviders.of(this).get(MainViewModel.class);

        recyclerView= findViewById(R.id.rcl_user_list);
        adapter= new UserAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        mainViewModel.getUserList(1).observeForever(this::update);

        //Let's paginating

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(current<TOTAL_PAGES){
                    current++;
                    mainViewModel.getUserList(current);
                }

            }
        });


        //Add button
        fltAdd= findViewById(R.id.fltAdd);
        fltAdd.setOnClickListener(view -> {
            View v= LayoutInflater.from(MainActivity.this).inflate(R.layout.add_user_layout, null);

            EditText edxFirstName= v.findViewById(R.id.edxFirstName);
            EditText edxLastName= v.findViewById(R.id.edxLastName);
            EditText edxEmail= v.findViewById(R.id.edxEmail);

            AlertDialog diallog= new AlertDialog.Builder(this)
                    .setCancelable(false)
                    .setView(view)
                    .setPositiveButton(R.string.str_save, (dialogInterface, i) -> {
                        User user= new User(null, edxEmail.getText().toString(),
                                edxFirstName.getText().toString(), edxLastName.getText().toString(), null);
                        mainViewModel.addUser(user);
                        mainViewModel.getUserList(1);
                    })
                    .setNegativeButton(R.string.str_cancel, (dialogInterface, i) -> {

                    })
                    .create();
        });


    }

    private void update(UserDto userDto) {
        TOTAL_PAGES= userDto.getTotalPages();
        adapter.addAll(userDto.getUsers());
        adapter.notifyDataSetChanged();
    }
}
