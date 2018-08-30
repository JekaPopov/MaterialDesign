package ru.dravn.instagramviewer;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ImageFragment extends Fragment {

    private File directory;
    File[] files;
    ArrayList<String> data = new ArrayList<>();
    private ViewPager pager;
    private GridView gallery;


    public static ImageFragment newInstance(HashMap<String, String> map) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyFolder");
        if (!directory.exists())
            directory.mkdirs();

        files = directory.listFiles();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_image, container, false);


        ImagesAdapter adapter = new ImagesAdapter(getContext(), files);
        gallery = v.findViewById(R.id.gallery);
        gallery.setAdapter(adapter);

        pager = v.findViewById(R.id.pager);

        return v;
    }

    private class ImagesAdapter extends BaseAdapter {
        private Context mContext;
        private File[] files;

        public ImagesAdapter(Context context, File[] files) {
            super();
            this.mContext = context;
            this.files = files;
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
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item_layout, null);
                holder = new ViewHolder();
                holder.imageView = convertView.findViewById(R.id.grid_item_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
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
                    ImageFragment.ImagePagerAdapter mPagerAdapter = new ImageFragment.ImagePagerAdapter(getContext(), files);

                    pager.setAdapter(mPagerAdapter);
                    pager.setCurrentItem(position);
                    pager.setVisibility(View.VISIBLE);
                    gallery.setVisibility(View.GONE);
                }
            });


            return convertView;
        }

        private class ViewHolder {
            ImageView imageView;
        }

    }

    class ImagePagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public ImagePagerAdapter(Context context, File[] files) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return files.length;
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            Picasso.get()
                    .load(files[position])
                    .fit()
                    .centerCrop()
                    .placeholder(R.drawable.ic_menu_gallery)
                    .error(R.drawable.ic_menu_gallery)
                    .into((ImageView) itemView.findViewById(R.id.image));

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}

