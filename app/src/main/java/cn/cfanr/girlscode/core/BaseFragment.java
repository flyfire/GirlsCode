package cn.cfanr.girlscode.core;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author xifan
 */
public abstract class BaseFragment extends Fragment {
    public Context mContext;
    private View layoutView;
    private Unbinder unbinder;

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(getLayoutResId(), container, false);
        unbinder = ButterKnife.bind(this, layoutView);
        initPresenter();
        initExtraBase();
        initViews(layoutView);
        return layoutView;
    }

    public abstract int getLayoutResId();

    protected abstract void initPresenter();

    private void initExtraBase() {

    }

    public abstract void initViews(View layoutView);

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
