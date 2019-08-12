package com.emarkall.assessment.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.emarkall.assessment.app.MyApp;
import com.emarkall.assessment.domain.User;
import com.emarkall.assessment.domain.dto.UserDto;
import com.emarkall.assessment.repository.api.UserApi;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private UserApi userApi;
    private ModelMapper mapper;
    private MutableLiveData<UserDto> userLiveData= new MutableLiveData<>();

    public MainViewModel(){
        userApi= MyApp.getInstance().getClientNetworking().create(UserApi.class);
        mapper= new ModelMapper();
    }

    public LiveData<UserDto> getUserList(int page){
        Call<UserDto> userDtoCall= userApi.getList(page);
        userDtoCall.enqueue(new Callback<UserDto>() {
            @Override
            public void onResponse(Call<UserDto> call, Response<UserDto> response) {
                Log.i("USERS", response.body().getUsers().toString());
                userLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<UserDto> call, Throwable t) {

            }
        });
        return userLiveData;
    }
}
