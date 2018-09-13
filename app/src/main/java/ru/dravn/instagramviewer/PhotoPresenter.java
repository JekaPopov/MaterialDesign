package ru.dravn.instagramviewer;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class PhotoPresenter extends MvpPresenter<PhotoView> {
    PhotoModel model;

    public PhotoPresenter() {
        this.model = new PhotoModel();
    }
}
