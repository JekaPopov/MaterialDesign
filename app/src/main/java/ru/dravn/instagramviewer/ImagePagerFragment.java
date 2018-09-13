package ru.dravn.instagramviewer;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.HashMap;

import ru.dravn.instagramviewer.Adapters.ImagePagerAdapter;

public class ImagePagerFragment extends Fragment {


    private File[] files;

    public static ImagePagerFragment newInstance(HashMap<String, String> map) {
        ImagePagerFragment fragment = new ImagePagerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyFolder");
        if (!directory.exists())
            directory.mkdirs();

        files = directory.listFiles();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pager_image, container, false);

        ViewPager pager = v.findViewById(R.id.pager);

        ImagePagerAdapter mPagerAdapter = new ImagePagerAdapter(getContext(), files);

        pager.setAdapter(mPagerAdapter);
        return v;
    }
}
