package com.mirea.vbeygul.praktika4;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Current thread: " + mainThread.getName());
        mainThread.setName("MireaThread");
        infoTextView.append("\n New name of thread: " + mainThread.getName());
    }

    public void onClick(View view) {
        Runnable runnable = new Runnable() {
            public void run() {
                int numberThread = counter++;
                Log.i("ThreadProject", "Started thread No " + numberThread);
                long endTime = System.currentTimeMillis()
                        + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime -
                                    System.currentTimeMillis());
                        } catch (Exception e) {
                        }
                    }
                }
                Log.i("ThreadProject", "Completed thread No " + numberThread);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}