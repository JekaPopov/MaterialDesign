package ru.dravn.instagramviewer;

import android.content.SharedPreferences;

class ThemeModel {

    private SharedPreferences mSettings;
    private String THEME = "theme";
    private String defaultTheme = "RedAppTheme";

    public ThemeModel(SharedPreferences settings) {

        this.mSettings = settings;
    }

    public String getTheme() {

        return mSettings.getString(THEME, defaultTheme);
    }

    public void setTheme(String theme)
    {
        mSettings.edit().putString(THEME, theme).apply();
    }
}
