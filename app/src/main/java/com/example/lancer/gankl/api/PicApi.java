package com.example.lancer.gankl.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * author: Lancer
 * date：2018/8/9
 * des:
 * email:tyk790406977@126.com
 */

public interface PicApi {
    @GET("/api/bing_pic")
    Observable<String> getPic();
}
