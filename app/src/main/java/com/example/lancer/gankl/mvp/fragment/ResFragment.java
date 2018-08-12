package com.example.lancer.gankl.mvp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseFragment;
import com.example.lancer.gankl.mvp.presneter.ResPresenter;
import com.example.lancer.gankl.mvp.view.AndroidView;


public class ResFragment extends BaseFragment<AndroidView, ResPresenter> implements AndroidView {
    private LinearLayoutManager mLinearLayoutManager;
    private android.support.v4.widget.SwipeRefreshLayout refreshRes;
    private RecyclerView recycleRes;

    @Override
    public RecyclerView getRecycleView() {
        return recycleRes;
    }

    @Override
    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_res;
    }

    @Override
    protected void initView(View view) {

        refreshRes = view.findViewById(R.id.refresh_res);
        recycleRes = view.findViewById(R.id.recycle_res);
    }

    @Override
    protected void initData() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        recycleRes.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getRes(true, getContext());
        mPresenter.ScrollRecycleView();
        refreshRes.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshRes.setRefreshing(false);
                mPresenter.getmore();
            }
        });
    }

    @Override
    protected ResPresenter createPresenter() {
        return new ResPresenter(getContext());
    }
}
