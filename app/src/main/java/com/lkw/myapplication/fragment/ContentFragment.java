package com.lkw.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lkw.myapplication.LoginActivity;
import com.lkw.myapplication.MainActivity;
import com.lkw.myapplication.ProgressActivity;
import com.lkw.myapplication.R;
import com.lkw.myapplication.adapter.HomeLVAdapter;
import com.lkw.myapplication.tools.AutoTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Bean.HomeLvData;

/**
 * Created by LKW on 2015/4/30.
 */
public class ContentFragment extends Fragment implements View.OnClickListener {
    private SlidingMenu menu;
    private View view;
    private AutoTextView autotxtv;
    private ViewPager headPager,circleimg_pager;
    private ImageView[] imgvDots = new ImageView[5];
    private ImageView[] imgvTwoDots=new ImageView[2];
    private int count = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    headPager.setCurrentItem(count % 5);
                    count++;
                    handler.sendEmptyMessageDelayed(0, 2000);
                    break;
                case 1:
                    initAutoTextView();
                    break;
                case 2:
                    refresh.onRefreshComplete();
                    break;
                case 3:
                    lvAdapter.notifyDataSetChanged();
                    refresh.onRefreshComplete();
                    break;
            }
        }
    };

    private ListView listView;
    private PullToRefreshScrollView refresh;
    private List<HomeLvData> curList=new ArrayList<>();
    private HomeLVAdapter lvAdapter;
    private ImageView email;
    private ImageView content_frame_back;


    public ContentFragment() {
        this.menu = MainActivity.sm;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_content, container, false);

         view.findViewById(R.id.xinQite).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent =new Intent(ContentFragment.this.getActivity(),ProgressActivity.class);
                 Bundle bundle =new Bundle();
                 bundle.putString("url","http://api.zhongchou.cn/deal/getdetail?projectID=7a450e34f751023b2e817014&sort=sb&v=2");
                 bundle.putString("url1","http://api.zhongchou.cn/deal/getallitems?projectID=7a450e34f751023b2e817014&sort=sb&v=2");
                 bundle.putString("url2","http://api.zhongchou.cn/comment/getlist?offset=0&count=10&projectID=7a450e34f751023b2e817014&sort=sb&v=2");
                 bundle.putString("url3", "http://api.zhongchou.cn/deal/getprocess?projectID=7a450e34f751023b2e817014&sort=sb&v=2");
                 bundle.putInt("count",1);
                 intent.putExtras(bundle);
                 startActivity(intent);
             }
         });
         view.findViewById(R.id.zuiduozhichi).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent =new Intent(ContentFragment.this.getActivity(),ProgressActivity.class);
                 Bundle bundle =new Bundle();
                  bundle.putString("url","http://api.zhongchou.cn/deal/getdetail?projectID=22a2112994f8d2d030eb2efd&sort=sb&v=2");
                  bundle.putString("url1","http://api.zhongchou.cn/deal/getallitems?projectID=22a2112994f8d2d030eb2efd&sort=sb&v=2");
                 bundle.putString("url2","http://api.zhongchou.cn/comment/getlist?offset=0&count=10&projectID=22a2112994f8d2d030eb2efd&sort=sb&v=2");
                  bundle.putString("url3","http://api.zhongchou.cn/deal/getprocess?projectID=22a2112994f8d2d030eb2efd&sort=sb&v=2");
                  bundle.putInt("count",1);
                 intent.putExtras(bundle);
                 startActivity(intent);
             }
         });

        headPager = (ViewPager) view.findViewById(R.id.head_pager);
        circleimg_pager = (ViewPager) view.findViewById(R.id.circleimg_pager);

        content_frame_back = (ImageView) view.findViewById(R.id.content_frame_back);
        content_frame_back.setOnClickListener(this);

        initCircleimg_pagerListener();//实例化中间八个圆图的监听
        circleimg_pager.setAdapter(new HomeCircleImgAdapter(getFragmentManager()));

        initImgvFiveDots();//实例化五个小点
        initImgvTwoDots();//实例化两个个小点

        initHeadPagerListener();//实例化顶部 Viewpager监听
        headPager.setAdapter(new HomeHeadVpAdapter(getFragmentManager()));
        handler.sendEmptyMessage(0);

