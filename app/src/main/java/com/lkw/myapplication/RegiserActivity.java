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
import android.widget.TextView;
import android.widget.Toast;

import com.lkw.myapplication.service.ChatService;


public class RegiserActivity extends ActionBarActivity implements View.OnClickListener,ServiceConnection {

    private TextView register_back;
    private TextView register_close;
    private Button register_btn;
    private TextView register_username;
    private TextView register_password;
    private ChatService.ChatController controller;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiser);

        Intent intent = new Intent(this, ChatService.class);
        startService(intent);
        bindService(intent,this,BIND_AUTO_CREATE);
        register_back = (TextView) findViewById(R.id.register_back);
        register_back.setOnClickListener(this);
        register_close = (TextView) findViewById(R.id.register_close);
        register_close.setOnClickListener(this);
        register_btn = (Button) findViewById(R.id.register_btn);
        register_btn.setOnClickListener(this);
        register_username = (TextView) findViewById(R.id.register_username);
        register_password = (TextView) findViewById(R.id.register_password);


    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.register_back:
                intent.setClass(RegiserActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register_close:
                intent.setClass(RegiserActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.register_btn:
                username = register_username.getText().toString();
                password = register_password.getText().toString();
                if (controller.Register(username,password)){
                    Toast.makeText(RegiserActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(RegiserActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
                }

                break;

        }

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
