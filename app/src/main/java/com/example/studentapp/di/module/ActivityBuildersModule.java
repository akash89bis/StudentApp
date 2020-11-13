package com.example.studentapp.di.module;

import com.example.studentapp.view.HomeActivity;
import com.example.studentapp.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity contributesMainActivity();

    @ContributesAndroidInjector(modules = {FragmentBuildersModule.class})
    abstract HomeActivity contributesHomeActivity();
}
