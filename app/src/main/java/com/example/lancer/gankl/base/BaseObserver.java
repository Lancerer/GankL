package com.example.lancer.gankl.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.lancer.gankl.util.NetWorkUtil;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author: Lancer
 * date：2018/9/6
 * des:
 * email:tyk790406977@126.com
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private Context mContext;
    private ProgressDialog mProgressDialog;
    private Disposable mDisposable;

    public BaseObserver(Context context) {
        mContext = context.getApplicationContext();
    }

    public BaseObserver(Context context, boolean shoeProgress) {
        this.mContext = context.getApplicationContext();
        if (shoeProgress) {
            mProgressDialog = new ProgressDialog(context);
            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    mDisposable.dispose();
                }
            });
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T value) {
        if (value != null) {
            onHandleSuccess(value);
        } else {
            onHandleError();
        }
    }

    private void onHandleError() {
        Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onComplete() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public abstract void onHandleSuccess(T t);

    public class RxSchedulers {
        public <T> ObservableTransformer<T, T> compose(final Context context) {
            return new ObservableTransformer<T, T>() {
                @Override
                public ObservableSource<T> apply(Observable<T> observable) {
                    return observable
                            .subscribeOn(Schedulers.io())
                            .unsubscribeOn(Schedulers.io())
                            .doOnSubscribe(new Consumer<Disposable>() {
                                @Override
                                public void accept(Disposable disposable) throws Exception {
                                    if (!NetWorkUtil.isWifiConnected(context)) {
                                        Toast.makeText(context, "网络异常", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread());
                }
            };
        }
    }
}
/**
 //  ┏┓　　　┏┓
 //┏┛┻━━━┛┻┓
 //┃　　　　　　　┃
 //┃　　　━　　　┃
 //┃　┳┛　┗┳　┃
 //┃　　　　　　　┃
 //┃　　　┻　　　┃
 //┃　　　　　　　┃
 //┗━┓　　　┏━┛
 //   ┃　　　┃   神兽保佑代码无bug
 //   ┃　　　┃   阿弥陀佛
 //   ┃　　　┗━━━┓
 //   ┃　　　　　　　┣┓
 //   ┃　　　　　　　┏┛
 //   ┗┓┓┏━┳┓┏┛
 //     ┃┫┫　┃┫┫
 //     ┗┻┛　┗┻┛
 //
 */

