package com.example.lancer.gankl.mvp.presneter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lancer.gankl.adapter.AndroidAdapter;
import com.example.lancer.gankl.adapter.BeforeAdapter;
import com.example.lancer.gankl.api.GankApi;
import com.example.lancer.gankl.base.BasePresenter;
import com.example.lancer.gankl.bean.gank.AndroidBean;
import com.example.lancer.gankl.bean.gank.BeforeBean;
import com.example.lancer.gankl.mvp.view.AndroidView;
import com.example.lancer.gankl.mvp.view.BeforeView;
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

public class BeforePresenter extends BasePresenter<BeforeView> {
    private Context mContext;
    private int page = 1;

    private boolean isloadMore = false;
    private BeforeView mBeforeView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private BeforeAdapter mBeforeAdapter;
    private List<BeforeBean.ResultsBean> mList = new ArrayList<>();
    private int lastVisibleItem;//最后一个条目

    public BeforePresenter(Context context) {
        mContext = context;
    }

    @Override
    protected BeforeView getView() {
        return super.getView();
    }

    @Override
    public void attachView(BeforeView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getBefore(final Boolean flag, final Context context) {
        mBeforeView = getView();
        if (mBeforeView != null) {
            mRecyclerView = mBeforeView.getRecycleView();
            mLinearLayoutManager = mBeforeView.getLinearLayoutManager();
            NetUtil.getInstance().getGank().create(GankApi.class)
                    .getGankBefore(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<BeforeBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(BeforeBean value) {
                            if (flag) {
                                mList = value.getResults();
                                mBeforeAdapter = new BeforeAdapter(context, mList);
                                mRecyclerView.setLayoutManager(mLinearLayoutManager);
                                mRecyclerView.setAdapter(mBeforeAdapter);
                            } else {
                                List<BeforeBean.ResultsBean> results = value.getResults();
                                mList.addAll(results);
                                if (mBeforeAdapter != null) {
                                    mBeforeAdapter.notifyDataSetChanged();
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
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !isloadMore && lastVisibleItem + 1 == mBeforeAdapter.getItemCount()) {
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
        getBefore(false, mContext);
        isloadMore = false;
    }
}
