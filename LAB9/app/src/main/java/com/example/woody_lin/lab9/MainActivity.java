package com.example.woody_lin.lab9;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button start,startReceiver,stopReceiver;
    TextView show;
    IntentFilter filter;
    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.button);
        show = (TextView) findViewById(R.id.textView);
        startReceiver = (Button) findViewById(R.id.button2);
        stopReceiver = (Button) findViewById(R.id.button3);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                startService(intent);
                Toast.makeText(MainActivity.this, "啟動service成功", Toast.LENGTH_SHORT).show();
            }
        });

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int count = intent.getExtras().getInt("count");
                show.setText("Service啟動了" + count + "秒");
            }
        };

        filter = new IntentFilter("MyMessage");

        startReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerReceiver(receiver, filter);
            }
        });

        stopReceiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unregisterReceiver(receiver);
            }
        });
    }
}
