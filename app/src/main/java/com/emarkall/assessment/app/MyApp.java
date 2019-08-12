package com.emarkall.assessment.app;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApp extends Application {

    private static MyApp INSTANCE;
    private Retrofit retrofit;
    private final static String BASE_URL= 

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE= this;
    }

    public static MyApp getInstance() {
        return INSTANCE;
    }

    /**
     * this method allows us to do networking
     * @return
     */
    public Retrofit getClientNetworking(){
        if(retrofit!= null) return retrofit;

        Gson gson= new GsonBuilder().create();

        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}
