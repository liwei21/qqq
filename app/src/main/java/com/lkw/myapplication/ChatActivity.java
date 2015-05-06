package com.lkw.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.lkw.myapplication.adapter.ChatMessageAdapter;
import com.lkw.myapplication.bean.ChatMessage;
import com.lkw.myapplication.service.ChatService;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import java.util.ArrayList;


public class ChatActivity extends ActionBarActivity implements ServiceConnection, View.OnClickListener, MessageListener, PacketListener {

    private ChatService.ChatController controller;
    private Chat chat;

    private String userJID;
    private String thread1;
    private String body;
    private EditText message;
    private Button fasong;
    private ListView listView;
    private ArrayList<ChatMessage> chatMessages;
    private ChatMessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        //接收目标联系人
        Intent intent = getIntent();
        userJID = intent.getStringExtra("userJID");
        //获取Chat主题，可能为空，因为自己点击进入ChatActivity时，是没有的
        thread1 = intent.getStringExtra("thread1");
        //只有收到消息的时候，才会有
        body = intent.getStringExtra("body");
        //绑定服务，用于发送消息
        Intent service = new Intent(this, ChatService.class);
        bindService(service,this,BIND_AUTO_CREATE);
        message = (EditText) findViewById(R.id.message);
        fasong = (Button) findViewById(R.id.fasong);
        fasong.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.listView);

        chatMessages = new ArrayList<>();
        adapter = new ChatMessageAdapter(chatMessages, this);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }




    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        controller=(ChatService.ChatController)iBinder;
        //绑定成功后，进行聊天会话的创建
        chat=controller.openChat(userJID,null,this);
        //controller向内部的XMPPTCPConnection添加一个PacketListener进行消息检查
        controller.addPacketListener(this);

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        //删除/停止监听数据包的内容
        controller.removePackerListener(this);
        if (chat!=null){
            chat.close();
        }
controller=null;
    }

    @Override
    public void onClick(View view) {
        String content = message.getText().toString();
        if (chat!=null){
            try {
                chat.sendMessage(content);

                message.setText("");

                ChatMessage msg = new ChatMessage();
                msg.setBody(content);
                //发送
                msg.setSourceType(ChatMessage.SOURCE_TYPE_SEND);
                chatMessages.add(msg);

                adapter.notifyDataSetChanged();


            } catch (XMPPException e) {
                e.printStackTrace();
            } catch (SmackException.NotConnectedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void processMessage(Chat chat, Message message) {
        //TODO 处理消息的发送与接收
        String from = message.getFrom();
        String to = message.getTo();
        String body = message.getBody();


    }

    /**
     * 接收消息，显示在ListView上面
     * @param packet
     * @throws SmackException.NotConnectedException
     */
    @Override
    public void processPacket(Packet packet) throws SmackException.NotConnectedException {
        if (packet instanceof Message){
            Message msg=(Message)packet;

            //检查消息的来源是否是当前的会话的人
            String from = msg.getFrom();
            //因为PacketListener会接收所有的消息，对于会话界面而言，就需要检查消息的来源，
            //是否当前聊天人
            if (from.startsWith(userJID)){
                //显示ListView的信息
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setBody(msg.getBody());
                chatMessage.setFrom(from);
                chatMessage.setTo(msg.getTo());
                chatMessage.setSourceType(ChatMessage.SOURCE_TYPE_RECEIVED);
                chatMessage.setTime(System.currentTimeMillis());

                //添加消息，更新ListView的adapter
                chatMessages.add(chatMessage);


                //因为processPackers 执行在子线程中，
                //ListView更新应该在主线程中
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }


        }
    }
}
