package cn.cfanr.girlscode.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import butterknife.BindView;
import cn.cfanr.girlscode.R;
import cn.cfanr.girlscode.core.BaseBarActivity;
import cn.cfanr.girlscode.ui.fragment.GankFragment;
import cn.cfanr.girlscode.ui.fragment.HomeFragment;
import cn.cfanr.girlscode.ui.fragment.VideoFragment;

public class MainActivity extends BaseBarActivity {
    private static final int BOTTOM_NAV_HOME_INDEX = 0;
    private static final int BOTTOM_NAV_ANDROID_INDEX = 1;
    private static final int BOTTOM_NAV_IOS_INDEX = 2;
    private static final int BOTTOM_NAV_GIRLS_INDEX = 3;
    private static final int BOTTOM_NAV_VIDEO_INDEX = 4;

    @BindView(R.id.bottom_nav)
    BottomNavigationView mBottomNav;
    @BindView(R.id.frame_layout_container)
    FrameLayout mLayoutContainer;

    private FragmentManager mFragmentManager;
    private Fragment mHomeFragment;
    private Fragment mAndroidFragment;
    private Fragment mIOSFragment;
    private Fragment mGirlsFragment;
    private Fragment mVideoFragment;
    private Fragment mCurFragment;

    private int mCurFragmentIndex = 0;
    private boolean mHomeAdded = false;
    private boolean mAndroidAdded = false;
    private boolean mIOSAdded = false;
    private boolean mGirlsAdded = false;
    private boolean mVideoAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEvent();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        this.setTitle(getResources().getString(R.string.menu_nav_home));
        mHomeFragment = HomeFragment.newInstance();
        mAndroidFragment = GankFragment.newInstance("Android");
        mIOSFragment = GankFragment.newInstance("iOS");
        mGirlsFragment = GankFragment.newInstance("福利");
        mVideoFragment = GankFragment.newInstance("休息视频");
        mCurFragment = mHomeFragment;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_layout_container, mHomeFragment);
        fragmentTransaction.commit();
    }

    protected void initEvent() {
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        switchToFragment(BOTTOM_NAV_HOME_INDEX);
                        break;
                    case R.id.menu_android:
                        switchToFragment(BOTTOM_NAV_ANDROID_INDEX);
                        break;
                    case R.id.menu_ios:
                        switchToFragment(BOTTOM_NAV_IOS_INDEX);
                        break;
                    case R.id.menu_girl:
                        switchToFragment(BOTTOM_NAV_GIRLS_INDEX);
                        break;
                    case R.id.menu_video:
                        switchToFragment(BOTTOM_NAV_VIDEO_INDEX);
                        break;
                    default:
                        break;
                }
                setTitle(item.getTitle());
                return true;
            }
        });

    }

    private void switchToFragment(int index) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        if(mCurFragmentIndex == index){
//            fragmentTransaction.add(R.id.frame_layout_container, getFragment(index));
//            fragmentTransaction.commitAllowingStateLoss();
//            mCurFragmentIndex = index;
//            if(index == BOTTOM_NAV_HOME_INDEX){
//                mHomeAdded = true;
//            }
//            return;
//        }

        fragmentTransaction.hide(mCurFragment);

        switch (index) {
            case BOTTOM_NAV_HOME_INDEX:
                if (mHomeFragment.isAdded()) {
                    fragmentTransaction.show(mHomeFragment);
                } else {
                    fragmentTransaction.add(R.id.frame_layout_container, mHomeFragment);
                    mHomeAdded = true;
                }
                mCurFragment = mHomeFragment;
                break;
            case BOTTOM_NAV_ANDROID_INDEX:
                if (mAndroidFragment.isAdded()) {
                    fragmentTransaction.show(mAndroidFragment);
                } else {
                    fragmentTransaction.add(R.id.frame_layout_container, mAndroidFragment);
                    mAndroidAdded = true;
                }
                mCurFragment = mAndroidFragment;
                break;
            case BOTTOM_NAV_IOS_INDEX:
                if (mIOSFragment.isAdded()) {
                    fragmentTransaction.show(mIOSFragment);
                } else {
                    fragmentTransaction.add(R.id.frame_layout_container, mIOSFragment);
                    mIOSAdded = true;
                }
                mCurFragment = mIOSFragment;
                break;
            case BOTTOM_NAV_GIRLS_INDEX:
                if (mGirlsFragment.isAdded()) {
                    fragmentTransaction.show(mGirlsFragment);
                } else {
                    fragmentTransaction.add(R.id.frame_layout_container, mGirlsFragment);
                    mGirlsAdded = true;
                }
                mCurFragment = mGirlsFragment;
                break;
            case BOTTOM_NAV_VIDEO_INDEX:
                if (mVideoFragment.isAdded()) {
                    fragmentTransaction.show(mVideoFragment);
                } else {
                    fragmentTransaction.add(R.id.frame_layout_container, mVideoFragment);
                    mVideoAdded = true;
                }
                mCurFragment = mVideoFragment;
                break;
            default:
                break;
        }

        fragmentTransaction.commitAllowingStateLoss();
        mCurFragmentIndex = index;
    }

    private Fragment getFragment(int menuItemId) {
        switch (menuItemId){
            case BOTTOM_NAV_HOME_INDEX:
                if(mHomeFragment == null){

                }
                return mHomeFragment;
            case BOTTOM_NAV_ANDROID_INDEX:
                if(mAndroidFragment == null){
                    mAndroidFragment = GankFragment.newInstance("Android");
                }
                return mAndroidFragment;
            case BOTTOM_NAV_IOS_INDEX:
                if(mIOSFragment == null){
                    mIOSFragment = GankFragment.newInstance("");
                }
                return mIOSFragment;
            case BOTTOM_NAV_GIRLS_INDEX:
                if(mGirlsFragment == null){
//                    mGirlsFragment = GirlsFragment.newInstance();
                }
                return mGirlsFragment;
            case BOTTOM_NAV_VIDEO_INDEX:
                if(mVideoFragment == null){
                    mVideoFragment = VideoFragment.newInstance();
                }
                return mVideoFragment;
            default:
                break;
        }
        return null;
    }
}
