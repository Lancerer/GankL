package com.example.lancer.gankl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.lancer.gankl.R;
import com.example.lancer.gankl.bean.gank.MeiziBean;
import com.example.lancer.gankl.mvp.activity.BigPicActivity;
import com.example.lancer.gankl.util.Constants;

import java.util.List;

/**
 * author: Lancer
 * date：2018/8/11
 * des:
 * email:tyk790406977@126.com
 */

public class GirlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private Context mContext;
    private List<MeiziBean.ResultsBean> mList;
    private Info mInfo;

    public GirlAdapter(Context context, List<MeiziBean.ResultsBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Constants.TYPE_NORMAL) {
            View view = View.inflate(mContext, R.layout.item_girl, null);
            NormalViewHolder normalViewHolder = new NormalViewHolder(view);
            view.setOnClickListener(this);
            return normalViewHolder;
        } else {
            View view = View.inflate(mContext, R.layout.item_foot, null);
            FootViewHolder footViewHolder = new FootViewHolder(view);

            return footViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof NormalViewHolder) {
            ((NormalViewHolder) holder).itemView.setTag(position);
            Glide.with(mContext).load(mList.get(position).getUrl()).into(((NormalViewHolder) holder).ivGirl);
           /* ((NormalViewHolder) holder).ivGirl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BigPicActivity.class);
                    intent.putExtra("imgurl", mList.get(position).getUrl());
                    mContext.startActivity(intent);
                }
            });*/
        } else if (holder instanceof FootViewHolder) {
            ((FootViewHolder) holder).pb.setVisibility(View.VISIBLE);
            ((FootViewHolder) holder).tvLoad.setText("正在加载。。。");
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
        private PhotoView ivGirl;

        public NormalViewHolder(View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View view) {
            ivGirl = view.findViewById(R.id.iv_girl);

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

    //声明一个这个接口的变量
    private OnItemClickListener mOnItemClickListener = null;

    //在MyAdapter中定义如下接口,模拟ListView的OnItemClickListener
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // 将点击事件转移给外面的调用者
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
//注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v, (int) v.getTag());
        }
    }

    //最后暴露给外面的调用者，定义一个设置Listener的方法（）：
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
