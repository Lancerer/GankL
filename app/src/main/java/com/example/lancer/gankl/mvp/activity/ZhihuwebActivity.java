package com.example.lancer.gankl.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lancer.gankl.R;
import com.example.lancer.gankl.base.BaseActivity;
import com.example.lancer.gankl.mvp.presneter.ZhihuWebPresenter;
import com.example.lancer.gankl.mvp.view.ZhihuWebView;

public class ZhihuwebActivity extends BaseActivity<ZhihuWebView, ZhihuWebPresenter> implements ZhihuWebView {

    private android.support.design.widget.CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView ivWebImg;
    private TextView tvImgTitle;
    private TextView tvImgSource;
    private WebView webView;
    private static final String ID = "id";
    private String id;

    private void parseIntent() {
        id = getIntent().getStringExtra(ID);
    }

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, ZhihuwebActivity.class);
        intent.putExtra(ZhihuwebActivity.ID, id);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroyImg();
    }

    @Override
    public WebView getWebView() {
        return webView;
    }

    @Override
    public ImageView getWebImg() {
        return ivWebImg;
    }

    @Override
    public TextView getImgTitle() {
        return tvImgTitle;
    }

    @Override
    public TextView getImgSource() {
        return tvImgSource;
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_zhihuweb;
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
        parseIntent();
        mPresenter.getNews(id);
    }

    @Override
    protected ZhihuWebPresenter createPresenter() {
        return new ZhihuWebPresenter(this);
    }
}
