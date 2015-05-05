package com.lkw.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lkw.myapplication.R;
import com.lkw.myapplication.adapter.FraPingLunAdapter;
import com.lkw.myapplication.bean.PingLun;
import com.lkw.myapplication.bean.PingOwner;
import com.lkw.myapplication.tools.MyListView;
import com.lkw.myapplication.util.HttpGetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LKW on 2015/5/3.
 */
public class FragmentPingLun extends Fragment {
//    private String url="http://api.zhongchou.cn/comment/getlist?offset=0&count=10&projectID=b3a4dee40de3b7280e4d41e2&v=2";
    public static  String url;
    private ArrayList<PingLun> pinglunList;
    private PingLun pingLun;
    private PingOwner owner;
    private ListView fragment_pinglun_list;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg .what==1) {

                fragment_pinglun_list.setAdapter(new FraPingLunAdapter(getActivity(),pinglunList));
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_pinglun,container,false);

        fragment_pinglun_list = (MyListView) view.findViewById(R.id.fragment_pinglun_list);

        getInfo(url);
        return view;
    }


    public void getInfo(String url){
        HttpGetUtils.getJSONString(url,new HttpGetUtils.OnNetStrDataListener() {
            @Override
            public void successed(String result) {
                try {
                    JSONObject obj1 = new JSONObject(result);
                    pinglunList = new ArrayList<>();
                    JSONArray arr1 = obj1.getJSONArray("data");
                    for (int i = 0; i < arr1.length(); i++) {
                        Log.d("fffffffffffffffffff",arr1.length()+"");
                        pingLun = new PingLun();
                        JSONObject obj2 = arr1.getJSONObject(i);
                        JSONObject obj3 = obj2.getJSONObject("owner");
                        owner = new PingOwner();
                        owner.setHeaderUrl(obj3.getString("headerUrl"));
                        owner.setName(obj3.getString("name"));

                        pingLun.setOwner(owner);

                        pingLun.setTime(obj2.getString("time"));
                        pingLun.setContent(obj2.getString("content"));

//                        List<String> imageUrl=new ArrayList<String>();
//                        JSONArray arr2 = obj2.getJSONArray("imageUrlArray");
//                        for (int j = 0; j < arr2.length(); j++) {
//                            JSONObject obj4 = arr2.getJSONObject(i);
//                            imageUrl.add(obj4.getString("imageUrl"));
//                        }
//                        pingLun.setImageUrlArray(imageUrl);

//                        JSONArray arr2 = obj2.getJSONArray("imageUrlArray");
//                        if (obj2.getJSONArray("imageUrlArray")!=null){
//                            JSONObject obj4 = obj2.getJSONArray("imageUrlArray").getJSONObject(0);
//                            pingLun.setImageUrlArray(obj4.getString("imageUrl"));
//                        }


                        pinglunList.add(pingLun);
                        Log.d("--pinLun--",pingLun.toString());
                        Log.d("---------pinglunList---",pinglunList.toString());

                    }
                    handler.sendEmptyMessage(1);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed() {

            }
        });
    }


}
