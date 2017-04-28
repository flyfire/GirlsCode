package cn.cfanr.girlscode.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import cn.cfanr.girlscode.R;
import cn.cfanr.girlscode.core.BaseFragment;
import cn.cfanr.girlscode.model.Gank;
import cn.cfanr.girlscode.model.GankDaily;
import cn.cfanr.girlscode.model.GirlGank;
import cn.cfanr.girlscode.ui.binder.GirlViewBinder;
import cn.cfanr.girlscode.model.Title;
import cn.cfanr.girlscode.presenter.HomePresenter;
import cn.cfanr.girlscode.ui.binder.GankViewBinder;
import cn.cfanr.girlscode.ui.binder.TitleViewBinder;
import cn.cfanr.girlscode.ui.view.HomeView;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * @author xifan
 * @since 2017/3/26.
 */
public class HomeFragment extends BaseFragment implements HomeView{
    private HomePresenter mPresenter;
    @BindView(R.id.rv_home)
    RecyclerView mRecyclerView;

    private MultiTypeAdapter mAdapter;
    private List<Object> items = new ArrayList<>();

    public HomeFragment(){

    }

    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.frg_home;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter(this);
    }

    @Override
    public void initViews(View layoutView) {
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(GirlGank.class, new GirlViewBinder(getActivity()));
        mAdapter.register(Title.class, new TitleViewBinder());
        mAdapter.register(Gank.class, new GankViewBinder(getActivity()));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.loadHomeData(new Date(System.currentTimeMillis()));
    }

    @Override
    public void showHomeData(GankDaily daily) {
        items.addAll(daily.results.girlsList);
        if(daily.results.androidList != null) {
            items.add(new Title("Android"));
            items.addAll(daily.results.androidList);
        }
        if(daily.results.iOSList != null) {
            items.add(new Title("iOS"));
            items.addAll(daily.results.iOSList);
        }
        if(daily.results.sourceList != null) {
            items.add(new Title("扩展资源"));
            items.addAll(daily.results.sourceList);
        }
        if(daily.results.recommendList != null) {
            items.add(new Title("瞎推荐"));
            items.addAll(daily.results.recommendList);
        }
        if(daily.results.restList != null) {
            items.add(new Title("休息视频"));
            items.addAll(daily.results.restList);
        }
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
    }

}
