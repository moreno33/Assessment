package com.emarkall.assessment.repository.api;

import com.emarkall.assessment.domain.User;
import com.emarkall.assessment.domain.dto.UserDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserApi {

    @GET("/api/users")
    public Call<UserDto> getList(@Query("page") int page);

    @POST("/api/users")
    public Call<Void> addUser(@Body User user);
}
