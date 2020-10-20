package com.example.studentapp.view.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    @Inject
    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Dagger home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}