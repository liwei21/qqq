package com.lkw.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lkw.myapplication.R;
import com.lkw.myapplication.bean.PingLun;
import com.lkw.myapplication.bean.PingOwner;
import com.lkw.myapplication.tools.LoadBitmap;

import java.util.List;

/**
 * Created by LKW on 2015/5/3.
 */
public class FraPingLunAdapter extends BaseAdapter {

    private Context context;
    private List<PingLun>list;

    public FraPingLunAdapter(Context context, List<PingLun> list) {
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
        final ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.fragment_pinglun_layout,viewGroup,false);
            holder.icon=(ImageView)view.findViewById(R.id.pinglun_iv_icon);
            holder.name=(TextView)view.findViewById(R.id.pinglun_tv_name);
            holder.neirong=(TextView)view.findViewById(R.id.pinglun_tv_neirong);
            holder.tupian=(ImageView)view.findViewById(R.id.pinglun_iv_neirong);
            holder.time=(TextView)view.findViewById(R.id.pinglun_tv_time);
            holder.zan=(CheckBox)view.findViewById(R.id.zan);
            view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        PingLun pingLun = list.get(i);

        PingOwner owner = pingLun.getOwner();
        LoadBitmap.getBitmap(owner.getHeaderUrl(),holder.icon,context);
        holder.name.setText(owner.getName());

        holder.neirong.setText(pingLun.getContent());

//        String imageUrlArray = pingLun.getImageUrlArray();
//        if (imageUrlArray!=null) {
//
//            LoadBitmap.getBitmap(imageUrlArray, holder.tupian, context);
//        }
        holder.time.setText(pingLun.getTime());

        holder.zan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.zan.setTextColor(Color.RED);
            }
        });


        return view;
    }


    class ViewHolder{
        private TextView name,neirong,time;
        private ImageView icon,tupian;
        private CheckBox zan;
    }
}
