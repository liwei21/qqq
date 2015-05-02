package com.lkw.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;

import com.lkw.myapplication.adapter.ProgressNewAdapter;
import com.lkw.myapplication.bean.Detail;
import com.lkw.myapplication.util.HttpGetUtils;
import com.lkw.myapplication.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ProgressNewActivity extends ActionBarActivity {

    private WebView progress_webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_new);
        progress_webView = (WebView) findViewById(R.id.progress_webView);

        progress_webView.getSettings().setJavaScriptEnabled(true);
        progress_webView.setWebChromeClient(new WebChromeClient());
        progress_webView.setWebViewClient(new WebViewClient());

        Detail detail = ProgressActivity.detailList.get(0);
        String url = detail.getDescUrl();

        progress_webView.loadUrl(url);

    }



}
