package com.example.woody_lin.lab7;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class game extends AppCompatActivity {

    Button start;
    SeekBar rabbit_seekbar,tortoise_seekbar;
    int rabbit_count = 0,tortoise_count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        start = (Button) findViewById(R.id.button);
        tortoise_seekbar = (SeekBar) findViewById(R.id.seekBa1);
        rabbit_seekbar = (SeekBar) findViewById(R.id.seekBar2);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(game.this, "開始遊戲", Toast.LENGTH_SHORT).show();
                runThread();
                runAsyncTask();
            }
        });
    }

    private  void runThread() {
        new Thread() {
            public void run() {
                do {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    rabbit_count += (int) (Math.random() * 3);
                    Message msg = new Message();
                    msg.what = 1;
                    mHandler.sendMessage(msg);
                }while (rabbit_count < 100 );
            }
        }.start();
    }

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    rabbit_seekbar.setProgress(rabbit_count);
                    break;
            }
            if (rabbit_count >= 100)
                if(tortoise_count < 100)
                    Toast.makeText(game.this,"兔子勝利",Toast.LENGTH_SHORT).show();
        }
    };

    private void runAsyncTask() {
        new AsyncTask<Void, Integer, Boolean>(){
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                tortoise_count = 0;
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                do {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tortoise_count += (int) (Math.random() * 3);    //隨機增加0~2
                    publishProgress(tortoise_count);        //更新進度條進度，傳入烏龜計數器
                }while (tortoise_count <100);
                return true;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                tortoise_seekbar.setProgress(values[0]);
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                if (rabbit_count <100)
                    Toast.makeText(game.this,"烏龜勝利",Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }
}
