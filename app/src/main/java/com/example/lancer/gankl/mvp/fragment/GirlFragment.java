package com.example.lancer.gankl.mvp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseFragment;
import com.example.lancer.gankl.mvp.presneter.GirlPresenter;
import com.example.lancer.gankl.mvp.view.GirlView;


public class GirlFragment extends BaseFragment<GirlView, GirlPresenter> implements GirlView {
    private android.support.v4.widget.SwipeRefreshLayout refreshGirl;
    private RecyclerView recycleGirl;
    private GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);

    @Override
    public RecyclerView getRecycleView() {
        return recycleGirl;
    }

    @Override
    public GridLayoutManager getGridLayoutManager() {
        return mGridLayoutManager;
    }


    @Override
    protected int initLayout() {
        return R.layout.fragment_girl;
    }

    @Override
    protected void initView(View view) {

        refreshGirl = view.findViewById(R.id.refresh_girl);
        recycleGirl = view.findViewById(R.id.recycle_girl);
    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
        mPresenter.getGirl(true, getContext());
        mPresenter.ScrollRecycleView();
        refreshGirl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getMore();
                refreshGirl.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected GirlPresenter createPresenter() {
        return new GirlPresenter(getContext());
    }
}