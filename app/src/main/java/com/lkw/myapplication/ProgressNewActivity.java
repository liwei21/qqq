package com.lkw.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    private String url = "http://api.zhongchou.cn/deal/getdetail?projectID=b3a4dee40de3b7280e4d41e2&v=1";

    private Detail detail;
    public static List<Detail> detailList;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            progress_new_listview.setAdapter(new ProgressNewAdapter(ProgressNewActivity.this,detailList));
        }
    };
    private ListView progress_new_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_new);
        progress_new_listview = (ListView) findViewById(R.id.progress_new_listview);
        getInfo(url);
    }

    public void getInfo(String url){

        HttpGetUtils.getJSONString(url,new HttpGetUtils.OnNetStrDataListener() {
            @Override
            public void successed(String result) {
                try {
                    JSONObject obj1 = new JSONObject(result);

                    detailList = new ArrayList<Detail>();
                    JSONObject obj2 = obj1.getJSONObject("data");

                    JSONArray arr = obj2.getJSONArray("description");
                    for (int i = 0; i < arr.length(); i++) {
                        detail = new Detail();
                        JSONObject obj3 = arr.getJSONObject(i);
                        detail.setContent(obj3.getString("content"));
                        detail.setType(obj3.getInt("type"));
                        detailList.add(detail);
                    }

                    handler.sendEmptyMessage(1);




                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void failed() {
                Log.d("4444444444444444444444444", "失败");
            }
        });

    }



}
