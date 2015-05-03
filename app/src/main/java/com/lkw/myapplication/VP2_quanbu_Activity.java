package com.lkw.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Spinner;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lkw.myapplication.adapter.VP2_quanbu_adapter;
import com.lkw.myapplication.bean.VP2_quanbu_beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class VP2_quanbu_Activity extends ActionBarActivity {
    private String url = "http://api.zhongchou.cn/deal/list?offset=0&categoryID=797484e981d914c7d7c9e72f&sort=sb&v=1";
    //数据类
    private VP2_quanbu_beans vp2_quanbu_beans;
    //声明集合存放数据
    public static List<VP2_quanbu_beans> beans_quanbu_List;
    private ListView listView;

    private Spinner spinner_type;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
            listView.setAdapter(new VP2_quanbu_adapter(VP2_quanbu_Activity.this,beans_quanbu_List));
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp2_quanbu);
        //创建spinner对像
        spinner_type = ((Spinner) findViewById(R.id.spinner_type));

        listView = (ListView) findViewById(R.id.VP2_quanbulistView);
        getListInfo();

    }

    /*
    * 获取ListView的网络数据x
    * */
    public void getListInfo() {
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET,url,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                String listOfJson = objectResponseInfo.result;
                Log.d("-----resutl---",listOfJson);
                //解析数据
                beans_quanbu_List=new ArrayList<VP2_quanbu_beans>();
                try {
                    JSONObject object1=new JSONObject(listOfJson);
                    JSONArray arr1 = object1.getJSONArray("data");
                    for (int i=0;i<arr1.length();i++){
                        JSONObject obj2 = arr1.getJSONObject(i);
                        Log.d("---data","-------"+obj2.toString());
                        VP2_quanbu_beans vp2_bean1=new VP2_quanbu_beans();
                        vp2_bean1.setFloorPrice(obj2.getString("floorPrice"));
                        vp2_bean1.setImageUrl(obj2.getString("imageUrl"));
                        vp2_bean1.setName(obj2.getString("name"));
                        vp2_bean1.setSummary(obj2.getString("summary"));
                        vp2_bean1.setProgress(obj2.getString("progress"));
                        Log.d("---vp2_bean1","======"+vp2_bean1);

                        beans_quanbu_List.add(vp2_bean1);
                        handler.sendEmptyMessage(0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

//        HttpGetUtils.getJSONString(url, new HttpGetUtils.OnNetStrDataListener() {
//
//            @Override
//            public void successed(String result) {
//                Log.d("111111111111", "222222222222");
//                //解析数据
//                try {
//                    Log.d("=======================", "哈哈哈哈");
//                    JSONObject object1 = new JSONObject(result);
//                    beans_quanbu_List = new ArrayList<VP2_quanbu_beans>();
//                    JSONArray arr1 = object1.getJSONArray("data");
//                    for (int i = 0; i < arr1.length(); i++) {
//                        JSONObject obj2 = arr1.getJSONObject(i);
//                        Log.d("---data", "-------" + obj2.toString());
//                        VP2_quanbu_beans vp2_bean1 = new VP2_quanbu_beans();
//                        vp2_bean1.setFloorPrice(obj2.getString("floorPrice"));
//                        vp2_bean1.setImageUrl(obj2.getString("imageUrl"));
//                        vp2_bean1.setName(obj2.getString("name"));
//                        vp2_bean1.setSummary(obj2.getString("summary"));
//                        Log.d("---vp2_bean1", "======" + vp2_bean1);
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void failed() {
//
//            }
//        });
    }




}
