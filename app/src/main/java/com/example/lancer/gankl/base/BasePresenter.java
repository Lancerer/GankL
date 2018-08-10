package com.example.lancer.gankl.base;

/**
 * author: Lancer
 * date：2018/8/9
 * des:
 * email:tyk790406977@126.com
 */

public class BasePresenter<V> {
    private V view;

    protected V getView() {
        return view;
    }

    public void attachView(V view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

}
