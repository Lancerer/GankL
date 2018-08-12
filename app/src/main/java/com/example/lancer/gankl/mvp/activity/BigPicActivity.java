package com.example.lancer.gankl.mvp.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseActivity;
import com.example.lancer.gankl.base.BaseFragment;
import com.example.lancer.gankl.base.BasePresenter;

public class BigPicActivity extends BaseActivity {


    private android.widget.LinearLayout ll;
    private android.widget.ImageView ivBig;
    /*
        * 沉浸式代码
        * */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_big_pic;
    }

    @Override
    protected void initView() {

        ll = findViewById(R.id.ll);
        ivBig = findViewById(R.id.iv_big);
    }


    @Override
    protected void initData() {
            String imgurl = getIntent().getStringExtra("imgurl");
            Glide.with(this).load(imgurl).into(ivBig);
            ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
