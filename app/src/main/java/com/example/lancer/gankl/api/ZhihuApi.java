package com.example.lancer.gankl.api;



import com.example.lancer.gankl.bean.zhihu.News;
import com.example.lancer.gankl.bean.zhihu.NewsTimeLine;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by lancer on 2018/6/19.
 */

public interface ZhihuApi {
    @GET("news/latest")
    Observable<NewsTimeLine> getLatestNews();

    @GET("news/before/{time}")
    Observable<NewsTimeLine> getBeforetNews(@Path("time") String time);

    @GET("news/{id}")
    Observable<News> getDetailNews(@Path("id") String id);
}
