package com.lkw.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lkw.myapplication.R;
import com.lkw.myapplication.bean.VP2_quanbu_beans;
import com.lkw.myapplication.tools.ImageViewWithWidth;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/5/3.
 */
public class VP2_quanbu_adapter extends BaseAdapter{

    private Context context;
    private List<VP2_quanbu_beans> data;

    public VP2_quanbu_adapter(Context context, List<VP2_quanbu_beans> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.main_listitem,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder= (ViewHolder) convertView.getTag();
        VP2_quanbu_beans quanbu_beans = data.get(position);
        holder.item_title.setText(quanbu_beans.getName());
        holder.item_info.setText(quanbu_beans.getSummary());
        holder.item_price.setText("￥"+quanbu_beans.getFloorPrice());
        holder.item_sale.setText(quanbu_beans.getProgress()+"%");
        BitmapUtils utils=new BitmapUtils(context);
          utils.display(holder.item_image,quanbu_beans.getImageUrl());
        return convertView;
}
class ViewHolder{

    private TextView item_title,item_info,item_price,item_sale;
    private ImageView item_image;
    public ViewHolder(View view){
        item_title= (TextView) view.findViewById(R.id.item_title);
        item_info= (TextView) view.findViewById(R.id.item_info);
        item_price= (TextView) view.findViewById(R.id.item_price);
        item_image= (ImageView) view.findViewById(R.id.item_image);
        item_sale= (TextView) view.findViewById(R.id.item_sale);
    }
    }
}
