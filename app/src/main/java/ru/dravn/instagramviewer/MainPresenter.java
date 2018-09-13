package ru.dravn.instagramviewer;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.HashMap;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    MainModel model;

    public MainPresenter() {
        this.model = new MainModel();
    }

    public void startFragment(String fragmentName, HashMap<String, String> map) {
        getViewState().startFragment(model.getFragment(fragmentName,map));
    }
}
