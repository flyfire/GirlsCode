package cn.cfanr.girlscode.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;


/**
 * @author xifan
 * @time 2016/5/4
 * @desc
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBaseContentView(getLayoutResId());
        ButterKnife.bind(this);
        initPresenter();
        initExtraBase();
        initView();
    }

    protected void setBaseContentView(@LayoutRes int layoutResId){
        setContentView(layoutResId);
    }

    protected abstract int getLayoutResId();

    protected abstract void initPresenter();

    private void initExtraBase(){
    }

    protected abstract void initView();

    protected Activity getActivity(){
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}