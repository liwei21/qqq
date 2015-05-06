package com.lkw.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lkw.myapplication.bean.Detail;
import com.lkw.myapplication.bean.Owner;
import com.lkw.myapplication.fragment.FragmentDongTai;
import com.lkw.myapplication.fragment.FragmentPingLun;
import com.lkw.myapplication.fragment.FragmentProActivity;
import com.lkw.myapplication.fragment.Fragment_Detail;
import com.lkw.myapplication.tools.LoadBitmap;
import com.lkw.myapplication.util.HttpGetUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;
import m.framework.utils.UIHandler;


public class ProgressActivity extends ActionBarActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, PlatformActionListener {


//    public static String url = "http://api.zhongchou.cn/deal/getdetail?projectID=b3a4dee40de3b7280e4d41e2&v=2";
//    private String url = "http://api.zhongchou.cn/deal/getdetail?projectID=b3a4dee40de3b7280e4d41e2&v=2";
//        private String url="http://api.zhongchou.cn/deal/getdetail?projectID=7a450e34f751023b2e817014&sort=sb&v=2";
    public static String url;
    private  String url1;
    private    String url2;
    private   String url3;
    private  int count;


    private Detail detail;
    public static List<Detail> detailList;
    private ViewPager progress_viewpager1;
    private List<String> imageUrlList;

    private Owner owner;
    private List<Owner> ownerList;

