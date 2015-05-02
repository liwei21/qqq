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
import com.lkw.myapplication.bean.Detail;
import com.lkw.myapplication.tools.LoadBitmap;

import java.util.List;

/**
 * Created by LKW on 2015/5/2.
 */
public class ProgressNewAdapter extends BaseAdapter {
    private Context context;
    private List<Detail>list;

    public ProgressNewAdapter(Context context, List<Detail> list) {
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
        Detail detail = list.get(i);
        int type = detail.getType();
        String content = detail.getContent();
        Log.d("xxxxxxxxxxxxxxxxxxxxx",list.size()+"");
        Log.d("zzzzzzzzzzzzzzzzzzz",content);

          if (view==null){
              holder=new ViewHolder();
              view=LayoutInflater.from(context).inflate(R.layout.progress_new_tv,viewGroup,false);
              holder.tv=(TextView)view.findViewById(R.id.progress_new_list_tv);
              holder.iv=(ImageView)view.findViewById(R.id.progress_new_list_iv);
              view.setTag(holder);
          }else {
              holder= (ViewHolder) view.getTag();
          }

        if (i==0){
            holder.tv.setVisibility(View.VISIBLE);
            holder.iv.setVisibility(View.INVISIBLE);
            holder.tv.setText(content);
        }else {
            holder.tv.setVisibility(View.GONE);
            holder.iv.setVisibility(View.VISIBLE);
            LoadBitmap.getBitmap(content,holder.iv,context);
        }

        return view;
    }


    class ViewHolder{
        private TextView tv;
        private ImageView iv;

    }

}
