package com.example.lancer.gankl.mvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseActivity;
import com.example.lancer.gankl.base.BasePresenter;

public class AboutActivity extends BaseActivity {


    private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected int initLayout() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {

        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    protected void initData() {
        toolbar.setTitle("关于");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
