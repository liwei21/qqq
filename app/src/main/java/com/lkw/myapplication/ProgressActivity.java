package com.lkw.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lkw.myapplication.bean.Detail;
import com.lkw.myapplication.fragment.Fragment_Detail;
import com.lkw.myapplication.util.HttpGetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ProgressActivity extends ActionBarActivity implements View.OnClickListener {

    private String url = "http://api.zhongchou.cn/deal/getdetail?projectID=b3a4dee40de3b7280e4d41e2&v=1";

    private Detail detail;
    public static List<Detail> detailList;
    private ViewPager progress_viewpager1;
    private List<String> imageUrlList;

    private int index = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    progress_viewpager1.setAdapter(new MyAdapter(getSupportFragmentManager()));
                    handler.sendEmptyMessageDelayed(1,2500);
                    textSet();
                    break;
                case 1:
                    progress_viewpager1.setCurrentItem(index%imageUrlList.size());
                    handler.sendEmptyMessageDelayed(1, 2500);
                    index++;
                    break;
            }
        }
    };
    private TextView text_ck_text;
    private TextView text_more_text;
    private TextView text_more;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_project_item);

        progress_viewpager1 = (ViewPager) findViewById(R.id.progress_viewpager1);

        text_ck_text = (TextView) findViewById(R.id.text_ck_text);
        text_more_text = (TextView) findViewById(R.id.text_more_text);
        text_more = (TextView) findViewById(R.id.text_more);

        text_more.setOnClickListener(this);


        getInfo(url);
    }




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
                        Log.d("000000000000000000000000000", newImageUrl + "\n");
                        count++;

                    }
                    detail.setImageUrlArray(imageUrlList);


                       detail.setSummary( obj2.getString("summary"));





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

    public void textSet(){
        Detail tail = detailList.get(0);
        String name = tail.getName();
        text_ck_text.setText(name);
        text_more_text.setText(tail.getSummary());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_more:
                Intent intent = new Intent(ProgressActivity.this, ProgressNewActivity.class);
                startActivity(intent);
                break;
        }
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
