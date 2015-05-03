package com.lkw.myapplication.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lkw.myapplication.R;

import java.util.List;

import Bean.HomeLvData;

/**
 * Created by aaa on 15-5-3.
 */
public class HomeLVAdapter extends BaseAdapter {
    private List<HomeLvData> list;
    private Context context ;

    public HomeLVAdapter(List<HomeLvData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        Log.d("!!!!count----->",list.size()+"");
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.main_listitem,parent,false);
            holder.item_image = (ImageView) convertView
                    .findViewById(R.id.item_image);
            holder.name = (TextView) convertView
                    .findViewById(R.id.item_title);
            holder.summary = (TextView) convertView
                    .findViewById(R.id.item_info);
            holder.floorPrice = (TextView) convertView
                    .findViewById(R.id.item_price);
            holder.progress = (TextView) convertView
                    .findViewById(R.id.item_price);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        HomeLvData newsData = list.get(position);
        String imgUrl = newsData.getImageUrl();
        BitmapUtils bitmapUtils=new BitmapUtils(context);
        if (imgUrl != null) {
            String replace = null;
            bitmapUtils.display(holder.item_image, imgUrl);
        }
        holder.name.setText(newsData.getName());
        holder.summary.setText(newsData.getSummary());
        holder.floorPrice.setText(newsData.getFloorPrice());
        holder.progress.setText(newsData.getProgress());

        return convertView;
    }

    class ViewHolder{
        private ImageView item_image;
        private TextView name,summary,floorPrice,progress;
    }
}
