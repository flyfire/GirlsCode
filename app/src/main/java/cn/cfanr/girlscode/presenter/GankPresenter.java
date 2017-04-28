package cn.cfanr.girlscode.presenter;

import android.util.Log;

import cn.cfanr.girlscode.app.RetrofitManager;
import cn.cfanr.girlscode.core.mvp.BasePresenter;
import cn.cfanr.girlscode.model.GankIO;
import cn.cfanr.girlscode.ui.view.GankView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xifan
 * @since 2017/4/24.
 */

public class GankPresenter extends BasePresenter<GankView> {

    public GankPresenter(GankView mvpView) {
        super(mvpView);
    }

    public void loadGankData(String type, int size, int page){
        RetrofitManager.builder().loadGankData(type, size, page)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankIO>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(GankIO gankIO) {
                        mMvpView.loadComplete();
                        Log.e("gank", "onNext");
                        if(gankIO != null && !gankIO.error) {
                            mMvpView.showGankData(gankIO.getResults());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("gank", "onError");
                        mMvpView.loadComplete();
                    }

                    @Override
                    public void onComplete() {
                        Log.e("gank", "onComplete");
                        mMvpView.loadComplete();
                    }
                });
    }

}
