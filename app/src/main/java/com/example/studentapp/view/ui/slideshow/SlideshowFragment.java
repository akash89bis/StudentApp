package com.example.studentapp.view.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentapp.R;
import com.example.studentapp.factory.ViewModelFactory;
import com.example.studentapp.model.StudentDetailsResponse;
import com.example.studentapp.view.ui.slideshow.rv.StudentListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class SlideshowFragment extends DaggerFragment {

    private SlideshowViewModel slideshowViewModel;

    private StudentListAdapter mAdapter ;
    private List<StudentDetailsResponse> studentList = new ArrayList<>();

    @Inject
    ViewModelFactory providerFactory;

    private RecyclerView recyclerView;
    private TextView txtError;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = new ViewModelProvider(this,providerFactory).get(SlideshowViewModel.class);

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        recyclerView = root.findViewById(R.id.rv_student_list);
        txtError = root.findViewById(R.id.txt_err);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        slideshowViewModel.getStudentList();
        initRecyclerView();
        observerViewModel();
    }

    private void observerViewModel() {
        slideshowViewModel.studentListLiveData().observe(getViewLifecycleOwner(), new Observer<List<StudentDetailsResponse>>() {
            @Override
            public void onChanged(List<StudentDetailsResponse> studentDetailsResponses) {
                if (studentDetailsResponses != null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    mAdapter.updateStudent(studentDetailsResponses);
                }
            }
        });

        slideshowViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    txtError.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new StudentListAdapter(studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }
    
}
