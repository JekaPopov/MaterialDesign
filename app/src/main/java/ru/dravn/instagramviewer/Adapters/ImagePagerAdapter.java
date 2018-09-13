package ru.dravn.instagramviewer.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

import ru.dravn.instagramviewer.R;

public class ImagePagerAdapter extends PagerAdapter {

    private final File[] files;
    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public ImagePagerAdapter(Context context, File[] files) {
        mContext = context;
        this.files = files;
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