    private int index = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    progress_viewpager1.setAdapter(new MyAdapter(getSupportFragmentManager()));
                    handler.sendEmptyMessageDelayed(1, 2500);
                    textSet();
                    break;
                case 1:
                    progress_viewpager1.setCurrentItem(index % imageUrlList.size());
                    handler.sendEmptyMessageDelayed(1, 2500);
                    index++;
                    break;
            }
        }
    };
    private TextView text_ck_text;
    private TextView text_more_text;
    private TextView text_more;
    private ImageView author;
    private TextView author_text;
    private TextView author_intro;
    private TextView author_dizhi;
    private ProgressBar progressBar;
    private TextView progressbar_num_text;
    private TextView progress;
    private TextView progress_mubiao_Number;
    private TextView progress_day;
    private TextView image_like_num;
    private TextView image_zhichi_num;
    private TextView progress_day_num;
    private TextView progressbar_num;
    private RadioGroup rg_homepage;
    private TextView share;
    private TextView fanhui;
    public static TextView close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_project_item);

        progress_viewpager1 = (ViewPager) findViewById(R.id.progress_viewpager1);

        text_ck_text = (TextView) findViewById(R.id.text_ck_text);
        text_more_text = (TextView) findViewById(R.id.text_more_text);
        text_more = (TextView) findViewById(R.id.text_more);

        text_more.setOnClickListener(this);

        author = (ImageView) findViewById(R.id.author);
        author_text = (TextView) findViewById(R.id.author_text);
        author_intro = (TextView) findViewById(R.id.author_intro);
        author_dizhi = (TextView) findViewById(R.id.author_dizhi);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressbar_num_text = (TextView) findViewById(R.id.progressbar_num_text);
        progress = (TextView) findViewById(R.id.progress);
        progress_mubiao_Number = (TextView) findViewById(R.id.progress_mubiao_Number);
        progress_day = (TextView) findViewById(R.id.progress_day);
        image_like_num = (TextView) findViewById(R.id.image_like_num);
        image_zhichi_num = (TextView) findViewById(R.id.image_zhichi_num);
        progress_day_num = (TextView) findViewById(R.id.progress_day_num);
        progressbar_num = (TextView) findViewById(R.id.progressbar_num);
        rg_homepage = (RadioGroup) findViewById(R.id.rg_homepage);
        rg_homepage.setOnCheckedChangeListener(this);
        share = (TextView) findViewById(R.id.share);
        share.setOnClickListener(this);

        fanhui = (TextView) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(this);
        close = (TextView) findViewById(R.id.close);
        close.setOnClickListener(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();

        url = bundle.getString("url");
        url1 = bundle.getString("url1");
        url2 = bundle.getString("url2");
        url3 = bundle.getString("url3");
        count = bundle.getInt("count");
        if (count == 1) {
            close.setVisibility(View.INVISIBLE);
        }
        FragmentProActivity.url = url1;
        getInfo(url);
        FragmentProActivity.url=url1;
        getFragment(new FragmentProActivity());
    }


    //注释
    public void getInfo(final String url) {


        HttpGetUtils.getJSONString(url, new HttpGetUtils.OnNetStrDataListener() {
            @Override
            public void successed(String result) {
                try {
//                    Log.d("333333333333333333", "成功");
                    JSONObject obj1 = new JSONObject(result);
                    detailList = new ArrayList<Detail>();
                    JSONObject obj2 = obj1.getJSONObject("data");
                    detail = new Detail();

                    detail.setName(obj2.getString("name"));

                    //定义存放viewpaer图片的集合
                    imageUrlList = new ArrayList<String>();


                    Object array = obj2.get("imageUrlArray");
                    String arrayUrl = array.toString();

                    int count = 0;
                    //取出引号
                    Pattern compile1 = Pattern.compile("\\\"");
                    Matcher matcher1 = compile1.matcher(arrayUrl);
                    String imageUrl2 = matcher1.replaceAll("");
                    //取出逗号
                    Pattern compile2 = Pattern.compile(",");
                    Matcher matcher2 = compile2.matcher(imageUrl2);
                    String imageUrl3 = matcher2.replaceAll("");

                    //取出斜杠
                    Pattern compile = Pattern.compile("\\\\");
                    Matcher matcher = compile.matcher(imageUrl3);
                    String imageUrl = matcher.replaceAll("");

//                    Log.d("22222222222222222222222222222222", imageUrl);
//                    Log.d("77777777777777777777777777", arrayUrl.length() + "");
                    String newImageUrl = "";

                    for (int i = 0; i < imageUrl.length() / 100; i++) {

                        //分割出图片地址
                        int length = newImageUrl.length() * count;
                        newImageUrl = imageUrl.substring((imageUrl.indexOf("h") + length), imageUrl.indexOf("=1") + 2 + length);


                        imageUrlList.add(newImageUrl);
//                        Log.d("000000000000000000000000000", newImageUrl + "\n");
                        count++;

                    }
                    detail.setImageUrlArray(imageUrlList);
                    detail.setSummary(obj2.getString("summary"));
                    detail.setDescUrl(obj2.getString("descUrl"));
                    detail.setProgress(obj2.getInt("progress"));
                    detail.setSupportMoney(obj2.getString("supportMoney"));
                    detail.setTargetMoney(obj2.getString("targetMoney"));
                    detail.setSupportCount(obj2.getString("supportCount"));
                    detail.setDayLeft(obj2.getString("dayLeft"));
                    detail.setLikeCount(obj2.getString("likeCount"));

                    JSONObject obj3 = obj2.getJSONObject("owner");
                    owner = new Owner();
                    owner.setHeaderUrl(obj3.getString("headerUrl"));
                    owner.setName(obj3.getString("name"));
                    owner.setIntro(obj3.getString("intro"));
                    owner.setProvince(obj3.getString("province"));

                    detailList.add(detail);
                    handler.sendEmptyMessage(0);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed() {
//                Log.d("4444444444444444444444444", "失败");
            }
        });
    }

    public void textSet() {
        Detail tail = detailList.get(0);
        String name = tail.getName();
        text_ck_text.setText(name);
        text_more_text.setText(tail.getSummary());
        LoadBitmap.getBitmap(owner.getHeaderUrl(), author, ProgressActivity.this);
        author_text.setText(owner.getName());
        author_intro.setText(owner.getIntro());
        author_dizhi.setText(owner.getProvince());
        progressBar.setProgress(tail.getProgress());
        progressbar_num_text.setText(tail.getProgress() + "%");
        progressbar_num.setText(tail.getProgress() + "%");
        progress.setText("￥" + tail.getSupportMoney());
        progress_mubiao_Number.setText("￥" + tail.getTargetMoney());
        progress_day_num.setText(tail.getDayLeft());
        image_zhichi_num.setText(tail.getSupportCount());
        image_like_num.setText(tail.getLikeCount());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_more:
                Intent intent = new Intent(ProgressActivity.this, ProgressNewActivity.class);

                startActivity(intent);
                break;
            case R.id.share:
                showShare();
                break;
            case R.id.fanhui:
                finish();
                break;
            case R.id.close:
                Intent intent1 = new Intent(ProgressActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
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
                Toast.makeText(ProgressActivity.this, "授权失败", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onCancel(Platform platform, int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ProgressActivity.this, "授权取消", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        switch (i) {
            case R.id.rb_homepage:

                getFragment(new FragmentProActivity());

                FragmentProActivity.url = url1;

                getFragment(new FragmentProActivity());
//                Log.d("hhhhhhhhhhhhhhh","点击");

               FragmentProActivity.url=url1;


                break;
            case R.id.rb_comment:
                FragmentPingLun.url = url2;
                Log.d("---------------url1", url2);

                getFragment(new FragmentPingLun());
                break;
            case R.id.rb_dynamic:
                FragmentDongTai.url = url3;
                Log.d("---------------url1", url3);

                getFragment(new FragmentDongTai());
                break;
        }
    }






        public void getFragment (Fragment fragment){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.re_list, fragment).commit();
        }

        class MyAdapter extends FragmentPagerAdapter {
            public MyAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                return Fragment_Detail.getFragment(position);
            }

            @Override
            public int getCount() {
                return imageUrlList.size();
            }
        }


    }
