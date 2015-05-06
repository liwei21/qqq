package com.lkw.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lkw.myapplication.R;
import com.lkw.myapplication.bean.ChatMessage;

import java.util.List;

/**
 * Created by LKW on 2015/5/5.
 */
public class ChatMessageAdapter extends BaseAdapter {

    private List<ChatMessage>messages;
    private Context context;
    private LayoutInflater inflater;

    public ChatMessageAdapter(List<ChatMessage> messages, Context context) {
        this.messages = messages;
        this.context = context;
inflater=LayoutInflater.from(context);
    }

    /**
     * 获取所有数据的个数
     * @return
     */
    @Override
    public int getCount() {
        int ret=0;
        if (messages!=null){
            ret=messages.size();
        }

        return ret;
    }

    /**
     * 获取指定所引的实际数据对象
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        Object ret=null;
        if (messages!=null){
            ret=messages.get(i);
        }

        return ret;
    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public int getViewTypeCount() {
        //主要是聊天主要有接收和发送两种布局
        //左侧接收，右侧发送
        return 2;
    }


    /**
     * 每次ListView显示Item的时候，都先问一下Adapter指定位置的item是什么类型
     * @param position 根据位置，获取数据的类型
     * @return int 注意，返回到数值必须是从-到getViewTypeCount（）-1
     */
    @Override
    public int getItemViewType(int position) {

        ChatMessage chatMessage = messages.get(position);
        int ret=0;
        int sourceType = chatMessage.getSourceType();
        //发出去的消息，显示在右侧，返回值类型为1
        if (sourceType==chatMessage.SOURCE_TYPE_SEND){
            ret=1;
        }else if (sourceType==chatMessage.SOURCE_TYPE_RECEIVED){
            ret=0;
        }

        return ret;
    }

    /**
     *
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View ret=null;

        ChatMessage chatMessage = messages.get(i);
        //获取来源类型
        int sourceType = chatMessage.getSourceType();

        String body = chatMessage.getBody();
        if (sourceType==ChatMessage.SOURCE_TYPE_RECEIVED){
            //收到的在左侧
            if (view!=null){
                ret=view;
            }else {
                ret=inflater.inflate(R.layout.item_chat_left,viewGroup,false);
            }

            TextView txtMessage = (TextView) ret.findViewById(R.id.chat_message);
            txtMessage.setText(body);
        }else if (sourceType==ChatMessage.SOURCE_TYPE_SEND){
            //TODO 发送的，就在右侧

            if (view!=null){
                ret=view;
            }else {
                ret=inflater.inflate(R.layout.item_chat_right,viewGroup,false);
            }

            //TODO 显示消息，右侧
            TextView txtMessage = (TextView) ret.findViewById(R.id.chat_message_right);

            txtMessage.setText(body);

        }

        return ret;

    }
}
