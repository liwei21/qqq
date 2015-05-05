package com.lkw.myapplication.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lkw.myapplication.R;
import com.lkw.myapplication.adapter.FragmentProAdapter;
import com.lkw.myapplication.bean.HuiBao;
import com.lkw.myapplication.util.HttpGetUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by LKW on 2015/5/2.
 */
public class FragmentProActivity extends Fragment {
<<<<<<< HEAD
    public static String url = "http://api.zhongchou.cn/deal/getallitems?projectID=b3a4dee40de3b7280e4d41e2&v=2";

=======
//    private String url = "http://api.zhongchou.cn/deal/getallitems?projectID=b3a4dee40de3b7280e4d41e2&v=2";
//        public static String url="http://api.zhongchou.cn/deal/getallitems?projectID=7a450e34f751023b2e817014&sort=sb&v=2";
        public  static String url;
>>>>>>> 154875e3466f389f4908367608675206a2982f6b
    private ListView fragment_pro_list;
    private HuiBao huiBao;
    private List<HuiBao> huiBaoList;
    private List<String> imgUrl;

    private List<String> imageUrlList;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                fragment_pro_list.setAdapter(new FragmentProAdapter(getActivity(), huiBaoList));
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);
        fragment_pro_list = (ListView) view.findViewById(R.id.fragment_pro_list);


        getInfo(url);
        return view;
    }


    public void getInfo(String url) {
        HttpGetUtils.getJSONString(url, new HttpGetUtils.OnNetStrDataListener() {
            @Override
            public void successed(String result) {
                try {
                    huiBaoList = new ArrayList<HuiBao>();
                    JSONObject obj1 = new JSONObject(result);
                    JSONArray arr = obj1.getJSONArray("data");
                    for (int i = 0; i < arr.length()-1; i++) {
                        JSONObject obj2 = arr.getJSONObject(i);


                        huiBao = new HuiBao();


                        String money = obj2.getString("money");
                        if (money != null) {
                            huiBao.setMoney(money);
                        }
                        huiBao.setLimit(obj2.getString("limit"));
                        huiBao.setRepay(obj2.getString("repay"));


                        //定义存放viewpaer图片的集合
                        imageUrlList = new ArrayList<String>();


                        Object array = obj2.get("imageUrls");
                        String arrayUrl = array.toString();

                        int count = 0;
                        //取出引号
                        Pattern compile1 = Pattern.compile("\\\"");
                        Matcher matcher1 = compile1.matcher(arrayUrl);
                        String imageUrl2 = matcher1.replaceAll("");
                        //取出逗号
                        Pattern compile2 = Pattern.compile(",");
                        Matcher matcher2 = compile2.matcher(imageUrl2);
                        String imageUrl3 = matcher2.replaceAll("");

                        //取出斜杠
                        Pattern compile = Pattern.compile("\\\\");
                        Matcher matcher = compile.matcher(imageUrl3);
                        String imageUrl = matcher.replaceAll("");

//                    Log.d("22222222222222222222222222222222", imageUrl);
//                    Log.d("77777777777777777777777777", arrayUrl.length() + "");
                        String newImageUrl = "";

                        for (int j = 0; j < imageUrl.length() / 80; j++) {

                            //分割出图片地址
                            int length = newImageUrl.length() * count;
                            newImageUrl = imageUrl.substring((imageUrl.indexOf("h") + length), imageUrl.indexOf("pg") + 2 + length);


                            imageUrlList.add(newImageUrl);
//                            Log.d("ddddddddddddddddddddddddd", newImageUrl + "\n");
                            count++;

                        }
//                        Log.d("-------image-----","___________________________________________________");
                        huiBao.setImageUrls(imageUrlList);

                        huiBao.setSupportCount(obj2.getString("supportCount"));
                        huiBaoList.add(huiBao);

//                        Log.d("qqqqqqqqqqqqqqqqq", huiBaoList.toString());
                    }
//                    Log.d("wwwwwwwwwwwwwwww",huiBaoList.size()+"");
                    handler.sendEmptyMessage(1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed() {
//                Log.d("BBBBBBBBBBBBBBBBBBBB", "失败");
            }
        });
    }

}
