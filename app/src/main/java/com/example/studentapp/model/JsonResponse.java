package com.example.studentapp.model;

import com.google.gson.annotations.SerializedName;

public class JsonResponse {

    @SerializedName("msg")
    private String msg;

    @SerializedName("status")
    private String status;

    public String getMsg() {
        return msg;
    }

    public String getStatus() {
        return status;
    }
}
