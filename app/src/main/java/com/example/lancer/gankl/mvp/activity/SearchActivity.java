package com.example.lancer.gankl.mvp.activity;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;


import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseActivity;
import com.example.lancer.gankl.mvp.presneter.SearchPresenter;
import com.example.lancer.gankl.mvp.view.SerachView;

public class SearchActivity extends BaseActivity<SerachView, SearchPresenter> implements SerachView {


    private android.widget.ImageView ivBack;
    private android.widget.EditText etSearch;
    private android.widget.ImageView icError;
    private android.widget.ListView lvSearch;

    @Override
    protected int initLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {

        ivBack = findViewById(R.id.iv_back);
        etSearch = findViewById(R.id.et_search);
        icError = findViewById(R.id.ic_error);
        lvSearch = findViewById(R.id.lv_search);
    }

    @Override
    protected void initData() {
        mPresenter.attachView(this);
        mPresenter.init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    public ImageView getIvBack() {
        return ivBack;
    }

    @Override
    public ImageView getError() {
        return icError;
    }

    @Override
    public ListView getLv() {
        return lvSearch;
    }

    @Override
    public EditText getEt() {
        return etSearch;
    }
}
