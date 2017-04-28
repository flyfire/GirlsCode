package cn.cfanr.girlscode.ui.fragment;

import android.view.View;

import cn.cfanr.girlscode.R;
import cn.cfanr.girlscode.core.BaseFragment;

/**
 * Created by cfanr on 2017/1/23.
 */

public class VideoFragment extends BaseFragment {

    public VideoFragment(){

    }

    public static VideoFragment newInstance(){
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.frg_video;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void initViews(View layoutView) {

    }
}
