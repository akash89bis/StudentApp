package com.example.studentapp.network;

import com.example.studentapp.model.JsonResponse;
import com.example.studentapp.model.StudentDetailsRequest;

import javax.inject.Inject;

import io.reactivex.Observable;

public class ApiRepository {

    private ApiInterface api;

    @Inject
    ApiRepository(ApiInterface repoService) {
        this.api = repoService;
    }

    public Observable<JsonResponse> addStudentData(StudentDetailsRequest request){
        return api.addStudentData(request);
    }
}
