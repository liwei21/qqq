package com.lkw.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lkw.myapplication.R;
import com.lkw.myapplication.bean.DongTai;
import com.lkw.myapplication.tools.LoadBitmap;

import java.util.List;

/**
 * Created by LKW on 2015/5/3.
 */
public class FragmentDongAdapter extends BaseAdapter {

    private Context context;
    private List<DongTai>list;

    public FragmentDongAdapter(Context context, List<DongTai> list) {
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
            view= LayoutInflater.from(context).inflate(R.layout.fragment_dong_layout,viewGroup,false);
            holder.title=(TextView)view.findViewById(R.id.dongtai_title);
            holder.time=(TextView)view.findViewById(R.id.dongtai_time);
            holder.content=(TextView)view.findViewById(R.id.dongtai_neirong);
            holder.iv=(ImageView)view.findViewById(R.id.iv);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        DongTai dongTai = list.get(i);
        holder.title.setText(dongTai.getTitle());
        holder.time.setText(dongTai.getCreateTime());
        holder.content.setText(dongTai.getContent());
        LoadBitmap.getBitmap(dongTai.getIcon(),holder.iv,context);


        return view;
    }

    class ViewHolder{
        private TextView title,time,content;
        private ImageView iv;
    }
}