//        headPager.setOnPageChangeListener(this);
        autotxtv= (AutoTextView) view.findViewById(R.id.autotxtv);
        initAutoTextView();

        refresh= (PullToRefreshScrollView) view.findViewById(R.id.refresh);
        refresh.setMode(PullToRefreshBase.Mode.BOTH);
        initRefreshListener();

        listView = (ListView) view.findViewById(R.id.main_list);
        getNetWorkData();
        lvAdapter=new HomeLVAdapter(curList,getActivity());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getActivity(),ProgressActivity.class);
                HomeLvData data = curList.get(position);
                String IDurl= data.getProjectID();
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
        listView.setAdapter(lvAdapter);
        email = (ImageView) view.findViewById(R.id.email);

        String userJID = LoginActivity.userJID;
        if (userJID!=null){
            email.setVisibility(View.VISIBLE);
            content_frame_back.setImageResource(R.drawable.default_6);
        }else {
            email.setVisibility(View.INVISIBLE);
            content_frame_back.setImageResource(R.drawable.zc_login_name);
        }


        return view;
    }

    private int offset=0;
    private void getNetWorkData() {
        //recommendlist?offset=0&v=2
        String curUrlHou="offset="+offset+"&v=2";
        String curUrl="http://api.zhongchou.cn/deal/recommendlist?"+curUrlHou;
        HttpUtils utils=new HttpUtils();
        utils.send(HttpRequest.HttpMethod.GET,curUrl,new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> objectResponseInfo) {
                try {
                    Log.d("!!!!objectResponseInfo.result-->",objectResponseInfo.result);
                    JSONObject obj=new JSONObject(objectResponseInfo.result);
                    JSONArray dataArr=obj.getJSONArray("data");
                    for(int i=0;i<dataArr.length();i++){
                        JSONObject dataObj=dataArr.getJSONObject(i);
                        HomeLvData dataBean=new HomeLvData();
                        dataBean.setProjectID(dataObj.getString("projectID"));
                        dataBean.setImageUrl(dataObj.getString("imageUrl"));
                        dataBean.setName(dataObj.getString("name"));
                        dataBean.setSummary(dataObj.getString("summary"));
                        dataBean.setFloorPrice(dataObj.getString("floorPrice"));
                        dataBean.setProgress(dataObj.getInt("progress")+"");
                        curList.add(dataBean);

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
        refresh.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                handler.sendEmptyMessageDelayed(2,3000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                getNetWorkData();
                handler.sendEmptyMessage(3);
            }
        });
    }

    private void initHeadPagerListener() {
        headPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imgvDots.length; i++) {
                    if (i == position) {
                        imgvDots[i].setImageResource(R.drawable.greendot);
                    } else {
                        imgvDots[i].setImageResource(R.drawable.blackdot);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initCircleimg_pagerListener() {
        circleimg_pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < imgvTwoDots.length; i++) {
                    if (i == position) {
                        imgvTwoDots[i].setImageResource(R.drawable.greendot);
                    } else {
                        imgvTwoDots[i].setImageResource(R.drawable.blackdot);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private int sCount=0;
    private void initAutoTextView() {
        // TODO Auto-generated method stub
        autotxtv.next();
        sCount++;
        autotxtv.setText(sCount % 2 == 0 ? "  『 “光华戈十” 益行者计划---_李四』上线了!"
                :  "『停下脚步聆听孩子的声音』上线了!");
        handler.sendEmptyMessageDelayed(1,2000);
    }

    private void initImgvFiveDots() {
        ImageView imgvDot1 = (ImageView) view.findViewById(R.id.imgvDot1);
        ImageView imgvDot2 = (ImageView) view.findViewById(R.id.imgvDot2);
        ImageView imgvDot3 = (ImageView) view.findViewById(R.id.imgvDot3);
        ImageView imgvDot4 = (ImageView) view.findViewById(R.id.imgvDot4);
        ImageView imgvDot5 = (ImageView) view.findViewById(R.id.imgvDot5);
        imgvDots[0] = imgvDot1;
        imgvDots[1] = imgvDot2;
        imgvDots[2] = imgvDot3;
        imgvDots[3] = imgvDot4;
        imgvDots[4] = imgvDot5;
    }
    private void initImgvTwoDots() {
        ImageView twoDot1 = (ImageView) view.findViewById(R.id.twoDot1);
        ImageView twoDot2 = (ImageView) view.findViewById(R.id.twoDot2);
        imgvTwoDots[0] = twoDot1;
        imgvTwoDots[1] = twoDot2;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.content_frame_back:
                menu.toggle();
                break;

        }
    }

    class HomeCircleImgAdapter extends FragmentPagerAdapter {

        public HomeCircleImgAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return HomeCircleImgFragment.getFragment(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

    }

    class HomeHeadVpAdapter extends FragmentPagerAdapter {

        public HomeHeadVpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return HomeHeadImgFragment.getFragment(position);
        }

        @Override
        public int getCount() {
            return 5;
        }

    }
}
