package com.example.lancer.gankl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lancer.gankl.R;
import com.example.lancer.gankl.bean.gank.AllBean;
import com.example.lancer.gankl.mvp.activity.WebActivity;
import com.example.lancer.gankl.util.Constants;

import java.util.List;

/**
 * author: Lancer
 * date：2018/8/11
 * des:
 * email:tyk790406977@126.com
 */

public class AllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<AllBean.ResultsBean> mList;

    public AllAdapter(Context context, List<AllBean.ResultsBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Constants.TYPE_NORMAL) {
            View view = View.inflate(mContext, R.layout.item_android, null);
            NormalViewHolder normalViewHolder = new NormalViewHolder(view);

            return normalViewHolder;
        } else {
            View view = View.inflate(mContext, R.layout.item_foot, null);
            FootViewHolder footViewHolder = new FootViewHolder(view);
            return footViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NormalViewHolder) {
            ((NormalViewHolder) holder).tvAndroidTitle.setText(mList.get(position).getDesc());
            final List<String> images = mList.get(position).getImages();
            if (images != null&&images.size()>0) {
                Glide.with(mContext).load(images.get(0)).into(((NormalViewHolder) holder).ivAndroidImg);

            } else {
                Glide.with(mContext).load(R.drawable.sea).into(((NormalViewHolder) holder).ivAndroidImg);
            }
            ((NormalViewHolder) holder).cdAndroid.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, WebActivity.class);
                intent.putExtra("title", mList.get(position).getDesc());
                intent.putExtra("url", mList.get(position).getUrl());
                intent.putExtra("who", mList.get(position).getWho());
                if (images != null) {
                    intent.putExtra("image", images.get(0));
                }

                mContext.startActivity(intent);
            });
            ((NormalViewHolder) holder).itemView.setTag(position);


        } else if (holder instanceof FootViewHolder) {
            ((FootViewHolder) holder).pb.setVisibility(View.VISIBLE);
            ((FootViewHolder) holder).tvLoad.setText("正在加载。。。");

            ((FootViewHolder) holder).itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() + 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return Constants.TYPE_FOOT;
        }
        return Constants.TYPE_NORMAL;
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        private CardView cdAndroid;
        private TextView tvAndroidTitle;
        private ImageView ivAndroidImg;

        public NormalViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            cdAndroid = view.findViewById(R.id.cd_android);
            tvAndroidTitle = view.findViewById(R.id.tv_android_title);
            ivAndroidImg = view.findViewById(R.id.iv_android_img);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar pb;
        private TextView tvLoad;


        public FootViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            pb = view.findViewById(R.id.pb);
            tvLoad = view.findViewById(R.id.tv_load);
        }
    }
}
