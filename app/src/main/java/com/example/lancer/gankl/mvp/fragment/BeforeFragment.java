package com.example.lancer.gankl.mvp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseFragment;
import com.example.lancer.gankl.mvp.presneter.BeforePresenter;
import com.example.lancer.gankl.mvp.view.AndroidView;
import com.example.lancer.gankl.mvp.view.BeforeView;


public class BeforeFragment extends BaseFragment<BeforeView, BeforePresenter> implements BeforeView {

    private android.support.v4.widget.SwipeRefreshLayout refreshBefore;
    private RecyclerView recycleBefore;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());

    @Override
    public RecyclerView getRecycleView() {
        return recycleBefore;
    }

    @Override
    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_before;
    }

    @Override
    protected void initView(View view) {

        refreshBefore = view.findViewById(R.id.refresh_before);
        recycleBefore = view.findViewById(R.id.recycle_before);
    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
        mPresenter.getBefore(true, getContext());
        mPresenter.ScrollRecycleView();
        refreshBefore.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getmore();
                refreshBefore.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected BeforePresenter createPresenter() {
        return new BeforePresenter(getContext());
    }
}
