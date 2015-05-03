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
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lkw.myapplication.service.ChatService;


public class LoginActivity extends ActionBarActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ServiceConnection {

    private ImageView login_head_iv;
    private RadioGroup login_rg;
    private EditText login_username;
    private EditText login_yanzhenma;
    private Button login_rg_btn;
    private TextView login_register;
    private Button login_btn;
    private ChatService.ChatController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_head_iv = (ImageView) findViewById(R.id.login_head_iv);
        login_head_iv.setOnClickListener(this);

        login_rg = (RadioGroup) findViewById(R.id.login_rg);
        login_rg.setOnCheckedChangeListener(this);

        login_username = (EditText) findViewById(R.id.login_username);
        login_yanzhenma = (EditText) findViewById(R.id.login_yanzhenma);
        login_rg_btn = (Button) findViewById(R.id.login_rg_btn);
        login_register = (TextView) findViewById(R.id.login_register);
        login_register.setOnClickListener(this);
        login_btn = (Button) findViewById(R.id.login_btn);
        login_btn.setOnClickListener(this);

        Intent intent = new Intent(this, ChatService.class);
        startService(intent);
        bindService(intent,this,BIND_AUTO_CREATE);

    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.login_head_iv:
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.setClass(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.login_register:
               intent.setClass(LoginActivity.this,RegiserActivity.class);
                startActivity(intent);
                break;
            case R.id.login_btn:
                String username=login_username.getText().toString();
                String password = login_yanzhenma.getText().toString();
                if (controller.Register(username,password))
                {
                    intent.setClass(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    //TODO 登录失败
                    Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                }

        }

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i==R.id.login_rb1){
            login_username.setHint("请输入手机号，未注册将自动创建");
            login_yanzhenma.setHint("请输入验证码");
            login_rg_btn.setVisibility(View.VISIBLE);
        }else   if (i==R.id.login_rb2){
            login_username.setHint("请输入您的手机号/邮箱");
            login_yanzhenma.setHint("请输入密码");
            login_rg_btn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        super.onDestroy();
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        controller= (ChatService.ChatController) iBinder;
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
controller=null;
    }
}
