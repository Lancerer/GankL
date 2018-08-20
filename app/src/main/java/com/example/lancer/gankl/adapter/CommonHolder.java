package com.example.lancer.gankl.adapter;

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

public class CommonHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;

    public CommonHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<View>();
    }

    public <T extends View> T findView(int ViewId) {
        View view = mViews.get(ViewId);
        //集合中没有，则从item当中获取，并存入集合当中
        if (view == null) {
            view = itemView.findViewById(ViewId);
            mViews.put(ViewId, view);
        }
        return (T) view;
    }

    public CommonHolder setText(int viewId, String text) {
        TextView tv = findView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonHolder setText(int viewId, int text) {
        TextView tv = findView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonHolder setImageResource(int viewId, int ImageId) {
        ImageView image = findView(viewId);
        image.setImageResource(ImageId);
        return this;
    }

    public CommonHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView = findView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public CommonHolder setImageNet(int viewId, String url, Context context) {
        final ImageView imageView = findView(viewId);

        Glide.with(context).load(url).into(imageView);
        return this;
    }
}
