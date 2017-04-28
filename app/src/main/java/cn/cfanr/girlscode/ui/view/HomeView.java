package cn.cfanr.girlscode.ui.view;

import cn.cfanr.girlscode.core.mvp.MvpView;
import cn.cfanr.girlscode.model.GankDaily;

/**
 * @author xifan
 * @since 2017/3/26.
 */

public interface HomeView extends MvpView{
    void showHomeData(GankDaily daily);
}
