package com.emarkall.assessment.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emarkall.assessment.R;
import com.emarkall.assessment.adapter.UserAdapter;
import com.emarkall.assessment.domain.User;
import com.emarkall.assessment.viewModel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private MainViewModel mainViewModel;
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

        mainViewModel.getUserList(1).observeForever(this::update);


    }

    private void update(List<User> users) {
        adapter.setUsers(users);
        adapter.notifyDataSetChanged();
    }
}
