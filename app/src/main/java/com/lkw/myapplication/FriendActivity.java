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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.lkw.myapplication.service.ChatService;

import org.jivesoftware.smack.RosterEntry;

import java.util.ArrayList;
import java.util.List;


public class FriendActivity extends ActionBarActivity implements View.OnClickListener, ServiceConnection, AdapterView.OnItemClickListener {

    private RelativeLayout browding_goback;

    //服务调用接口
    private ChatService.ChatController controller;
    private List<RosterEntry> rosterEntries;
    private ListView friend_list;
    private ArrayList<String> data;
    private ArrayAdapter<String> adapter;
    private ImageView add_sendmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        browding_goback = (RelativeLayout) findViewById(R.id.browding_goback);
        browding_goback.setOnClickListener(this);

        //联系人列表部分
        friend_list = (ListView) findViewById(R.id.friend_list);
        data = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        friend_list.setAdapter(adapter);

        Intent intent = new Intent(this, ChatService.class);
        startService(intent);
        bindService(intent, this, BIND_AUTO_CREATE);
        startService(intent);

        //listview的点击事件
        friend_list.setOnItemClickListener(this);

        add_sendmessage = (ImageView) findViewById(R.id.add_sendmessage);
        add_sendmessage.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.browding_goback:
                finish();
                break;
            case R.id.add_sendmessage:
                Intent intent = new Intent(this, AddFriendActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateRosterList();
    }

    private void updateRosterList(){
        if (controller!=null){
            //每次显示的时候，及时获取联系人列表，进行刷新操作
            //获取联系人信息
            rosterEntries=controller.getRosterEntries();
            //清空数据
            data.clear();
            for (RosterEntry rosterEntry : rosterEntries) {
                String user = rosterEntry.getUser();
                data.add(user);
            }
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        controller = (ChatService.ChatController) iBinder;
        updateRosterList();
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        controller = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        //联系人信息
        RosterEntry entry = rosterEntries.get(i);

        //获取联系人账号
        String userJID = entry.getUser();

        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("userJID",userJID);
        startActivity(intent);
    }
}
