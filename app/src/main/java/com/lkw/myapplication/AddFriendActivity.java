package com.lkw.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AddFriendActivity extends ActionBarActivity implements View.OnClickListener {

    private RelativeLayout re_return;
    private LinearLayout add_friends_search_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        re_return = ((RelativeLayout) findViewById(R.id.re_return));
        re_return.setOnClickListener(this);
        add_friends_search_layout = (LinearLayout) findViewById(R.id.add_friends_search_layout);
        add_friends_search_layout.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_return:
                finish();
                break;
            case R.id.add_friends_search_layout:
                Intent intent = new Intent(this, SearchAddActivity.class);
                startActivity(intent);
                break;
        }
    }
}