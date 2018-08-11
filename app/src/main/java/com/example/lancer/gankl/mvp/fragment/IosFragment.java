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
import com.example.lancer.gankl.mvp.presneter.IosPresenter;
import com.example.lancer.gankl.mvp.view.AndroidView;
import com.example.lancer.gankl.mvp.view.IosView;


public class IosFragment extends BaseFragment<IosView, IosPresenter> implements IosView {
    private LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(getContext());
    private android.support.v4.widget.SwipeRefreshLayout refreshIos;
    private RecyclerView recycleIos;

    @Override
    public RecyclerView getRecycleView() {
        return recycleIos;
    }

    @Override
    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_ios;
    }

    @Override
    protected void initView(View view) {

        refreshIos = view.findViewById(R.id.refresh_ios);
        recycleIos = view.findViewById(R.id.recycle_ios);
    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
        mPresenter.getIos(true,getContext());
        mPresenter.ScrollRecycleView();
        refreshIos.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshIos.setRefreshing(false);
                mPresenter.getmore();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected IosPresenter createPresenter() {
        return new IosPresenter(getContext());
    }
}
