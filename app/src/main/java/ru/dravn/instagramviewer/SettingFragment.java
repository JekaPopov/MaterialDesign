package ru.dravn.instagramviewer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingFragment extends MvpAppCompatFragment implements SettingView {

    @BindView(R.id.buttonGreen)
    Button buttonGreen;
    @BindView(R.id.buttonBlue)
    Button buttonBlue;
    @BindView(R.id.buttonRed)
    Button buttonRed;

    @InjectPresenter
    SettingPresenter presenter;

    @ProvidePresenter
    public SettingPresenter provideSettingPresenter() {
        SharedPreferences mSettings = getActivity()
                .getSharedPreferences(MainActivity.SETTING, Context.MODE_PRIVATE);

        presenter = new SettingPresenter(mSettings);
        return presenter;
    }

    public static SettingFragment newInstance(HashMap<String, String> map) {
        return new SettingFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, v);
        initElement();

        return v;
    }

    @OnClick({R.id.buttonRed, R.id.buttonBlue, R.id.buttonGreen})
    public void onClick(Button button) {
        presenter.selectTheme((String) button.getTag());
    }

    private void initElement() {
        buttonGreen.setTag(SettingPresenter.GREEN);
        buttonBlue.setTag(SettingPresenter.BLUE);
        buttonRed.setTag(SettingPresenter.RED);
        presenter.hideButton();
    }


    @Override
    public void changeTheme() {

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(MainActivity.THEME, true);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void hideButton(String theme) {
        buttonRed.setVisibility((buttonRed.getTag().equals(theme) ? View.GONE : View.VISIBLE));
        buttonBlue.setVisibility((buttonBlue.getTag().equals(theme) ? View.GONE : View.VISIBLE));
        buttonGreen.setVisibility((buttonGreen.getTag().equals(theme) ? View.GONE : View.VISIBLE));
    }

}
