package com.example.lancer.gankl.mvp.presneter;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lancer.gankl.api.ZhihuApi;
import com.example.lancer.gankl.base.BasePresenter;
import com.example.lancer.gankl.bean.zhihu.News;
import com.example.lancer.gankl.mvp.view.ZhihuWebView;
import com.example.lancer.gankl.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Lancer
 * date：2018/8/14
 * des:
 * email:tyk790406977@126.com
 */

public class ZhihuWebPresenter extends BasePresenter<ZhihuWebView> {
    private Context mContext;

    public ZhihuWebPresenter(Context context) {
        mContext = context;
    }

    @Override
    protected ZhihuWebView getView() {
        return super.getView();
    }

    @Override
    public void attachView(ZhihuWebView view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    public void getNews(String id) {
        NetUtil.getInstance().getZhihu().create(ZhihuApi.class)
                .getDetailNews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<News>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(News value) {
                        setwebView(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    ImageView webImg;

    private void setwebView(News news) {
        WebView webView = getView().getWebView();
        webImg = getView().getWebImg();
        TextView imgTitle = getView().getImgTitle();
        TextView imgSource = getView().getImgSource();

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String head = "<head>\n" +
                "\t<link rel=\"stylesheet\" href=\"" + news.getCss()[0] + "\"/>\n" +
                "</head>";
        String img = "<div class=\"headline\">";
        String html = head + news.getBody().replace(img, " ");
        webView.loadDataWithBaseURL(null, html, "text/html", "utf-8", null);
        Glide.with(mContext).load(news.getImage()).centerCrop().into(webImg);

        imgTitle.setText(news.getTitle());
        imgSource.setText(news.getImage_source());
    }

    public void destroyImg() {
        if (webImg != null) {
            Glide.clear(webImg);
        }
    }
}
