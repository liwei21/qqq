package com.lkw.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;


public class MyLikeActivity extends ActionBarActivity implements View.OnClickListener {

    private RelativeLayout browding_goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_like);
        browding_goback = (RelativeLayout) findViewById(R.id.browding_goback);
        browding_goback.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.browding_goback:
                finish();
                break;
        }
    }
}
