package com.example.lancer.gankl.api;

import com.example.lancer.gankl.bean.gank.AllBean;
import com.example.lancer.gankl.bean.gank.AndroidBean;
import com.example.lancer.gankl.bean.gank.BeforeBean;
import com.example.lancer.gankl.bean.gank.IosBean;
import com.example.lancer.gankl.bean.gank.MeiziBean;
import com.example.lancer.gankl.bean.gank.ResBean;
import com.example.lancer.gankl.bean.search.SearchBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * author: Lancer
 * date：2018/8/9
 * des:
 * email:tyk790406977@126.com
 */

public interface GankApi {
    @GET("/api/data/福利/10/{page}")
    Observable<MeiziBean> getMeizi(@Path("page") int page);

    @GET("/api/data/Android/10/{page}")
    Observable<AndroidBean> getGankAndroid(@Path("page") int page);

    @GET("/api/data/iOS/10/{page}")
    Observable<IosBean> getGankIos(@Path("page") int page);

    @GET("/api/data/前端/10/{page}")
    Observable<BeforeBean> getGankBefore(@Path("page") int page);

    @GET("/api/data/拓展资源/10/{page}")
    Observable<ResBean> getGankRes(@Path("page") int page);

    @GET("/api/data/all/10/{page}")
    Observable<AllBean> getGankAll(@Path("page") int page);

  /*  @GET("/api/search/query/{what}/category/all/count/50/page/1")*/
  @GET
  Observable<SearchBean> getGankSearch(@Url String url);
}
