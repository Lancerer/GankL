package com.example.lancer.gankl.mvp.presneter;

import android.content.Context;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import com.bumptech.glide.Glide;
import com.example.lancer.gankl.R;
import com.example.lancer.gankl.adapter.AndroidAdapter;
import com.example.lancer.gankl.adapter.CommonAdapter;
import com.example.lancer.gankl.adapter.CommonHolder;
import com.example.lancer.gankl.api.GankApi;
import com.example.lancer.gankl.base.BasePresenter;
import com.example.lancer.gankl.bean.gank.AndroidBean;
import com.example.lancer.gankl.mvp.view.AndroidView;
import com.example.lancer.gankl.util.Constants;
import com.example.lancer.gankl.util.NetUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Lancer
 * date：2018/8/11
 * des:
 * email:tyk790406977@126.com
 */

public class AndroidPresenter extends BasePresenter<AndroidView> {
    private Context mContext;
    private int page = 1;
    private CommonAdapter mCommonAdapter;
    private boolean isloadMore = false;
    private AndroidView mAndroidView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private AndroidAdapter mAndroidAdapter;
    private List<AndroidBean.ResultsBean> mList = new ArrayList<>();
    private int lastVisibleItem;//最后一个条目

    public AndroidPresenter(Context context) {
        mContext = context;
    }

    @Override
    public void attachView(AndroidView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    protected AndroidView getView() {
        return super.getView();
    }

    public void getAdnroid(final boolean flag, final Context context) {
        mAndroidView = getView();
        if (mAndroidView != null) {
            mRecyclerView = mAndroidView.getRecycleView();
            mLinearLayoutManager = mAndroidView.getLinearLayoutManager();

            NetUtil.getInstance().getGank().create(GankApi.class)
                    .getGankAndroid(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AndroidBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AndroidBean value) {
                            if (flag) {
                                mList = value.getResults();
                             /*   mRecyclerView.setAdapter(mCommonAdapter=new CommonAdapter<AndroidBean.ResultsBean, String, String>(mContext, R.layout.item_android, mList, 0, null, R.layout.item_foot, null) {
                                    @Override
                                    public void convertBody(CommonHolder holder, AndroidBean.ResultsBean data, int position) {
                                        holder.setText(R.id.tv_android_title, data.getDesc());
                                        List<String> images = data.getImages();
                                        if (images != null && images.size() > 0) {
                                            holder.setImageNet(R.id.iv_android_img, images.get(0), mContext);

                                        } else {
                                            holder.setImageResource(R.id.iv_android_img, R.drawable.sea);
                                        }
                                    }

                                    @Override
                                    public void convertFooter(CommonHolder holder, String data, int position) {
                                        holder.setText(R.id.tv_load,"loading....");
                                    }
                                });*/
                               mAndroidAdapter = new AndroidAdapter(mList, context);
                                //mRecyclerView.setLayoutManager(mLinearLayoutManager);
                                mRecyclerView.setAdapter(mAndroidAdapter);
                            } else {
                                List<AndroidBean.ResultsBean> results = value.getResults();
                                mList.addAll(results);
                                if (mAndroidAdapter != null) {
                                    mAndroidAdapter.notifyDataSetChanged();
                                }

                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d("GANKL", e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }
    }

    public void ScrollRecycleView() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !isloadMore && lastVisibleItem + 1 == mAndroidAdapter.getItemCount()) {
                    if (page < Constants.totalPage) {
                        Log.e("GankModel", "addOnScrollListener: " + "加载更多");
                        isloadMore = true;
                        page++;
                        getmore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    public void getmore() {
        getAdnroid(false, mContext);
        isloadMore = false;
    }

  /*  public void onClick() {
        mAndroidAdapter.setOnItemClickListener(new AndroidAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent();
            }
        });
    }*/
}
