package ru.dravn.instagramviewer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;

public class SettingFragment extends Fragment{

    private String theme;

    public static SettingFragment newInstance(HashMap<String, String> map) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.THEME, map.get(MainActivity.THEME));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            theme = getArguments().getString(MainActivity.THEME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        checkSelectButton(v);

        v.findViewById(R.id.buttonGreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme(R.style.GreenAppTheme);
            }
        });
        v.findViewById(R.id.buttonRed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme(R.style.RedAppTheme);
            }
        });
        v.findViewById(R.id.buttonBlue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTheme(R.style.BlueAppTheme);
            }
        });


        return v;
    }
    private void changeTheme(int themeId) {
        SharedPreferences mSettings = getActivity().getSharedPreferences(MainActivity.SETTING, Context.MODE_PRIVATE);
        mSettings.edit().putInt(MainActivity.THEME, themeId).apply();
        //getActivity().recreate();//doesn`t work anim

        getActivity().finish();
        getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        getActivity().startActivity(getActivity().getIntent());
    }

    private void checkSelectButton(View v) {

        switch (Integer.valueOf(theme)) {
            case R.style.GreenAppTheme: {
                v.findViewById(R.id.buttonGreen).setVisibility(View.GONE);
                break;
            }
            case R.style.BlueAppTheme: {
                v.findViewById(R.id.buttonBlue).setVisibility(View.GONE);
                break;
            }
            case R.style.RedAppTheme: {
                v.findViewById(R.id.buttonRed).setVisibility(View.GONE);
                break;
            }
        }
    }

}
