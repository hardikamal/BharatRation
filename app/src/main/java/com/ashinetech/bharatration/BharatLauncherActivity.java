package com.ashinetech.bharatration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ragavendran on 08-07-2015.
 */
public class BharatLauncherActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_launcher);


       Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(BharatLauncherActivity.this,MainActivity.class));
            }
        },1000);

    }
}
