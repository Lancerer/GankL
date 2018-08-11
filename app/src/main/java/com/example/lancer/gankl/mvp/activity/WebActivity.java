package com.example.lancer.gankl.mvp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;

import com.bumptech.glide.Glide;
import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseActivity;
import com.example.lancer.gankl.base.BasePresenter;

public class WebActivity extends BaseActivity {


    private android.support.design.widget.CollapsingToolbarLayout collapsingToolbarLayout;
    private android.widget.ImageView ivWebImg;
    private android.widget.TextView tvImgTitle;
    private android.widget.TextView tvImgSource;
    private android.webkit.WebView webView;

    @Override
    protected int initLayout() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        ivWebImg = findViewById(R.id.iv_web_img);
        tvImgTitle = findViewById(R.id.tv_img_title);
        tvImgSource = findViewById(R.id.tv_img_source);
        webView = findViewById(R.id.web_view);
    }

    @Override
    protected void initData() {
        String image = getIntent().getStringExtra("image");
        String title = getIntent().getStringExtra("title");
        String url = getIntent().getStringExtra("url");
        String who = getIntent().getStringExtra("who");

        if (image != null) {
            Glide.with(this).load(image).into(ivWebImg);
        } else {
            ivWebImg.setImageResource(R.drawable.cat);
        }
        tvImgSource.setText(who);
        tvImgTitle.setText(title);

        webView.loadUrl(url);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        settings.setSupportZoom(true);
        // 设置出现缩放工具
        settings.setBuiltInZoomControls(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);


    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
