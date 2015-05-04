package com.lkw.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.lkw.myapplication.R;

/**
 * Created by aaa on 15-5-2.
 */
public class HomeCircleImgFragment extends Fragment {
    private static final String KEY = "position";

    public static HomeCircleImgFragment getFragment(int position) {
        HomeCircleImgFragment fragmentWelcome = new HomeCircleImgFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        fragmentWelcome.setArguments(bundle);
        return fragmentWelcome;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int position = getArguments().getInt(KEY);
        if (position == 0) {
            View view = inflater.inflate(R.layout.layout_content_vpfragment_one, container, false);
            return view;
        } else{
            View view = inflater.inflate(R.layout.layout_content_vpfragment_two, container, false);
            return view;
        }

    }

}
