package com.example.lancer.gankl.mvp.presneter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lancer.gankl.adapter.AndroidAdapter;
import com.example.lancer.gankl.adapter.ResAdapter;
import com.example.lancer.gankl.api.GankApi;
import com.example.lancer.gankl.base.BasePresenter;
import com.example.lancer.gankl.bean.gank.AndroidBean;
import com.example.lancer.gankl.bean.gank.ResBean;
import com.example.lancer.gankl.mvp.view.AndroidView;
import com.example.lancer.gankl.mvp.view.ResView;
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

public class ResPresenter extends BasePresenter<AndroidView> {
    private Context mContext;
    private int page = 1;

    private boolean isloadMore = false;
    private AndroidView mAndroidView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ResAdapter mResAdapter;
    private List<ResBean.ResultsBean> mList = new ArrayList<>();
    private int lastVisibleItem;//最后一个条目

    public ResPresenter(Context context) {
        mContext = context;
    }

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

    public void getRes(final boolean flag, final Context context) {
        mAndroidView = getView();
        if (mAndroidView != null) {
            mRecyclerView = mAndroidView.getRecycleView();
            mLinearLayoutManager = mAndroidView.getLinearLayoutManager();

            NetUtil.getInstance().getGank().create(GankApi.class)
                    .getGankRes(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResBean value) {
                            if (flag) {
                                mList = value.getResults();
                                mResAdapter = new ResAdapter(context, mList);
                             //   mRecyclerView.setLayoutManager(mLinearLayoutManager);
                                mRecyclerView.setAdapter(mResAdapter);
                            } else {
                                List<ResBean.ResultsBean> results = value.getResults();
                                mList.addAll(results);
                                if (mResAdapter != null) {
                                    mResAdapter.notifyDataSetChanged();
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
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !isloadMore && lastVisibleItem + 1 == mResAdapter.getItemCount()) {
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
        getRes(false, mContext);
        isloadMore = false;
    }
}
