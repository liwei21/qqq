package com.lkw.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lkw.myapplication.adapter.Spinner_item_Adapter;
import com.lkw.myapplication.adapter.VP2_quanbu_adapter;
import com.lkw.myapplication.bean.Spinnerdata;
import com.lkw.myapplication.bean.VP2_quanbu_beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class VP2_quanbu_Activity extends ActionBarActivity {
     private  String url1;
    private TextView title;
    //数据类
    private VP2_quanbu_beans vp2_quanbu_beans;
    //声明集合存放数据
    public static List<VP2_quanbu_beans> beans_quanbu_List;
    private ListView listView;
    //定义spinner属性
    private Spinner spinner_type;
    private Spinner_item_Adapter adapter;


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
    private TextView back;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp2_quanbu);
        //创建spinner对像
        spinner_type = ((Spinner) findViewById(R.id.spinner_type));
        back = ((TextView) findViewById(R.id.back));
        listView = (ListView) findViewById(R.id.VP2_quanbulistView);
        title =(TextView)findViewById(R.id.text_title);
        Intent intent =this. getIntent();
        Bundle bundle =intent.getExtras();

         url1 = bundle.getString("url");
        count = bundle.getInt("count");
            switch (count){
                case 0:
                    title.setText("全部");
                    break;
                case 1:
                    title.setText("科技");
                    break;
                case 2:
                    title.setText("公益");
                    break;
                case 3:
                    title.setText("出版");
                    break;
                case 4:
                    title.setText("娱乐");
                    break;
                case 5:
                    title.setText("艺术");
                    break;
                case 6:
                    title.setText("农业");
                    break;
                case 7:
                    title.setText("商铺");
                    break;
                case 8:
                    title.setText("其他");
                    break;
                case 9:
                    title.setText("原始会");
                    break;
                case 10:
                    title.setText("众筹筑屋");
                    break;
            }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
        getListInfo();
        getSpinneritem();

    }


    public  void getSpinneritem(){
        adapter=new Spinner_item_Adapter(this, Spinnerdata.quanbu);
        //设置适配器
        spinner_type.setAdapter(adapter);
        //添加适配器的监听
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ImageView image = (ImageView) view.findViewById(R.id.spinner_item_image);
//              int img[] = {R.drawable.umeng_fb_tick_selected, R.drawable.umeng_fb_tick_selected, R.drawable.umeng_fb_tick_selected, R.drawable.umeng_fb_tick_selected, R.drawable.umeng_fb_tick_selected,
//                        R.drawable.umeng_fb_tick_selected, R.drawable.umeng_fb_tick_selected, R.drawable.umeng_fb_tick_selected, R.drawable.umeng_fb_tick_selected, R.drawable.umeng_fb_tick_selected};

//                    if (image!=null){
//                        image.setVisibility(View.INVISIBLE);
//                    }

//                    image.setImageResource(img[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Spinner_item_Adapter adapter1=new Spinner_item_Adapter(this, Spinnerdata.order);

        Spinner spinner_order = (Spinner) findViewById(R.id.spinner_order);
        //设置适配器
        spinner_order.setAdapter(adapter1);
        //添加适配器的监听
//        spinner_order.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        Spinner_item_Adapter adapter2=new Spinner_item_Adapter(this, Spinnerdata.state);

        Spinner spinner_state = (Spinner) findViewById(R.id.spinner_state);
        //设置适配器
        spinner_state.setAdapter(adapter2);
        //添加适配器的监听
        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }






    /*
    * 获取ListView的网络数据x
    * */
    public void getListInfo() {
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, url1, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                String listOfJson = objectResponseInfo.result;
                Log.d("-----resutl---", listOfJson);
                //解析数据
                beans_quanbu_List = new ArrayList<VP2_quanbu_beans>();
                try {
                    JSONObject object1 = new JSONObject(listOfJson);
                    JSONArray arr1 = object1.getJSONArray("data");
                    for (int i = 0; i < arr1.length(); i++) {
                        JSONObject obj2 = arr1.getJSONObject(i);
                        Log.d("---data", "-------" + obj2.toString());
                        VP2_quanbu_beans vp2_bean1 = new VP2_quanbu_beans();
                        vp2_bean1.setFloorPrice(obj2.getString("floorPrice"));
                        vp2_bean1.setImageUrl(obj2.getString("imageUrl"));
                        vp2_bean1.setName(obj2.getString("name"));
                        vp2_bean1.setSummary(obj2.getString("summary"));
                        vp2_bean1.setProgress(obj2.getString("progress"));
                        Log.d("---vp2_bean1", "======" + vp2_bean1);

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
