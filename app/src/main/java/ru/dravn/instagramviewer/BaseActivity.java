package ru.dravn.instagramviewer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    protected static final String SETTING = "setting";
    protected static final String THEME = "theme";
    protected SharedPreferences mSettings;
    protected int theme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if ((!App.isAUTH()) && (!this.getClass().equals(LoginActivity.class))) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();

        }

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        mSettings = getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        theme = mSettings.getInt(THEME, R.style.RedAppTheme);
        setTheme(theme);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
