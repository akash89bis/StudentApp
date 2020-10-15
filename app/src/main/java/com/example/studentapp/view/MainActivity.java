package com.example.studentapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.studentapp.R;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
