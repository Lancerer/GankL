package com.example.lancer.gankl.mvp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
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



public class BeforeFragment extends BaseFragment<AndroidView, BeforePresenter> implements AndroidView {

    private android.support.v4.widget.SwipeRefreshLayout refreshBefore;
    private RecyclerView recycleBefore;
    private LinearLayoutManager mLinearLayoutManager;

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
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        recycleBefore.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    protected BeforePresenter createPresenter() {
        return new BeforePresenter(getContext());
    }
}
