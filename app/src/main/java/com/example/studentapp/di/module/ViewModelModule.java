package com.example.studentapp.di.module;

import androidx.lifecycle.ViewModel;

import com.example.studentapp.di.util.ViewModelKey;
import com.example.studentapp.view.ui.gallery.GalleryViewModel;
import com.example.studentapp.view.ui.home.HomeViewModel;
import com.example.studentapp.view.ui.slideshow.SlideshowViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindsHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel.class)
    abstract ViewModel bindsGalleryViewModel(GalleryViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SlideshowViewModel.class)
    abstract ViewModel bindsSlideshowViewModel(SlideshowViewModel homeViewModel);

}
