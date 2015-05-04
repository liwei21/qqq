package com.lkw.myapplication.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.lkw.myapplication.R;
import com.lkw.myapplication.VP2_quanbu_Activity;

/**
 * Created by aaa on 15-5-2.
 */
public class HomeCircleImgFragment extends Fragment {
    private static final String KEY = "position";
    private ImageButton quanbu,keji,gongyi,chuban,yule,yishu,nongye,shangpu;
    private ImageView qita;
    private ImageView yuanshihui;
    private ImageView zhouchouzhuwu;

    public static HomeCircleImgFragment getFragment(int position) {
        HomeCircleImgFragment fragmentWelcome = new HomeCircleImgFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY, position);
        fragmentWelcome.setArguments(bundle);
        return fragmentWelcome;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int position = getArguments().getInt(KEY);
        if (position == 0) {
            View view = inflater.inflate(R.layout.layout_content_vpfragment_one, container, false);

            quanbu = ((ImageButton) view.findViewById(R.id.quanbu));
            keji = ((ImageButton) view.findViewById(R.id.keji));
            gongyi = ((ImageButton) view.findViewById(R.id.gongyi));
            chuban = ((ImageButton) view.findViewById(R.id.chuban));
            yule = ((ImageButton) view.findViewById(R.id.yule));
            yishu = ((ImageButton) view.findViewById(R.id.yishu));
            nongye = ((ImageButton) view.findViewById(R.id.nongye));
            shangpu = ((ImageButton) view.findViewById(R.id.shangpu));
           View.OnClickListener oc1 = new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent =new Intent(HomeCircleImgFragment.this.getActivity(),VP2_quanbu_Activity.class);
                   Bundle bundle = new Bundle();                           //创建Bundle对象

                   switch (v.getId()){
                       case R.id.quanbu:
                           bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=797484e981d914c7d7c9e72f&sort=sb&v=1");
                           bundle.putInt("count",0);
                           intent.putExtras(bundle);
                           startActivity(intent);
                           break;
                       case R.id.keji:
                           bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=1caaa00704ea0af53adad706&sort=sb&v=1");     //装入数据
                           bundle.putInt("count",1);
                           intent.putExtras(bundle);
                           startActivity(intent);
                           break;
                       case R.id.gongyi:
                           bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=fb9650095e7657e96c79de2f&sort=sb&v=1");     //装入数据
                           bundle.putInt("count",2);
                           intent.putExtras(bundle);
                           startActivity(intent);
                           break;
                       case R.id.chuban:
                           bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=1caaa00704cd0af53adad72f&sort=sb&v=1");
                           bundle.putInt("count",3);
                           intent.putExtras(bundle);
                           startActivity(intent);
                           break;
                       case R.id.yule:
                           bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=cd5627cc1bd23adaf6f7d406&sort=sb&v=1");
                           bundle.putInt("count",4);
                           intent.putExtras(bundle);
                           startActivity(intent);
                           break;
                       case R.id.yishu:
                           bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=653b96c77508497657e9d12f&sort=sb&v=1");
                           bundle.putInt("count",5);
                           intent.putExtras(bundle);
                           startActivity(intent);
                           break;
                       case R.id.nongye:
                           bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=48b2c9e842298511940adb2f&sort=sb&v=1");
                           bundle.putInt("count",6);
                           intent.putExtras(bundle);
                           startActivity(intent);
                           break;
                       case R.id.shangpu:
                           bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=54a509dbe5d03b6ba5c6d206&sort=sb&v=1");
                           bundle.putInt("count",7);
                           intent.putExtras(bundle);
                           startActivity(intent);
                           break;

                   }
               }
           };
            quanbu.setOnClickListener(oc1);
            keji.setOnClickListener(oc1);
            gongyi.setOnClickListener(oc1);
            chuban.setOnClickListener(oc1);
            yule.setOnClickListener(oc1);
            yishu.setOnClickListener(oc1);
            nongye.setOnClickListener(oc1);
            shangpu.setOnClickListener(oc1);
            return view;
        } else {
            View view = inflater.inflate(R.layout.layout_content_vpfragment_two, container, false);
            qita = ((ImageView) view.findViewById(R.id.qita));
            yuanshihui = ((ImageView) view.findViewById(R.id.yuanshihui));
            zhouchouzhuwu = ((ImageView) view.findViewById(R.id.zhongchouzhuwu));
            View.OnClickListener oc2=new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(HomeCircleImgFragment.this.getActivity(),VP2_quanbu_Activity.class);
                    Bundle bundle = new Bundle();
                    switch (v.getId()){
                        case R.id.qita:
                            bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=4a9d39f428daf6f73b6bd52f&sort=sb&v=1");
                            bundle.putInt("count",8);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                        case  R.id.yuanshihui:
                            bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=4a9d39f428daf6f73b6bd52f&sort=sb&v=1");
                            bundle.putInt("count",9);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                        case R.id.zhongchouzhuwu:
                            bundle.putString("url","http://api.zhongchou.cn/deal/list?offset=0&categoryID=653b96c7752f497657e9d106&sort=sb&v=1");
                            bundle.putInt("count",10);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            break;
                    }
                }
            };
            qita.setOnClickListener(oc2);
            yuanshihui.setOnClickListener(oc2);
            zhouchouzhuwu.setOnClickListener(oc2);
            return view;
        }

    }



}
