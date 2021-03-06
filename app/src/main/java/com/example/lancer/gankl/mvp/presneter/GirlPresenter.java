package com.example.lancer.gankl.mvp.presneter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.example.lancer.gankl.R;
import com.example.lancer.gankl.adapter.GirlAdapter;
import com.example.lancer.gankl.api.GankApi;
import com.example.lancer.gankl.base.BasePresenter;
import com.example.lancer.gankl.bean.gank.MeiziBean;
import com.example.lancer.gankl.mvp.activity.BigPicActivity;
import com.example.lancer.gankl.mvp.view.GirlView;
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

public class GirlPresenter extends BasePresenter<GirlView> {
    private Context mContext;
    private GirlView mGirlView;
    private List<MeiziBean.ResultsBean> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private GirlAdapter mGirlAdapter;
    private boolean isLoadMore = false;
    private int page = 1;
    private int lastVisibleItem;


    public GirlPresenter(Context context) {
        mContext = context;
    }

    @Override
    protected GirlView getView() {
        return super.getView();
    }

    @Override
    public void attachView(GirlView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getGirl(final Boolean flag, final Context context) {
        mGirlView = getView();
        if (mGirlView != null) {
            mRecyclerView = mGirlView.getRecycleView();
            mGridLayoutManager = mGirlView.getGridLayoutManager();
            NetUtil.getInstance().getGank().create(GankApi.class)
                    .getMeizi(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<MeiziBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                        @Override
                        public void onNext(final MeiziBean value) {
                            if (flag) {
                                mList = value.getResults();
                                mGirlAdapter = new GirlAdapter(context, mList);
                                mRecyclerView.setAdapter(mGirlAdapter);

                                mGirlAdapter.setOnItemClickListener((view, position) -> {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                        Intent intent = new Intent(context, BigPicActivity.class);
                                        intent.putExtra("imgurl", mList.get(position).getUrl());
                                        context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) context, view, "img").toBundle());
                                    }
                                });
                            } else {
                                List<MeiziBean.ResultsBean> results = value.getResults();
                                mList.addAll(results);
                                if (mGirlAdapter != null) {
                                    mGirlAdapter.notifyDataSetChanged();
                                }
                            }
                        }
                        @Override
                        public void onError(Throwable e) {
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
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadMore && lastVisibleItem + 1 == mGirlAdapter.getItemCount()) {
                    if (page < Constants.totalPage) {
                        isLoadMore = true;
                        page++;
                        getMore();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mGridLayoutManager.findLastVisibleItemPosition();
            }
        });
    }
    public void getMore() {
        getGirl(false, mContext);
        isLoadMore = false;
    }
}
