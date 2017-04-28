package cn.cfanr.girlscode.ui.view;

import java.util.List;

import cn.cfanr.girlscode.core.mvp.MvpView;
import cn.cfanr.girlscode.model.Gank;

/**
 * @author xifan
 * @since 2017/4/24.
 */

public interface GankView extends MvpView{

    void showGankData(List<Gank> gankList);

    void loadComplete();
}
