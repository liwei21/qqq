package com.lkw.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
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

import Bean.HomeLvData;


public class VP2_quanbu_Activity extends ActionBarActivity {
    private VP2_quanbu_adapter vp2Adapter;
     private  String url1;
    private TextView title;
    //数据类
    private VP2_quanbu_beans vp2_quanbu_beans;
    //声明集合存放数据
    private  List<VP2_quanbu_beans>  beans_quanbu_List = new ArrayList<VP2_quanbu_beans>();
    private ListView listView;
    private PullToRefreshScrollView scroll;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    scroll.onRefreshComplete();
                    break;
                case 1:
                    vp2Adapter.notifyDataSetChanged();
                    scroll.onRefreshComplete();
                    break;
//                case 2:
////                    vp2Adapter.notifyDataSetChanged();
//                    listView.setAdapter(vp2Adapter);
//                    break;
            }


        }
    };
    private TextView back;
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vp2_quanbu);
        //创建spinner对像
//        spinner_type = ((Spinner) findViewById(R.id.spinner_type));
        back = ((TextView) findViewById(R.id.back));
        title =(TextView)findViewById(R.id.Vp2_title);

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

        scroll= (PullToRefreshScrollView) findViewById(R.id.Scroll);
        scroll.setMode(PullToRefreshBase.Mode.BOTH);
        initRefreshListener();
        listView = (ListView) findViewById(R.id.VP2_quanbulistView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(VP2_quanbu_Activity.this,ProgressActivity.class);
                VP2_quanbu_beans vp2_quanbu_beans = beans_quanbu_List.get(position);
                String IDurl= vp2_quanbu_beans.getProjectID();
                String   url="http://api.zhongchou.cn/deal/getdetail?projectID="+IDurl+"&v=2";
                String url1="http://api.zhongchou.cn/deal/getallitems?projectID="+IDurl+"&v=2";
                String url2="http://api.zhongchou.cn/comment/getlist?offset=0&count=10&projectID="+IDurl+"&v=2";
                String url3="http://api.zhongchou.cn/deal/getprocess?projectID="+IDurl+"&v=2";
                Bundle bundle =new Bundle();
                bundle.putString("url",url);
                bundle.putString("url1",url1);
                bundle.putString("url2",url2);
                bundle.putString("url3",url3);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        getListInfo();
        vp2Adapter = new VP2_quanbu_adapter(VP2_quanbu_Activity.this,beans_quanbu_List);
        listView.setAdapter(vp2Adapter);
//        vp2Adapter.notifyDataSetChanged();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });
    }

    /*
    * 获取ListView的网络数据x
    * */
       private int offset=0;
     public void getListInfo() {
        String curUrlHou1="offset="+offset;

        String curUrl="http://api.zhongchou.cn/deal/list?"+curUrlHou1+url1;
         System.out.println("+++++++++++cururl"+curUrl);
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET, curUrl, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                String listOfJson = objectResponseInfo.result;
                Log.d("-----resutl---", listOfJson);
                //解析数据

                try {
                    JSONObject object1 = new JSONObject(listOfJson);
                    JSONArray arr1 = object1.getJSONArray("data");
                    for (int i = 0; i < arr1.length(); i++) {
                        JSONObject obj2 = arr1.getJSONObject(i);
                        Log.d("---data", "-------" + obj2.toString());
                        VP2_quanbu_beans vp2_bean1 = new VP2_quanbu_beans();
                        vp2_bean1.setProjectID(obj2.getString("projectID"));
                        vp2_bean1.setFloorPrice(obj2.getString("floorPrice"));
                        vp2_bean1.setImageUrl(obj2.getString("imageUrl"));
                        vp2_bean1.setName(obj2.getString("name"));
                        vp2_bean1.setSummary(obj2.getString("summary"));
                        vp2_bean1.setProgress(obj2.getString("progress"));

                        Log.d("---vp2_bean1", "======" + vp2_bean1);

                        beans_quanbu_List.add(vp2_bean1);
//                        handler.sendEmptyMessage(2);
//                        listView.setAdapter(vp2Adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                offset+=10;
            }

            @Override
            public void onFailure(HttpException e, String s) {

            }
        });

    }


    private void initRefreshListener() {
        scroll.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                handler.sendEmptyMessageDelayed(0,3000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                getListInfo();
                handler.sendEmptyMessage(1);
            }
        });
    }


}
