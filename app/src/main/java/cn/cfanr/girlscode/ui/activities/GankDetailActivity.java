package cn.cfanr.girlscode.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import cn.cfanr.girlscode.R;
import cn.cfanr.girlscode.core.BaseBarActivity;

public class GankDetailActivity extends BaseBarActivity {
    @BindView(R.id.gank_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.gank_web_view_gank)
    WebView mWebView;

    private WeakReference<Activity> mActivityRef;
    private String webUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_gank_detail;
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        mActivityRef = new WeakReference<Activity>(this);
        webUrl = getIntent().getExtras().getString("webUrl");

        mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
        mWebView.getSettings().setAllowFileAccess(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setAppCachePath(getActivity().getApplicationContext().getDir("cache", 0).getPath());
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());

        mWebView.loadUrl(webUrl);
    }

    private class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            Activity activity = mActivityRef.get();
            if (newProgress == 100) {
                ((GankDetailActivity) activity).mProgressBar.setVisibility(View.GONE);
            } else {
                if (View.INVISIBLE == ((GankDetailActivity) activity).mProgressBar.getVisibility()) {
                    ((GankDetailActivity) activity).mProgressBar.setVisibility(View.VISIBLE);
                }
                ((GankDetailActivity) activity).mProgressBar.setProgress(newProgress);
            }
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            mActivityRef.get().setTitle(title);
        }

    }

}