package com.lkw.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lkw.myapplication.LoginActivity;
import com.lkw.myapplication.R;

/**
 * Created by LKW on 2015/4/30.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout head_real_login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_menu, null);

        head_real_login = (RelativeLayout) view.findViewById(R.id.head_real_login);

        head_real_login.setOnClickListener(this);


        return view;
    }




    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.head_real_login:
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
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
