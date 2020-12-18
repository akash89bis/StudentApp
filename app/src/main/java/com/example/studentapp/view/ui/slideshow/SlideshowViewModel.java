package com.example.studentapp.view.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.studentapp.model.StudentDetailsResponse;
import com.example.studentapp.network.ApiRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<StudentDetailsResponse>> studentList;
    private MutableLiveData<Boolean> errorLiveData;

    private ApiRepository apiRepository;
    private CompositeDisposable disposable;

    @Inject
    public SlideshowViewModel(ApiRepository apiRepository) {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");

        studentList = new MutableLiveData<>();
        errorLiveData = new MutableLiveData<>();
        this.apiRepository = apiRepository;
        disposable = new CompositeDisposable();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<StudentDetailsResponse>> studentListLiveData(){
        return studentList;
    }

    public LiveData<Boolean> getErrorLiveData() {
        return errorLiveData;
    }

    public void getStudentList(){
        disposable.add(apiRepository.getStudentList()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<List<StudentDetailsResponse>>() {
            @Override
            public void onSuccess(List<StudentDetailsResponse> studentDetailsResponses) {
                studentList.setValue(studentDetailsResponses);
            }

            @Override
            public void onError(Throwable e) {
                errorLiveData.setValue(true);
            }
        }));
    }
}