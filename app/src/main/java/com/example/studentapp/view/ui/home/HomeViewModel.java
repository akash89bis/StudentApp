package com.example.studentapp.view.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.studentapp.model.JsonResponse;
import com.example.studentapp.model.StudentDetailsRequest;
import com.example.studentapp.network.ApiRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<String>> cityList;
    private MutableLiveData<List<String>> stateList;

    private static final String TAG = "HomeViewModel";

    private ApiRepository apiRepository;
    private CompositeDisposable disposable;
    private StudentDetailsRequest request;

    @Inject
    public HomeViewModel(ApiRepository apiRepository) {
        mText = new MutableLiveData<>();
        mText.setValue("Dagger home fragment");

        cityList = new MutableLiveData<>();
        stateList = new MutableLiveData<>();

        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();
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

    public void makePostApiCall(String fName, String lName, String email, String phone){
        request = new StudentDetailsRequest();
        request.setFirstName(fName);
        request.setLastName(lName);
        request.setEmail(email);
        request.setPhoneNum(Integer.parseInt(phone));
        request.setSex("Male");

        disposable.add(
                apiRepository.addStudentData(request)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<JsonResponse>() {

                            @Override
                            public void onNext(JsonResponse jsonResponse) {
                                Log.e(TAG, "onNext: "+ jsonResponse );
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(TAG, "onError: "+ e );
                            }

                            @Override
                            public void onComplete() {
                                Log.e(TAG, "onComplete: " );
                            }
                        })
        );
    }



}