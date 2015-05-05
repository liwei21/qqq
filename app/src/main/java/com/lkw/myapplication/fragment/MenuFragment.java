package com.lkw.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lkw.myapplication.BrowsingActivity;
import com.lkw.myapplication.DeliveryAddressActivity;
import com.lkw.myapplication.FriendActivity;
import com.lkw.myapplication.LoginActivity;
import com.lkw.myapplication.MainActivity;
import com.lkw.myapplication.MyIntentActivity;
import com.lkw.myapplication.MyLikeActivity;
import com.lkw.myapplication.R;
import com.lkw.myapplication.UsercenterActivity;
import com.lkw.myapplication.UsercenterInfoActivity;
import com.lkw.myapplication.service.ChatService;

/**
 * Created by LKW on 2015/4/30.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout head_real_login;
    private LinearLayout slid_zhichi;
    private LinearLayout slid_xihuan;
    private LinearLayout slid_faqi;
    private LinearLayout slid_haoyou;
    private LinearLayout slid_dizhi;
    private LinearLayout slid_lishi;
    private LinearLayout slid_shezhi;
    private LinearLayout slid_xinshou;


    public MenuFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_menu, null);

        head_real_login = (RelativeLayout) view.findViewById(R.id.head_real_login);

        head_real_login.setOnClickListener(this);
        slid_zhichi = (LinearLayout) view.findViewById(R.id.slid_zhichi);
        slid_zhichi.setOnClickListener(this);
        slid_xihuan = (LinearLayout) view.findViewById(R.id.slid_xihuan);
        slid_xihuan.setOnClickListener(this);
        slid_faqi = (LinearLayout) view.findViewById(R.id.slid_faqi);
        slid_faqi.setOnClickListener(this);
        slid_haoyou = (LinearLayout) view.findViewById(R.id.slid_haoyou);
        slid_haoyou.setOnClickListener(this);
        slid_dizhi = (LinearLayout) view.findViewById(R.id.slid_dizhi);
        slid_dizhi.setOnClickListener(this);
        slid_lishi = (LinearLayout) view.findViewById(R.id.slid_lishi);
        slid_lishi.setOnClickListener(this);
        slid_shezhi = (LinearLayout) view.findViewById(R.id.slid_shezhi);
        slid_shezhi.setOnClickListener(this);
        slid_xinshou = (LinearLayout) view.findViewById(R.id.slid_xinshou);
        slid_xinshou.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();

        ChatService.ChatController controller = LoginActivity.controller;

        String userJID = LoginActivity.userJID;
        if (userJID == null) {//未登录
            switch (view.getId()) {
                case R.id.head_real_login:

                case R.id.slid_zhichi:

                case R.id.slid_xihuan:

                case R.id.slid_faqi:

                case R.id.slid_haoyou:

                case R.id.slid_dizhi:

                case R.id.slid_lishi:
                    intent.setClass(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    break;
                case R.id.slid_shezhi:
                    intent.setClass(getActivity(), UsercenterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.slid_xinshou:

                    break;
            }
        } else {
            switch (view.getId()) {
                case R.id.head_real_login:
                    intent.setClass(getActivity(), UsercenterInfoActivity.class);
                    startActivity(intent);
                case R.id.slid_zhichi:

                    break;
                case R.id.slid_xihuan:
                    intent.setClass(getActivity(), MyLikeActivity.class);
                    startActivity(intent);
                    break;
                case R.id.slid_faqi:
                    intent.setClass(getActivity(), MyIntentActivity.class);
                    startActivity(intent);
                    break;
                case R.id.slid_haoyou:
                    intent.setClass(getActivity(), FriendActivity.class);
                    startActivity(intent);
                    break;
                case R.id.slid_dizhi:
                    intent.setClass(getActivity(), DeliveryAddressActivity.class);
                    startActivity(intent);
                    break;
                case R.id.slid_lishi:
                    intent.setClass(getActivity(), BrowsingActivity.class);
                    startActivity(intent);
                    break;
                case R.id.slid_shezhi:
                    intent.setClass(getActivity(), UsercenterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.slid_xinshou:

                    break;
            }
        }
    }
}
