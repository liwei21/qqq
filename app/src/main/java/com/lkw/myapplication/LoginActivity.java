package com.lkw.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lkw.myapplication.service.ChatService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import m.framework.utils.UIHandler;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ServiceConnection, PlatformActionListener {

    private ImageView login_head_iv;
    private RadioGroup login_rg;
    private EditText login_username;
    private EditText login_yanzhenma;
    private Button login_rg_btn;
    private TextView login_register;
    private Button login_btn;
    public static ChatService.ChatController controller;
    public static String userJID;
    private LinearLayout login_weixin;
    private LinearLayout login_qq;
    private LinearLayout login_weibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_head_iv = (ImageView) findViewById(R.id.login_head_iv);
        login_head_iv.setOnClickListener(this);

        login_rg = (RadioGroup) findViewById(R.id.login_rg);
        login_rg.setOnCheckedChangeListener(this);

        login_username = (EditText) findViewById(R.id.login_username);
        login_yanzhenma = (EditText) findViewById(R.id.login_yanzhenma);
        login_rg_btn = (Button) findViewById(R.id.login_rg_btn);
        login_register = (TextView) findViewById(R.id.login_register);
        login_register.setOnClickListener(this);
        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);

        login_weixin = (LinearLayout) findViewById(R.id.login_weixin);
        login_qq = (LinearLayout) findViewById(R.id.login_qq);
        login_weibo = (LinearLayout) findViewById(R.id.login_weibo);
        login_weixin.setOnClickListener(this);
        login_weibo.setOnClickListener(this);
        login_qq.setOnClickListener(this);

        Intent intent = new Intent(this, ChatService.class);
        startService(intent);
        bindService(intent, this, BIND_AUTO_CREATE);

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.login_head_iv:
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_register:
                intent.setClass(LoginActivity.this, RegiserActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn:
                String username = login_username.getText().toString();
                String password = login_yanzhenma.getText().toString();

                userJID = controller.login(username, password);
                if (userJID != null) {
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    intent.putExtra("userJID", userJID);
                    startActivity(intent);
                    finish();
                } else {
                    //TODO 登录失败
                    Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_weixin:
                authorize(1);
                break;
            case R.id.login_weibo:
                authorize(0);
                break;
            case R.id.login_qq:
                authorize(2);
                break;
        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.login_rb1) {
            login_username.setHint("请输入手机号，未注册将自动创建");
            login_yanzhenma.setHint("请输入验证码");
            login_rg_btn.setVisibility(View.VISIBLE);
        } else if (i == R.id.login_rb2) {
            login_username.setHint("请输入您的手机号/邮箱");
            login_yanzhenma.setHint("请输入密码");
            login_rg_btn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
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

    private void authorize(int id) {
        ShareSDK.initSDK(this);

        switch (id) {
            case 0:
                Platform weibo = ShareSDK.getPlatform(this, SinaWeibo.NAME);
                weibo.setPlatformActionListener(this);
                weibo.showUser(null);//执行登录，登录后在回调里面获取用户资料
                break;
            case 1:
                Platform weixin = ShareSDK.getPlatform(this, Wechat.NAME);
                weixin.setPlatformActionListener(this);
                weixin.showUser(null);//执行登录，登录后在回调里面获取用户资料
                break;
            case 2:
                Platform qq = ShareSDK.getPlatform(this, QQ.NAME);
                qq.setPlatformActionListener(this);
                qq.showUser(null);//执行登录，登录后在回调里面获取用户资料
                break;

        }

//weibo.showUser(“3189087725”);//获取账号为“3189087725”的资料
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> stringObjectHashMap) {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String, Object>> entries = stringObjectHashMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            builder.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
            Log.d("----------->",""+builder.toString());
        }
        //share.setText(builder.toString());
    }




    @Override
    public void onError(Platform platform, int i, Throwable throwable) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onCancel(Platform platform, int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        controller = (ChatService.ChatController) iBinder;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        controller = null;
    }
}
