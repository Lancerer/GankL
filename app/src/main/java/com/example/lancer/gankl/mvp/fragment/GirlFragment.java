package com.example.lancer.gankl.mvp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bm.library.PhotoView;
import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseFragment;
import com.example.lancer.gankl.mvp.presneter.GirlPresenter;
import com.example.lancer.gankl.mvp.view.GirlView;


public class GirlFragment extends BaseFragment<GirlView, GirlPresenter> implements GirlView {
    private android.support.v4.widget.SwipeRefreshLayout refreshGirl;
    private RecyclerView recycleGirl;
    private GridLayoutManager mGridLayoutManager;



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
        mGridLayoutManager = new GridLayoutManager(getContext(), 2);
        recycleGirl.setLayoutManager(mGridLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getGirl(true, getContext());
        mPresenter.ScrollRecycleView();
        refreshGirl.setOnRefreshListener(() -> {
            mPresenter.getMore();
            refreshGirl.setRefreshing(false);
        });
    }

    @Override
    protected GirlPresenter createPresenter() {
        return new GirlPresenter(getContext());
    }


}
