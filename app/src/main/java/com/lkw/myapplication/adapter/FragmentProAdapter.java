package com.lkw.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lkw.myapplication.R;
import com.lkw.myapplication.bean.HuiBao;
import com.lkw.myapplication.tools.LoadBitmap;

import java.util.List;

/**
 * Created by LKW on 2015/5/2.
 */
public class FragmentProAdapter extends BaseAdapter {

    private Context context;
    private List<HuiBao> list;

    public FragmentProAdapter(Context context, List<HuiBao> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.fragment_progress_layout,viewGroup,false);
            holder.price=(TextView)view.findViewById(R.id.fra_pro_tvprice);
            holder.xianzhi=(TextView)view.findViewById(R.id.fra_pro_xianzhi);
            holder.neirong=(TextView)view.findViewById(R.id.fra_pro_neirong);
            holder.zhichi=(TextView)view.findViewById(R.id.fra_pro_zhichi);
            holder.iv1=(ImageView)view.findViewById(R.id.fra_pro_iv1);
            holder.iv2=(ImageView)view.findViewById(R.id.fra_pro_iv2);
            holder.iv3=(ImageView)view.findViewById(R.id.fra_pro_iv3);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }

        HuiBao huiBao = list.get(i);

        Log.d("-----list.size-----","list的长度"+list.size());
        holder.price.setText("￥"+huiBao.getMoney());
        holder.xianzhi.setText("限制"+huiBao.getLimit()+"人");
        holder.neirong.setText(huiBao.getRepay());

        holder.zhichi.setText(huiBao.getSupportCount()+"人已支持");

        List<String> urls = huiBao.getImageUrls();
        int size = urls.size();
        Log.d("-----urls.size-----","urls的长度"+size);
        if (size==2){
            LoadBitmap.getBitmap(urls.get(0),holder.iv1,context);
            LoadBitmap.getBitmap(urls.get(1),holder.iv2,context);
        }else if (size==3){

                LoadBitmap.getBitmap(urls.get(0),holder.iv1,context);
                LoadBitmap.getBitmap(urls.get(1),holder.iv2,context);
                LoadBitmap.getBitmap(urls.get(2),holder.iv3,context);

        }


        return view;
    }

    class ViewHolder{
        private TextView price,xianzhi,neirong,zhichi;
        private ImageView iv1,iv2,iv3;
    }
}
