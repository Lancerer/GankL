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
import com.example.lancer.gankl.mvp.presneter.IosPresenter;
import com.example.lancer.gankl.mvp.view.AndroidView;



public class IosFragment extends BaseFragment<AndroidView, IosPresenter> implements AndroidView {
    private LinearLayoutManager mLinearLayoutManager ;
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
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        recycleIos.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    protected IosPresenter createPresenter() {
        return new IosPresenter(getContext());
    }
}
