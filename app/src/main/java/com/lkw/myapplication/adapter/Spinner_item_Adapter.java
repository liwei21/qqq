package com.lkw.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lkw.myapplication.R;

/**
 * Created by Administrator on 2015/5/4.
 */
public class Spinner_item_Adapter extends BaseAdapter {
    private Context context;

    private  String[]  str;
    public Spinner_item_Adapter(Context context,String[]  str) {
        this.context = context;
        this.str=str;
    }
    @Override
    public int getCount() {
        return str.length;
    }

    @Override
    public Object getItem(int position) {
        return str[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item1, parent, false);
        }
        TextView text = (TextView) convertView.findViewById(R.id.spinner_item_textview);


        text.setText(str[position]);

        return convertView;
    }
}
