package com.lkw.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lkw.myapplication.ProgressActivity;
import com.lkw.myapplication.R;

/**
 * Created by aaa on 15-5-2.
 */
public class HomeHeadImgFragment  extends Fragment implements View.OnClickListener {
    private static final String KEY = "position";
    private ImageView imgv;
    private Bundle bundle;

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
        imgv = new ImageView(getActivity());
        imgv.setScaleType(ImageView.ScaleType.FIT_XY);
        if (position == 0) {
            imgv.setImageResource(R.drawable.topimage1);
            bundle=new Bundle();
            bundle.putString("url","http://api.zhongchou.cn/deal/getdetail?projectID=b3a4dee40de3b7280e4d41e2&v=2");
            bundle.putString("url1","http://api.zhongchou.cn/deal/getallitems?projectID=b3a4dee40de3b7280e4d41e2&v=2");
            bundle.putString("url2","http://api.zhongchou.cn/comment/getlist?offset=0&count=10&projectID=b3a4dee40de3b7280e4d41e2&v=2");
            bundle.putString("url3","http://api.zhongchou.cn/deal/getprocess?projectID=b3a4dee40de3b7280e4d41e2&v=2");

            bundle.putInt("count",1);
        }else if(position==1){
            imgv.setImageResource(R.drawable.topimage2);
            bundle=new Bundle();
            bundle.putString("url", "http://api.zhongchou.cn/deal/getdetail?projectID=c70210e2b6e8a93a012afac7&v=2");
            bundle.putString("url1", "http://api.zhongchou.cn/deal/getallitems?projectID=c70210e2b6e8a93a012afac7&v=2");
            bundle.putString("url2", "http://api.zhongchou.cn/comment/getlist?offset=0&count=10&projectID=c70210e2b6e8a93a012afac7&v=2");
            bundle.putString("url3","http://api.zhongchou.cn/deal/getprocess?projectID=c70210e2b6e8a93a012afac7&v=2");
            bundle.putInt("count", 1);

        }else if(position==2){
            imgv.setImageResource(R.drawable.topimage3);
            bundle=new Bundle();
            bundle.putString("url","http://api.zhongchou.cn/deal/getdetail?projectID=6a15daddef9ebc30fadac3dc&v=2");
            bundle.putString("url1","http://api.zhongchou.cn/deal/getallitems?projectID=6a15daddef9ebc30fadac3dc&v=2");
            bundle.putString("url2","http://api.zhongchou.cn/comment/getlist?offset=0&count=10&projectID=6a15daddef9ebc30fadac3dc&v=2");
            bundle.putString("url3","http://api.zhongchou.cn/deal/getprocess?projectID=6a15daddef9ebc30fadac3dc&v=2");
            bundle.putInt("count",1);
        }else if(position==3){
            imgv.setImageResource(R.drawable.topimage4);
            bundle=new Bundle();
            bundle.putString("url","http://api.zhongchou.cn/deal/getdetail?projectID=e5811f00da76492e9d097902&v=2");
            bundle.putString("url1","http://api.zhongchou.cn/deal/getallitems?projectID=e5811f00da76492e9d097902&v=2");
            bundle.putString("url2","http://api.zhongchou.cn/comment/getlist?offset=0&count=10&projectID=e5811f00da76492e9d097902&v=2");
            bundle.putString("url3","http://api.zhongchou.cn/deal/getprocess?projectID=e5811f00da76492e9d097902v=2");
            bundle.putInt("count",1);
        }else if(position==4){
            imgv.setImageResource(R.drawable.topimage5);
            bundle=new Bundle();
            bundle.putString("url", "http://api.zhongchou.cn/deal/getdetail?projectID=118c66c5b2dde59581e624f7&v=2");
            bundle.putString("url1","http://api.zhongchou.cn/deal/getallitems?projectID=118c66c5b2dde59581e624f7&v=2");
            bundle.putString("url2","http://api.zhongchou.cn/comment/getlist?offset=0&count=10&projectID=118c66c5b2dde59581e624f7&v=2");
            bundle.putString("url3","http://api.zhongchou.cn/deal/getprocess?projectID=118c66c5b2dde59581e624f7&v=2");
            bundle.putInt("count",1);
        }

        imgv.setOnClickListener(this);

        return imgv;
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), ProgressActivity.class);

        intent.putExtras(bundle);
        startActivity(intent);
    }
}
