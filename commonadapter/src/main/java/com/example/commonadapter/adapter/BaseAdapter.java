package com.example.commonadapter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.commonadapter.viewholder.BaseHolder;

/**
 * author: Lancer
 * date：2018/8/16
 * des:
 * email:tyk790406977@126.com
 */

public class BaseAdapter extends RecyclerView.Adapter<BaseHolder> {
    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
