package com.example.lancer.gankl.mvp.view;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * author: Lancer
 * date：2018/9/28
 * des:
 * email:tyk790406977@126.com
 */
public interface SerachView {
    ImageView getIvBack();

    ImageView getError();

    ListView getLv();

    EditText getEt();
}
