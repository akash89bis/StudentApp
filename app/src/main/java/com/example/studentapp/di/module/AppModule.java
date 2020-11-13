package com.example.studentapp.di.module;

import com.example.studentapp.model.StudentDetailsRequest;
import com.example.studentapp.network.ApiInterface;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {


    private final static String BASE_URL = "http://192.168.0.108:2020/";

    @Provides
    static Retrofit provideRetrofit() {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static ApiInterface provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    static StudentDetailsRequest providesStudentDetailsRequest(){
        return new StudentDetailsRequest();
    }
}
