package ru.dravn.instagramviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChangeThemeActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_theme);

        checkSelectButton();


        findViewById(R.id.buttonGreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme(R.style.GreenAppTheme);
            }
        });
        findViewById(R.id.buttonRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme(R.style.RedAppTheme);
            }
        });
        findViewById(R.id.buttonBlue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme(R.style.BlueAppTheme);
            }
        });
    }

    private void changeTheme(int themeId) {
        mSettings.edit().putInt(THEME, themeId).apply();
        Intent intent = getIntent();
        startActivity(intent);
        finish();

    }

    private void checkSelectButton()
    {
        switch (theme) {
            case R.style.GreenAppTheme: {
                findViewById(R.id.buttonGreen).setVisibility(View.GONE);
                break;
            }
            case R.style.BlueAppTheme: {
                findViewById(R.id.buttonBlue).setVisibility(View.GONE);
                break;
            }
            case R.style.RedAppTheme: {
                findViewById(R.id.buttonRed).setVisibility(View.GONE);
                break;
            }
        }
    }


}
