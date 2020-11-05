package com.example.studentapp.view.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<String>> cityList;
    private MutableLiveData<List<String>> stateList;

    @Inject
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Dagger home fragment");

        cityList = new MutableLiveData<>();
        stateList = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<String>> getCityList(){
        List<String> cityL = new ArrayList<>();
        cityL.add("Kolkata");
        cityL.add("Bhubaneshwar");
        cityL.add("Chennai");
        cityL.add("Bangalore");
        cityList.setValue(cityL);
        return cityList;
    }

    public LiveData<List<String>> getStateList(){
        List<String> stateL = new ArrayList<>();
        stateL.add("West Bengal");
        stateL.add("Odissa");
        stateL.add("Tamil Nadu");
        stateL.add("Karnataka");
        stateList.setValue(stateL);
        return stateList;
    }



}