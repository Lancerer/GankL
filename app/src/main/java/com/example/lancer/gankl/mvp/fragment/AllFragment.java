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
import com.example.lancer.gankl.base.BasePresenter;
import com.example.lancer.gankl.mvp.presneter.AllPresenter;
import com.example.lancer.gankl.mvp.view.AllView;
import com.example.lancer.gankl.mvp.view.AndroidView;


public class AllFragment extends BaseFragment<AllView, AllPresenter> implements AllView {

    private android.support.v7.widget.RecyclerView recycleAll;
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
    private SwipeRefreshLayout refreshAll;

    @Override
    protected int initLayout() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initView(View view) {
        recycleAll = view.findViewById(R.id.recycle_all);
        refreshAll =view. findViewById(R.id.refresh_all);
    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
        mPresenter.getAll(true,getContext());
        mPresenter.ScrollRecycleView();

        refreshAll.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getmore();
                refreshAll.setRefreshing(false);
            }
        });
    }

    @Override
    protected AllPresenter createPresenter() {
        return new AllPresenter(getContext());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public RecyclerView getRecycleView() {
        return recycleAll;
    }

    @Override
    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }
}
