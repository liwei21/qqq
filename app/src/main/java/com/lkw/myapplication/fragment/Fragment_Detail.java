package com.lkw.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lkw.myapplication.ProgressActivity;
import com.lkw.myapplication.R;
import com.lkw.myapplication.bean.Detail;
import com.lkw.myapplication.tools.LoadBitmap;

import java.util.List;

/**
 * Created by LKW on 2015/5/1.
 */
public class Fragment_Detail extends Fragment {

    private static final String KEY="position";
    private ImageView fragment_detail_iv;


    public static Fragment_Detail getFragment(int position){
        Fragment_Detail fragment = new Fragment_Detail();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY,position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ImageView imageView = new ImageView(getActivity());
        int position = getArguments().getInt(KEY);
        Detail detail = ProgressActivity.detailList.get(0);

        Log.v("debug",detail.toString());
        List<String> urlArray =  detail.getImageUrlArray();
        String url = urlArray.get(position);


            LoadBitmap.getBitmap(url,imageView,getActivity());


        return imageView;
    }
}
