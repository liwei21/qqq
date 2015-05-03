package com.lkw.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lkw.myapplication.R;
import com.lkw.myapplication.adapter.FragmentDongAdapter;
import com.lkw.myapplication.bean.DongTai;
import com.lkw.myapplication.util.HttpGetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by LKW on 2015/5/3.
 */
public class FragmentDongTai extends Fragment {
    private String url="http://api.zhongchou.cn/deal/getprocess?projectID=b3a4dee40de3b7280e4d41e2&v=2";
    private ListView dongtai_listview;
    private ArrayList<DongTai> dongTaiList;
    private DongTai dongTai;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                dongtai_listview.setAdapter(new FragmentDongAdapter(getActivity(),dongTaiList));
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_dongtai,container,false);

        dongtai_listview = (ListView) view.findViewById(R.id.dongtai_listview);
getInfo(url);
        return view;
    }


    public void getInfo(String url){
        HttpGetUtils.getJSONString(url,new HttpGetUtils.OnNetStrDataListener() {
            @Override
            public void successed(String result) {
                try {
                    JSONObject obj1 = new JSONObject(result);
                    dongTaiList = new ArrayList<>();
                    JSONArray arr = obj1.getJSONArray("data");
                    for (int i = 0; i < arr.length(); i++) {
                        dongTai = new DongTai();
                        JSONObject obj2 = arr.getJSONObject(i);
                        dongTai.setTitle(obj2.getString("title"));
                        dongTai.setContent(obj2.getString("content"));
                        dongTai.setCreateTime(obj2.getString("createTime"));
                        dongTai.setIcon(obj2.getString("icon"));
                        dongTaiList.add(dongTai);
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
