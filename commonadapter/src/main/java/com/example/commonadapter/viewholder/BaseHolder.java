package com.example.commonadapter.viewholder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * author: Lancer
 * date：2018/8/16
 * des:
 * email:tyk790406977@126.com
 */

public class BaseHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public BaseHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public <T extends View> T findView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public BaseHolder setText(int viewId, String text) {
        TextView tv = findView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseHolder setText(int viewId, int text) {
        TextView tv = findView(viewId);
        tv.setText(text);
        return this;
    }

    public BaseHolder setImageResource(int viewId, int ImageId) {
        ImageView image = findView(viewId);
        image.setImageResource(ImageId);
        return this;
    }

    public BaseHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView = findView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public BaseHolder setImageNet(int viewId, String url, Context context) {
        final ImageView imageView = findView(viewId);

        Glide.with(context).load(url).into(imageView);
        return this;
    }
}
