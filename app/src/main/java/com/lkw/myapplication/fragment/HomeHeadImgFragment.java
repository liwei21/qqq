package com.lkw.myapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lkw.myapplication.R;

/**
 * Created by aaa on 15-5-2.
 */
public class HomeHeadImgFragment  extends Fragment {
    private static final String KEY = "position";

    public static HomeHeadImgFragment getFragment(int position) {
        HomeHeadImgFragment fragmentWelcome = new HomeHeadImgFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        fragmentWelcome.setArguments(bundle);
        return fragmentWelcome;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int position = getArguments().getInt(KEY);
        ImageView imgv=new ImageView(getActivity());
        imgv.setScaleType(ImageView.ScaleType.FIT_XY);
        if (position == 0) {
            imgv.setImageResource(R.drawable.topimage1);
        }else if(position==1){
            imgv.setImageResource(R.drawable.topimage2);
        }else if(position==2){
            imgv.setImageResource(R.drawable.topimage3);
        }else if(position==3){
            imgv.setImageResource(R.drawable.topimage4);
        }else if(position==4){
            imgv.setImageResource(R.drawable.topimage5);
        }

        return imgv;
    }

}
