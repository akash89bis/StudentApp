package com.example.studentapp.network;

import com.example.studentapp.model.JsonResponse;
import com.example.studentapp.model.StudentDetailsRequest;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("student/newEntry")
    Single<JsonResponse> addStudentData(@Body StudentDetailsRequest request);
}
