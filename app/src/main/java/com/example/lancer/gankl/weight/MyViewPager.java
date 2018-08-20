package com.example.lancer.gankl.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * author: Lancer
 * date：2018/8/20
 * des:
 * email:tyk790406977@126.com
 */

public class MyViewPager extends ViewPager {
    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    // 1.禁掉viewpager左右滑动事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    //2.禁掉viewpager左右滑动事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }

}
