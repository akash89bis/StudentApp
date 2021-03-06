package com.example.studentapp.view.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.studentapp.R;
import com.example.studentapp.factory.ViewModelFactory;
import com.example.studentapp.view.util.NameFilter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private ProgressBar progressBar;
    private TextInputLayout txt_input_email;
    private final int DELAY = 2000;
    private static final String TAG = "HomeFragment";
    private String fNameValidation;
    private String lNameValidation;
    private String emailValidation;
    private String phnNoValidation;

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
        edt_first_name.setInputType(InputType.TYPE_CLASS_TEXT);
        edt_first_name.setFilters( new InputFilter[]{ new NameFilter()});
        edt_first_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edt_first_name.setError(homeViewModel.validateEditTextForName(edt_first_name.getText()));
                fNameValidation = homeViewModel.validateEditTextForName(edt_first_name.getText());
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        txt_input_email = root.findViewById(R.id.txt_input_email);

        edt_email = root.findViewById(R.id.edt_email);
        edt_email.setInputType(InputType.TYPE_CLASS_TEXT);
        edt_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                emailValidation = homeViewModel.validateEditTextForEmail(edt_email.getText());
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    txt_input_email.setError(homeViewModel.validateEditTextForEmail(edt_email.getText()));
                    emailValidation = homeViewModel.validateEditTextForEmail(edt_email.getText());
                    checkRequiredFields();
                }
            }
        });
        edt_last_name = root.findViewById(R.id.edt_last_name);
        edt_last_name.setInputType(InputType.TYPE_CLASS_TEXT);
        edt_last_name.setFilters( new InputFilter[]{ new NameFilter()}) ;
        edt_last_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edt_last_name.setError(homeViewModel.validateEditTextForName(edt_last_name.getText()));
                lNameValidation = homeViewModel.validateEditTextForName(edt_last_name.getText());
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edt_phone = root.findViewById(R.id.edt_phone);
        edt_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                edt_phone.setError(homeViewModel.validateEditTextForPhone(edt_phone.getText().toString()));
                phnNoValidation = homeViewModel.validateEditTextForPhone(edt_phone.getText().toString());
                checkRequiredFields();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edt_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    edt_phone.setError(homeViewModel.validateEditTextForPhone(((EditText) view).getText().toString()));
                    phnNoValidation = homeViewModel.validateEditTextForPhone(((EditText) view).getText().toString());
                }
            }
        });
        edt_phone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i== EditorInfo.IME_ACTION_DONE){
                    edt_phone.setError(homeViewModel.validateEditTextForPhone(((EditText) textView).getText().toString()));
                    phnNoValidation = homeViewModel.validateEditTextForPhone(((EditText) textView).getText().toString());
                    checkRequiredFields();
                }
                return false;
            }
        });

        btnSubmit = root.findViewById(R.id.button);
        progressBar = root.findViewById(R.id.progressBar);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnSubmit.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);
                Log.e(TAG, "button clicked --- " );
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        homeViewModel.makePostApiCall(edt_first_name.getText().toString(),
                                edt_last_name.getText().toString(), edt_email.getText().toString(),
                                edt_phone.getText().toString());
                    }
                }, DELAY);

            }
        });

        homeViewModel.getSuccessLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                progressBar.setVisibility(View.GONE);
                btnSubmit.setEnabled(true);
                Snackbar.make(getView(), "Data inserted successfully !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        homeViewModel.getErrorLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                progressBar.setVisibility(View.GONE);
                btnSubmit.setEnabled(true);
                Snackbar.make(getView(), "Error occurred, Try Again later !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void checkRequiredFields() {
        if(!TextUtils.isEmpty(edt_first_name.getText().toString()) && !TextUtils.isEmpty(edt_last_name.getText().toString()) &&
                !TextUtils.isEmpty(edt_email.getText().toString()) && !TextUtils.isEmpty(edt_phone.getText().toString())){
            if (fNameValidation != null || lNameValidation != null || emailValidation != null || phnNoValidation != null) {
                btnSubmit.setEnabled(false);
            } else {
                btnSubmit.setEnabled(true);
            }
        }else{
            btnSubmit.setEnabled(false);
        }
    }
}


