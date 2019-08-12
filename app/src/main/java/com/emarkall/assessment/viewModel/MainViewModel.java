package com.emarkall.assessment.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.emarkall.assessment.app.MyApp;
import com.emarkall.assessment.domain.User;
import com.emarkall.assessment.domain.dto.UserDto;
import com.emarkall.assessment.repository.api.UserApi;

import java.util.List;

import retrofit2.Call;

public class MainViewModel extends ViewModel {
    private static UserApi userApi;

    static {
        userApi= MyApp.getInstance().getClientNetworking().create(UserApi.class);
    }

    public LiveData<List<User>> getUserList(int page){
        Call<UserDto> userDtoCall= userApi.getList(page);
    }
}
