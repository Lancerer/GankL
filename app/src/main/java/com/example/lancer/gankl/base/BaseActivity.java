package com.example.lancer.gankl.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.SupportActionModeWrapper;
import android.view.Window;

import com.example.lancer.gankl.util.ThemeUtil;

/**
 * author: Lancer
 * date：2018/8/9
 * des:
 * email:tyk790406977@126.com
 */

public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {
    protected T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        ThemeUtil.getInstance().init(this);
        setContentView(initLayout());
        if (mPresenter != null) {
            mPresenter = createPresenter();
            mPresenter.attachView((V) this);
        }
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract T createPresenter();
}
