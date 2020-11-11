package com.example.studentapp.view.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentapp.R;
import com.example.studentapp.factory.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


public class HomeFragment extends DaggerFragment {

    private HomeViewModel homeViewModel;

    @Inject
    ViewModelFactory providerFactory;

    private Spinner spinnerCity;
    private Spinner spinnerState;

    private EditText edt_first_name;
    private EditText edt_email;
    private EditText edt_last_name;
    private EditText edt_phone;
    private Button btnSubmit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this, providerFactory).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        initViews(root);
        return root;
    }

    private void initViews(View root) {
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // City Spinner item
        spinnerCity = root.findViewById(R.id.spinner);
        homeViewModel.getCityList().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> city) {
                spinnerCity.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, city));
            }
        });

        // State Spinner item
        spinnerState = root.findViewById(R.id.spinner_state);
        homeViewModel.getStateList().observe(getViewLifecycleOwner(), new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> state) {
                spinnerState.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, state));
            }
        });

        edt_first_name = root.findViewById(R.id.edt_first_name);
        edt_email = root.findViewById(R.id.edt_email);
        edt_last_name = root.findViewById(R.id.edt_last_name);
        edt_phone = root.findViewById(R.id.edt_phone);
        btnSubmit = root.findViewById(R.id.button);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeViewModel.makePostApiCall(edt_first_name.getText().toString(), edt_last_name.getText().toString(), edt_email.getText().toString(), edt_phone.getText().toString());
            }
        });
    }
}


