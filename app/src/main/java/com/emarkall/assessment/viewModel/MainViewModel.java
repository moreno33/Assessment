package com.emarkall.assessment.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emarkall.assessment.app.MyApp;
import com.emarkall.assessment.domain.User;
import com.emarkall.assessment.domain.dto.UserDto;
import com.emarkall.assessment.repository.api.UserApi;

import org.modelmapper.ModelMapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private static UserApi userApi;
    private static ModelMapper mapper;
    private MutableLiveData<List<User>> userLiveData= new MutableLiveData<>();

    static {
        userApi= MyApp.getInstance().getClientNetworking().create(UserApi.class);
        mapper= new ModelMapper();
    }

    public LiveData<List<User>> getUserList(int page){
        Call<UserDto> userDtoCall= userApi.getList(page);
        userDtoCall.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                List<User> users= mapper.map(response.body(), List.class);
                userLiveData.postValue(users);
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {

            }
        });
        return userLiveData;
    }
}
