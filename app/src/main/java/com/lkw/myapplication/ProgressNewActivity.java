package com.lkw.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lkw.myapplication.adapter.ProgressNewAdapter;
import com.lkw.myapplication.bean.Detail;
import com.lkw.myapplication.util.HttpGetUtils;
import com.lkw.myapplication.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import m.framework.utils.UIHandler;


public class ProgressNewActivity extends ActionBarActivity implements View.OnClickListener, PlatformActionListener {

    private WebView progress_webView;
    private TextView fanhui;
    private TextView close;
    private TextView share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_new);
        progress_webView = (WebView) findViewById(R.id.progress_webView);

        progress_webView.getSettings().setJavaScriptEnabled(true);
        progress_webView.setWebChromeClient(new WebChromeClient());
        progress_webView.setWebViewClient(new WebViewClient());

        fanhui = (TextView) findViewById(R.id.fanhui);
        close = (TextView) findViewById(R.id.close);
        share = (TextView) findViewById(R.id.share);

        fanhui.setOnClickListener(this);
        close.setOnClickListener(this);
        share.setOnClickListener(this);

        Detail detail = ProgressActivity.detailList.get(0);
        String url = detail.getDescUrl();

        progress_webView.loadUrl(url);

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.fanhui:
                intent.setClass(ProgressNewActivity.this, ProgressActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.close:
                intent.setClass(ProgressNewActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.share:
                showShare();
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        UIHandler.prepare();
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

    private void authorize() {
        ShareSDK.initSDK(this);
        Platform weibo = ShareSDK.getPlatform(this, QQ.NAME);
        weibo.setPlatformActionListener(this);
        weibo.showUser(null);//执行登录，登录后在回调里面获取用户资料
//weibo.showUser(“3189087725”);//获取账号为“3189087725”的资料
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> stringObjectHashMap) {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String, Object>> entries = stringObjectHashMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            builder.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");

        }
        share.setText(builder.toString());
    }


    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ProgressNewActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onCancel(Platform platform, int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ProgressNewActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
