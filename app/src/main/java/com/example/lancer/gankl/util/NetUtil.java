package com.example.lancer.gankl.util;

import android.content.Intent;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: Lancer
 * date：2018/8/9
 * des:网络工具类
 * email:tyk790406977@126.com
 */

public class NetUtil {

    private Retrofit mRetrofit;

    private NetUtil() {
    }

    private static NetUtil Instance;

    public static NetUtil getInstance() {
        if (Instance == null) {
            synchronized (NetUtil.class) {
                if (Instance == null) {
                    Instance = new NetUtil();
                }
            }
        }
        return Instance;
    }

    public Retrofit getZhihu() {
         mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.ZHIHU_BASE_URL)
                .build();
        return mRetrofit;
    }

    public Retrofit getGank() {
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.GANK_BASE_URL)
                .build();
        return mRetrofit;
    }
    public Retrofit getPic(){
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.PIC_BASE_URL)
                .build();
        return mRetrofit;
    }


}
