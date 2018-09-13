package ru.dravn.instagramviewer.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;

import ru.dravn.instagramviewer.ImagePagerFragment;
import ru.dravn.instagramviewer.R;

public class ImagesAdapter extends BaseAdapter {
    private final Fragment fragment;
    private Context mContext;
    private File[] files;

    public ImagesAdapter(Context context, File[] files, Fragment fragment) {
        super();
        this.mContext = context;
        this.files = files;
        this.fragment = fragment;
    }

    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public File getItem(int position) {
        return files[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ImagesAdapter.ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item_layout, null);
            holder = new ImagesAdapter.ViewHolder();
            holder.imageView = convertView.findViewById(R.id.grid_item_image);
            convertView.setTag(holder);
        } else {
            holder = (ImagesAdapter.ViewHolder) convertView.getTag();
        }

        Picasso.get()
                .load(files[position])
                .fit()
                .centerCrop()
                .placeholder(R.drawable.ic_menu_gallery)
                .error(R.drawable.ic_menu_gallery)
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, ImagePagerFragment.newInstance(new HashMap<String, String>()))
                        .commit();
            }
        });


        return convertView;
    }

    private class ViewHolder {
        ImageView imageView;
    }

}

