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
import android.widget.TextView;

import com.lkw.myapplication.service.ChatService;


public class SearchAddActivity extends ActionBarActivity implements View.OnClickListener, ServiceConnection {

    private TextView cancel;
    private EditText searchInput;
    private Button btn;
    private ChatService.ChatController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_add);
        cancel = ((TextView) findViewById(R.id.cancel));
        cancel.setOnClickListener(this);

        searchInput = (EditText) findViewById(R.id.searchInput);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        Intent intent = new Intent(this, ChatService.class);
        startService(intent);
        bindService(intent,this,BIND_AUTO_CREATE);
        startService(intent);



    }

    //点击监听
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel:
                finish();
                break;
            case R.id.btn:
                controller.addFriend(searchInput.getText().toString());
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        controller=(ChatService.ChatController)iBinder;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
controller=null;
    }
}