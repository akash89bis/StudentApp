package com.example.studentapp.di.module;

import com.example.studentapp.view.ui.gallery.GalleryFragment;
import com.example.studentapp.view.ui.home.HomeFragment;
import com.example.studentapp.view.ui.slideshow.SlideshowFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract GalleryFragment contributesGalleryFragment();

    @ContributesAndroidInjector
    abstract HomeFragment contributesHomeFragment();

    @ContributesAndroidInjector
    abstract SlideshowFragment contributesSlideShowFragment();
}
