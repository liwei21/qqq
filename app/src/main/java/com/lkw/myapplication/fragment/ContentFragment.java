package com.lkw.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.lkw.myapplication.R;

/**
 * Created by LKW on 2015/4/30.
 */
public class ContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_content, container, false);
//        ViewPager headPager = (ViewPager) view.findViewById(R.id.head_pager);
//        ViewPager typePager = (ViewPager) view.findViewById(R.id.type_pager);
        ImageView sperial1 = (ImageView) view.findViewById(R.id.special_1);
        ImageView sperial2 = (ImageView) view.findViewById(R.id.special_2);
        ImageView sperial3 = (ImageView) view.findViewById(R.id.special_3);
        ListView lv = (ListView) view.findViewById(R.id.main_list);
        return view;
    }
}
