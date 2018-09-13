package ru.dravn.instagramviewer;


import android.support.v4.app.Fragment;

import java.util.HashMap;

class MainModel {

    public Fragment getFragment(String fragmentName, HashMap<String, String> map) {

        switch (fragmentName) {
            case "ImageFragment": {
                return ImageFragment.newInstance(map);
            }
            case "PhotoFragment": {
                return PhotoFragment.newInstance(map);
            }
            case "SettingFragment": {
                return SettingFragment.newInstance(map);
            }
            default: {
                return ImageFragment.newInstance(map);
            }
        }
    }
}

