package com.lkw.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by LKW on 2015/4/30.
 */
public class MenuFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_menu, null);






        return view;
    }


    public void OnClick(View view){
        switch (view.getId()){
            case R.id.head_login:

                break;
            case R.id.slid_zhichi:

                break;
            case R.id.slid_xihuan:

                break;
            case R.id.slid_faqi:

                break;
            case R.id.slid_haoyou:

                break;
            case R.id.slid_dizhi:

                break;
            case R.id.slid_lishi:

                break;
            case R.id.slid_shezhi:

                break;
            case R.id.slid_xinshou:

                break;
        }
    }
}
