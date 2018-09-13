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
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends MvpAppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    protected static final String SETTING = "setting";
    protected static final String THEME = "theme";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @InjectPresenter
    MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        changeTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ((NavigationView) findViewById(R.id.nav_view))
                .setNavigationItemSelectedListener(this);


        Boolean changeTheme = getIntent().getBooleanExtra(THEME, false);

        if (changeTheme) {
            presenter.startFragment("SettingFragment", new HashMap<String, String>());
        }

    }

    @OnClick(R.id.fab)
    public void onClick(Button button) {
        Snackbar.make(button, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1)
                super.onBackPressed();
            else {
                drawer.openDrawer(GravityCompat.START);
            }
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
            presenter.startFragment("SettingFragment", new HashMap<String, String>());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        HashMap<String, String> map = new HashMap<>();

        if (item.getItemId() == R.id.nav_camera) {

            presenter.startFragment("PhotoFragment", map);

        } else if (item.getItemId() == R.id.nav_gallery) {
            presenter.startFragment("ImageFragment", map);

        } else if (item.getItemId() == R.id.nav_setting) {
            presenter.startFragment("SettingFragment", map);


        } else if (item.getItemId() == R.id.nav_share) {

        } else if (item.getItemId() == R.id.nav_send) {

        }

        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }


    private void changeTheme() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        SharedPreferences mSettings = getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String theme = mSettings.getString(THEME, "RedAppTheme");
        setTheme(getResources().getIdentifier(theme, "style", getPackageName()));
    }

    @Override
    public void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame, fragment).commit();
    }
}
