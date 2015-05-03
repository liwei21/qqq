package com.lkw.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lkw.myapplication.MainActivity;
import com.lkw.myapplication.R;
import com.lkw.myapplication.tools.AutoTextView;

/**
 * Created by LKW on 2015/4/30.
 */
public class ContentFragment extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private SlidingMenu menu;
    private View view;
    private AutoTextView autotxtv;
    private static ImageView content_frame_back;
    private ViewPager headPager;
    private ImageView[] imgvDots = new ImageView[5];
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
            }
        }
    };

    public ContentFragment() {
        this.menu = MainActivity.sm;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_content, container, false);
        headPager = (ViewPager) view.findViewById(R.id.head_pager);
        ViewPager circleimg_pager = (ViewPager) view.findViewById(R.id.circleimg_pager);

        ListView lv = (ListView) view.findViewById(R.id.main_list);

        content_frame_back = (ImageView) view.findViewById(R.id.content_frame_back);
        content_frame_back.setOnClickListener(this);

        circleimg_pager.setAdapter(new HomeCircleImgAdapter(getFragmentManager()));

        initImgvDots();//实例化五个小点
        headPager.setOnPageChangeListener(this);
        headPager.setAdapter(new HomeHeadVpAdapter(getFragmentManager()));
        handler.sendEmptyMessage(0);

        autotxtv= (AutoTextView) view.findViewById(R.id.autotxtv);
        initAutoTextView();
        return view;
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

    private void initImgvDots() {
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.content_frame_back:
                menu.toggle();
                break;
        }
    }

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
