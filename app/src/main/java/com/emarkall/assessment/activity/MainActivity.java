package com.emarkall.assessment.activity;

import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emarkall.assessment.R;
import com.emarkall.assessment.adapter.UserAdapter;
import com.emarkall.assessment.domain.User;
import com.emarkall.assessment.domain.dto.UserDto;
import com.emarkall.assessment.viewModel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private MainViewModel mainViewModel;

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
                if(current>TOTAL_PAGES){
                    current++;
                }
                mainViewModel.getUserList(current);
            }
        });

    }

    private void update(UserDto userDto) {
        TOTAL_PAGES= userDto.getTotalPages();
        adapter.addAll(userDto.getUsers());
        adapter.notifyDataSetChanged();
    }
}
