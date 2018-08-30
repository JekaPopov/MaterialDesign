package ru.dravn.instagramviewer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected static final String SETTING = "setting";
    protected static final String THEME = "theme";
    private int theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        changeTheme();

        setContentView(R.layout.main_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ((NavigationView) findViewById(R.id.nav_view))
                .setNavigationItemSelectedListener(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(getSupportFragmentManager().getBackStackEntryCount()>1)

            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_settings) {
            getFragment("SettingFragment", new HashMap<String, String>());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        HashMap<String, String> map = new HashMap<>();

        if (item.getItemId() == R.id.nav_camera) {

            getFragment("PhotoFragment", map);

        } else if (item.getItemId() == R.id.nav_gallery) {
            getFragment("ImageFragment", map);

        } else if (item.getItemId() == R.id.nav_setting) {
            getFragment("SettingFragment", map);

        } else if (item.getItemId() == R.id.nav_share) {

        } else if (item.getItemId() == R.id.nav_send) {

        }

        ((DrawerLayout)findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }


    private void getFragment(String fragmentName, HashMap<String, String> map) {
        Fragment fragment;

        switch (fragmentName) {
            case "ImageFragment": {
                fragment = ImageFragment.newInstance(map);
                break;
            }
            case "PhotoFragment": {
                fragment = PhotoFragment.newInstance(map);
                break;
            }
            case "SettingFragment": {
                map.put(THEME, String.valueOf(theme));
                fragment = SettingFragment.newInstance(map);
                break;
            }
            default: {
                fragment = ImageFragment.newInstance(map);
            }
        }
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .addToBackStack(null)
                .replace(R.id.frame, fragment).commit();
    }

    private void changeTheme() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        SharedPreferences mSettings = getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        theme = mSettings.getInt(THEME, R.style.RedAppTheme);
        setTheme(theme);
    }

}
