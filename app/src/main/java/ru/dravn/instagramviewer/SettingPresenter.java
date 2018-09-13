package ru.dravn.instagramviewer;

import android.content.SharedPreferences;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


@InjectViewState
public class SettingPresenter extends MvpPresenter<SettingView> {

    public static final String GREEN = "GreenAppTheme";
    public static final String BLUE = "BlueAppTheme";
    public static final String RED = "RedAppTheme";

    ThemeModel model;

    public SettingPresenter(SharedPreferences mSettings) {
        this.model = new ThemeModel(mSettings);
    }

    public void selectTheme(String theme) {

        model.setTheme(theme);
        getViewState().changeTheme();
    }

    public void hideButton() {

        getViewState().hideButton(model.getTheme());
    }

    public String getTheme() {
        return model.getTheme();
    }
}


