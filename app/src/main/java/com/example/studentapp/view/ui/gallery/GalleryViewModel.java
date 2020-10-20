package com.example.studentapp.view.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    @Inject
    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Deprecated gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}