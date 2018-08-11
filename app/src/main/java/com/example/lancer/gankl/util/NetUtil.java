package com.example.lancer.gankl.util;

import android.content.Intent;
import android.util.Log;

import com.example.lancer.gankl.MyApplication;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
                .client(mClient)
                .build();
        return mRetrofit;
    }

    public Retrofit getGank() {
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.GANK_BASE_URL)
                .client(mClient)
                .build();
        return mRetrofit;
    }

    public Retrofit getPic() {
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.PIC_BASE_URL)
                .client(mClient)
                .build();
        return mRetrofit;
    }

    /*  这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，
      要是没有网络的话我么就去缓存里面取数据*/
    public class CacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //没网
            if (!NetWorkUtil.isNetWorkAvailable(MyApplication.getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Log.d("GANKL", "no network");
            }
            Response response = chain.proceed(request);
            //有网
            if (NetWorkUtil.isNetWorkAvailable(MyApplication.getContext())) {
                String cacheControl = request.cacheControl().toString();
                return response.newBuilder()
                        //这里设置的为0就是说不进行缓存，我们也可以设置缓存时间
                        .header("Cache-Control", "public, max-age=" + 0)
                        .removeHeader("Pragma")
                        .build();
            } else {
                int maxTime = 4 * 24 * 60 * 60;
                return response.newBuilder()
                        //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxTime)
                        .removeHeader("Pragma")
                        .build();

            }

        }
    }

    File cacheFile = new File(MyApplication.getInstance().getCacheDir(), "caheData");
    Cache cache = new Cache(cacheFile, 1024 * 10);//google建议放到这里
    OkHttpClient mClient = new OkHttpClient.Builder()
            .retryOnConnectionFailure(true)//连接失败后是否重新连接
            .connectTimeout(15, TimeUnit.SECONDS)//超时时间15S
            .addInterceptor(new CacheInterceptor())
            .addNetworkInterceptor(new CacheInterceptor())
            .cache(cache)
            .build();

}
