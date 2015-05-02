package com.lkw.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class RegiserActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView register_back;
    private TextView register_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regiser);

        register_back = (TextView) findViewById(R.id.register_back);
        register_back.setOnClickListener(this);
        register_close = (TextView) findViewById(R.id.register_close);
        register_close.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.register_back:
                intent.setClass(RegiserActivity.this,LoginActivity.class);
                finish();
                break;
            case R.id.register_close:
                intent.setClass(RegiserActivity.this,MainActivity.class);
                finish();
                break;
        }
        startActivity(intent);
    }
}
