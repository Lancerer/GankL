package com.example.lancer.gankl.mvp.fragment;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseFragment;

import com.example.lancer.gankl.mvp.presneter.AndroidPresenter;
import com.example.lancer.gankl.mvp.view.AndroidView;


public class AndroidFragment extends BaseFragment<AndroidView, AndroidPresenter> implements AndroidView {

    private android.support.v4.widget.SwipeRefreshLayout refreshAndroid;
    private android.support.v7.widget.RecyclerView recycleAndroid;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());

    @Override
    protected int initLayout() {
        return R.layout.fragment_android;
    }

    @Override
    protected void initView(View view) {
        refreshAndroid = view.findViewById(R.id.refresh_android);
        recycleAndroid = view.findViewById(R.id.recycle_android);
    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
        mPresenter.getAdnroid(true, getContext());
        mPresenter.ScrollRecycleView();

        refreshAndroid.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              mPresenter.getmore();
                refreshAndroid.setRefreshing(false);
            }
        });
    }

    @Override
    protected AndroidPresenter createPresenter() {
        return new AndroidPresenter(getContext());
    }

    @Override
    public RecyclerView getRecycleView() {
        return recycleAndroid;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }
}
