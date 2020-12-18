package com.example.studentapp.network;

import com.example.studentapp.model.JsonResponse;
import com.example.studentapp.model.StudentDetailsRequest;
import com.example.studentapp.model.StudentDetailsResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ApiRepository {

    private ApiInterface api;

    @Inject
    ApiRepository(ApiInterface repoService) {
        this.api = repoService;
    }

    public Single<JsonResponse> addStudentData(StudentDetailsRequest request){
        return api.addStudentData(request);
    }

    public Single<List<StudentDetailsResponse>> getStudentList(){
        return api.getStudentList();
    }
}
