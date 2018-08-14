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
import com.example.lancer.gankl.base.BasePresenter;
import com.example.lancer.gankl.mvp.presneter.ZhihuPresenter;
import com.example.lancer.gankl.mvp.view.AndroidView;


public class ZhihuFragment extends BaseFragment<AndroidView, ZhihuPresenter> implements AndroidView {
    private android.support.v4.widget.SwipeRefreshLayout refreshZhihu;
    private RecyclerView recycleZhihu;
    private LinearLayoutManager mLinearLayoutManager;

    @Override
    protected int initLayout() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initView(View view) {

        refreshZhihu = view.findViewById(R.id.refresh_zhihu);
        recycleZhihu = view.findViewById(R.id.recycle_zhihu);
    }

    @Override
    protected void initData() {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        recycleZhihu.setLayoutManager(mLinearLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getZhihu();
        mPresenter.ScrollRecycleView();
        refreshZhihu.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshZhihu.setRefreshing(false);
                /*mPresenter.getmore();*/
            }
        });
    }

    @Override
    protected ZhihuPresenter createPresenter() {
        return new ZhihuPresenter(getContext());
    }

    @Override
    public RecyclerView getRecycleView() {
        return recycleZhihu;
    }

    @Override
    public LinearLayoutManager getLinearLayoutManager() {
        return mLinearLayoutManager;
    }
}
