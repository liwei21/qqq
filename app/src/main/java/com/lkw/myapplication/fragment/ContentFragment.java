package com.lkw.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lkw.myapplication.MainActivity;
import com.lkw.myapplication.R;

/**
 * Created by LKW on 2015/4/30.
 */
public class ContentFragment extends Fragment implements View.OnClickListener {
    private SlidingMenu menu;
    private  View view;
    private static ImageView content_frame_back;

    public ContentFragment() {
        this.menu= MainActivity.sm;
    }

//    public ContentFragment(SlidingMenu menu) {
//        this.menu = menu;
//    }

//    public static ContentFragment getFragment(SlidingMenu menu){
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.layout_content, container, false);
//        ViewPager headPager = (ViewPager) view.findViewById(R.id.head_pager);
//        ViewPager typePager = (ViewPager) view.findViewById(R.id.type_pager);
        ImageView sperial1 = (ImageView) view.findViewById(R.id.special_1);
        ImageView sperial2 = (ImageView) view.findViewById(R.id.special_2);
        ImageView sperial3 = (ImageView) view.findViewById(R.id.special_3);
        ListView lv = (ListView) view.findViewById(R.id.main_list);

         content_frame_back = (ImageView) view.findViewById(R.id.content_frame_back);
        content_frame_back.setOnClickListener(this);



        return view;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.content_frame_back:
                menu.toggle();
                break;
        }
    }
}
