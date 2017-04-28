package cn.cfanr.girlscode.presenter;

import java.util.Calendar;
import java.util.Date;

import cn.cfanr.girlscode.app.RetrofitManager;
import cn.cfanr.girlscode.core.mvp.BasePresenter;
import cn.cfanr.girlscode.model.GankDaily;
import cn.cfanr.girlscode.ui.view.HomeView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author xifan
 * @since 2017/3/26.
 */

public class HomePresenter extends BasePresenter<HomeView>{
    private static final int DAY_OF_MILLISECOND = 24*60*60*1000;

    public HomePresenter(HomeView mvpView) {
        super(mvpView);
    }

    public void loadHomeData(final Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        RetrofitManager.builder().loadGankDaily(year, month, day)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<GankDaily>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(GankDaily gankDaily) {
//                        if(gankDaily.results == null){
//                            loadHomeData(new Date(date.getTime()-DAY_OF_MILLISECOND));
//                        } else {
//                            mMvpView.showHomeData(gankDaily);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
                .subscribe(new Consumer<GankDaily>() {
                    @Override
                    public void accept(GankDaily gankDaily) throws Exception {
                        if(gankDaily.category.size() == 0){
                            loadHomeData(new Date(date.getTime() - DAY_OF_MILLISECOND));
                        } else {
                            mMvpView.showHomeData(gankDaily);
                        }
                    }
                });
    }
}
