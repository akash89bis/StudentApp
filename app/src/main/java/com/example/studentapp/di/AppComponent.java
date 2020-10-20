package com.example.studentapp.di;

import android.app.Application;

import com.example.studentapp.base.BaseApplication;
import com.example.studentapp.di.module.ActivityBuildersModule;
import com.example.studentapp.di.module.FragmentBuildersModule;
import com.example.studentapp.di.module.ViewModelFactoryModule;
import com.example.studentapp.di.module.ViewModelModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;


@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBuildersModule.class,
        FragmentBuildersModule.class,
        ViewModelFactoryModule.class,
        ViewModelModule.class
})
public interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{
        AppComponent build();

        @BindsInstance
        Builder application(Application application);

    }
}
