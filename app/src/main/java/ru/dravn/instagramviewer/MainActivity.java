package ru.dravn.instagramviewer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends BaseActivity implements ImageFragment.OnFragmentInteractionListener {


    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setVisibility(View.GONE);
                Fragment fragment = ImageFragment.newInstance("test", "test2");
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, fragment).commit();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, ChangeThemeActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.login:
                Intent intent2 = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent2);
                finish();
                return true;
            case R.id.av_action_img_OK:
                Snackbar
                        .make(mToolbar, "OK", Snackbar.LENGTH_LONG)
                        .show();
                return true;
            case R.id.av_action_img_Close:
                Snackbar
                        .make(mToolbar, "Close", Snackbar.LENGTH_LONG)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
