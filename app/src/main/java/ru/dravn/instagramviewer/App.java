package ru.dravn.instagramviewer;

import android.app.Application;

public class App extends Application {

    public  boolean AUTH = false;

    public  boolean isAUTH() {
        return AUTH;
    }

    public void setAUTH(boolean auth) {
        this.AUTH = auth;
    }

}
