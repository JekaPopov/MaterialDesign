package ru.dravn.instagramviewer;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PhotoFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private ImageView image;
    private static final int REQUEST_CODE_PHOTO = 1;
    File directory;
    private Uri photo;


    public static PhotoFragment newInstance(HashMap<String, String> map) {
        PhotoFragment fragment = new PhotoFragment();
        Bundle args = new Bundle();
        //for the future
//        args.putString(ARG_PARAM1, map.get(ARG_PARAM1));
//        args.putString(ARG_PARAM2, map.get(ARG_PARAM2));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createDirectory();
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_photo, container, false);

        image = v.findViewById(R.id.image);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        photo = generateFileUri();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photo);
        startActivityForResult(intent, REQUEST_CODE_PHOTO);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                Picasso.get()
                        .load(photo)
                        .fit()
                        .centerCrop()
                        .placeholder(R.drawable.ic_menu_gallery)
                        .error(R.drawable.ic_menu_gallery)
                        .into(image);
            }
        }
    }

    private Uri generateFileUri() {

        File file = new File(directory.getPath() + "/" + "photo_"
                + System.currentTimeMillis() + ".jpg");
        return Uri.fromFile(file);
    }

    private void createDirectory() {
        directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyFolder");
        if (!directory.exists())
            directory.mkdirs();
    }
}
