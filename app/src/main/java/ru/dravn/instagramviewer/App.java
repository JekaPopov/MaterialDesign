package ru.dravn.instagramviewer;

import android.app.Application;

public class App extends Application {

    public static boolean AUTH = false;

    public static boolean isAUTH() {
        return AUTH;
    }

    public static void setAUTH(boolean auth) {
        App.AUTH = auth;
    }

}
