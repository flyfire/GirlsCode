package cn.cfanr.girlscode.app;

import android.app.Application;

/**
 * @author xifan
 * @since 2017/3/26.
 */
public class AppController extends Application {

    private static AppController mInstance;

    public static synchronized  AppController getInstance(){
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }
}
