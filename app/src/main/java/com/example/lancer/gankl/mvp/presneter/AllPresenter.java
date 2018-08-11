package com.example.lancer.gankl.mvp.presneter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lancer.gankl.adapter.AllAdapter;
import com.example.lancer.gankl.adapter.AndroidAdapter;
import com.example.lancer.gankl.api.GankApi;
import com.example.lancer.gankl.base.BasePresenter;
import com.example.lancer.gankl.bean.gank.AllBean;
import com.example.lancer.gankl.bean.gank.AndroidBean;
import com.example.lancer.gankl.mvp.view.AllView;
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

public class AllPresenter extends BasePresenter<AllView> {
    private Context mContext;
    private int page = 1;
    private AllView mAllView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private AllAdapter mAllAdapter;
    private List<AllBean.ResultsBean> mList = new ArrayList<>();
    private int lastVisibleItem;//最后一个条目
    private boolean isloadMore = false;
    public AllPresenter(Context context) {
        mContext = context;
    }

    @Override
    protected AllView getView() {
        return super.getView();
    }

    @Override
    public void attachView(AllView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getAll(final boolean flag, final Context context) {
        mAllView=getView();
        if(mAllView!=null){
            mRecyclerView = mAllView.getRecycleView();
            mLinearLayoutManager = mAllView.getLinearLayoutManager();
            NetUtil.getInstance().getGank().create(GankApi.class)
                    .getGankAll(page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<AllBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(AllBean value) {
                            if (flag) {
                                mList = value.getResults();
                                mAllAdapter = new AllAdapter(context, mList);
                                mRecyclerView.setLayoutManager(mLinearLayoutManager);
                                mRecyclerView.setAdapter(mAllAdapter);
                            } else {
                                List<AllBean.ResultsBean> results = value.getResults();
                                mList.addAll(results);
                                if (mAllAdapter != null) {
                                    mAllAdapter.notifyDataSetChanged();
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
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !isloadMore && lastVisibleItem + 1 == mAllAdapter.getItemCount()) {
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
        getAll(false, mContext);
        isloadMore = false;
    }
}
