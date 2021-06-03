package com.mirea.vbeygul.looper;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Runnable updater;
    void updateTime(final String timeString) {
        TextView timer = (TextView) findViewById(R.id.textView);
        final Handler timerHandler = new Handler();

        updater = new Runnable() {
            @Override
            public void run() {
                timer.setText(timeString);
                timerHandler.postDelayed(updater,1000);
            }
        };
        timerHandler.post(updater);
    }

    MyLooper myLooper = new MyLooper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myLooper.start();
    }

    public void onClick(View view) {
        Message msg = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("KEY", "Годиков  Поглаживатель котиков");
        msg.setData(bundle);
        if (myLooper != null) {
            myLooper.handler.sendMessage(msg); }
    }
}