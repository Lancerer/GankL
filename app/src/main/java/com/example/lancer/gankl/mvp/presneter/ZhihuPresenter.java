package com.example.lancer.gankl.mvp.presneter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lancer.gankl.adapter.ZhihuAdapter;
import com.example.lancer.gankl.api.ZhihuApi;
import com.example.lancer.gankl.base.BasePresenter;
import com.example.lancer.gankl.bean.zhihu.NewsTimeLine;
import com.example.lancer.gankl.bean.zhihu.Stories;
import com.example.lancer.gankl.mvp.view.AndroidView;

import com.example.lancer.gankl.util.Constants;
import com.example.lancer.gankl.util.NetUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Lancer
 * date：2018/8/12
 * des:
 * email:tyk790406977@126.com
 */

public class ZhihuPresenter extends BasePresenter<AndroidView> {
    private Context mContext;
    private List<Stories> mList = new ArrayList<>();
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerView mRecyclerView;
    private boolean isLoadMore = false;

    private AndroidView mAndroidView;
    private int lastVisibleItem;
    private ZhihuAdapter mZhihuAdapter;

    @Override
    protected AndroidView getView() {
        return super.getView();
    }

    @Override
    public void attachView(AndroidView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public ZhihuPresenter(Context context) {
        mContext = context;
    }

    public void getZhihu() {
        mAndroidView = getView();
        if (mAndroidView != null) {
            mRecyclerView = mAndroidView.getRecycleView();
            mLinearLayoutManager = mAndroidView.getLinearLayoutManager();
            NetUtil.getInstance().getZhihu().create(ZhihuApi.class)
                    .getLatestNews()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NewsTimeLine>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(NewsTimeLine value) {
                            ifLoadMore(value);
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

    private String time;

    private void ifLoadMore(NewsTimeLine value) {
        if (isLoadMore) {
            if (time == null) {
                return;
            } else {
                mList.addAll(value.getStories());
            }
            mZhihuAdapter.notifyDataSetChanged();
        } else {
            mList = value.getStories();
            mZhihuAdapter = new ZhihuAdapter(mContext, mList);
            mRecyclerView.setAdapter(mZhihuAdapter);
        }
        time = value.getDate();
    }

    public void getZhihuBefore(String time) {
        mAndroidView = getView();
        if (mAndroidView != null) {
            mRecyclerView = mAndroidView.getRecycleView();
            mLinearLayoutManager = mAndroidView.getLinearLayoutManager();
            NetUtil.getInstance().getZhihu().create(ZhihuApi.class)
                    .getBeforetNews(time)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<NewsTimeLine>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(NewsTimeLine value) {
                            ifLoadMore(value);
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
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mZhihuAdapter.getItemCount()) {
                    Log.e("GankModel", "addOnScrollListener: " + "加载更多");
                    isLoadMore = true;
                    getZhihuBefore(time);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }
}
