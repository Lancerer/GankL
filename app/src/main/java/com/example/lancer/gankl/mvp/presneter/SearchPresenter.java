package com.example.lancer.gankl.mvp.presneter;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.lancer.gankl.adapter.SearchAdapter;
import com.example.lancer.gankl.api.GankApi;
import com.example.lancer.gankl.base.BasePresenter;

import com.example.lancer.gankl.bean.search.SearchBean;
import com.example.lancer.gankl.mvp.activity.WebActivity;
import com.example.lancer.gankl.mvp.view.SerachView;
import com.example.lancer.gankl.util.NetUtil;

import java.util.List;
import java.util.Random;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * author: Lancer
 * date：2018/9/28
 * des:
 * email:tyk790406977@126.com
 */
public class SearchPresenter extends BasePresenter<SerachView> {
    private Activity mActivity;
    private SerachView mSerachView;
    private ListView mListView;
    private ImageView mIvBack;
    private ImageView mIvError;
    private EditText mEt;
    private String mContent;
    private SearchAdapter mSearchAdapter;


    public SearchPresenter(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected SerachView getView() {
        return super.getView();
    }

    @Override
    public void attachView(SerachView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void init() {
        mSerachView = getView();

        if (mSerachView != null) {
            mIvBack = mSerachView.getIvBack();
            mIvError = mSerachView.getError();
            mEt = mSerachView.getEt();
            mListView = mSerachView.getLv();

            mIvBack.setOnClickListener((view) -> {
                mActivity.finish();
            });

            mIvError.setOnClickListener((view) -> {
                mEt.setText("");
                mIvError.setVisibility(View.INVISIBLE);
                mListView.setVisibility(View.INVISIBLE);
            });
            mEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {
                    mIvError.setVisibility(View.VISIBLE);
                    getdata();
                    if(TextUtils.isEmpty(mContent)){
                        mIvError.setVisibility(View.INVISIBLE);
                    }
                }
            });
        }
    }

    private void getdata() {
        mContent = mEt.getText().toString();
        NetUtil.getInstance().getGank().create(GankApi.class)
                .getGankSearch("https://gank.io/api/search/query/" + mContent + "/category/all/count/50/page/" + new Random().nextInt(10) + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }
                    @Override
                    public void onNext(SearchBean value) {
                        List<SearchBean.ResultsBean> results = value.getResults();
                        mSearchAdapter = new SearchAdapter(results, mActivity);
                        mListView.setAdapter(mSearchAdapter);
                        mListView.setOnItemClickListener((parent, view, position, id) -> {
                            Intent intent = new Intent(mActivity, WebActivity.class);
                            intent.putExtra("title", results.get(position).getDesc());
                            intent.putExtra("url", results.get(position).getUrl());
                            intent.putExtra("who", results.get(position).getWho());
                            mActivity.startActivity(intent);

                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


}
