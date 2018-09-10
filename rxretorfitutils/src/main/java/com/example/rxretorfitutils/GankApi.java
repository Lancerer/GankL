package com.example.rxretorfitutils;



import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * author: Lancer
 * date：2018/8/9
 * des:
 * email:tyk790406977@126.com
 */

public interface GankApi {
    @GET("/api/data/福利/10/{page}")
    Observable<BaseBean<MeiziBean>> getMeizi(@Path("page") int page);

    @GET("/api/bing_pic")
    Observable<BaseBean<String>> getPic();

}
