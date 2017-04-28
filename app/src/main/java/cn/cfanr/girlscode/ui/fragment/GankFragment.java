package cn.cfanr.girlscode.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.cfanr.girlscode.R;
import cn.cfanr.girlscode.core.BaseFragment;
import cn.cfanr.girlscode.model.Gank;
import cn.cfanr.girlscode.presenter.GankPresenter;
import cn.cfanr.girlscode.ui.binder.GankViewBinder2;
import cn.cfanr.girlscode.ui.binder.GirlGridViewBinder;
import cn.cfanr.girlscode.ui.view.GankView;
import cn.cfanr.girlscode.ui.widget.ptr.PtrLoadMoreListener;
import cn.cfanr.girlscode.ui.widget.ptr.PtrRecyclerView;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author xifan
 * @since 2017/1/23.
 */

public class GankFragment extends BaseFragment implements GankView{
    private static final String TYPE = "data_type";
    private String mType;

    @BindView(R.id.recycler_view_post)
    PtrRecyclerView mRecyclerView;

    private GankPresenter mPresenter;
    private MultiTypeAdapter mAdapter;
    private List<Object> items = new ArrayList<>();
    private int mSize = 15;
    private int mPage = 1;

    public GankFragment(){

    }

    public static GankFragment newInstance(String dataType){
        Bundle bundle = new Bundle();
        bundle.putString(TYPE, dataType);
        GankFragment fragment = new GankFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null){
            savedInstanceState = getArguments();
            mType = savedInstanceState.getString(TYPE);
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.frg_gank;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new GankPresenter(this);
    }

    @Override
    public void initViews(View layoutView) {
        initEvent();
        mAdapter = new MultiTypeAdapter();

        if(TextUtils.equals(mType, "福利")){
            mAdapter.register(Gank.class, new GirlGridViewBinder(getActivity()));
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }else {
            mAdapter.register(Gank.class, new GankViewBinder2(getContext()));
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.loadGankData(mType, mSize, mPage);
    }

    private void initEvent() {

        mRecyclerView.setPtrLoadMoreListener(new PtrLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPage = 1;
                mPresenter.loadGankData(mType, mSize, mPage);
            }

            @Override
            public void onLoadMore() {
                mPage++;
                mPresenter.loadGankData(mType, mSize, mPage);
            }
        });
    }

    @Override
    public void showGankData(List<Gank> gankList) {
        if(mPage == 1){
            items.clear();
        }
        items.addAll(gankList);
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadComplete() {
        mRecyclerView.refreshComplete();
    }

}