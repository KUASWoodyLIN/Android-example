package com.example.woody_lin.lab5;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    Button Toast1Click,Toast2Click,Dialog1Click,Dialog2Click,LAB5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast1Click = (Button)findViewById(R.id.button2);
        Toast2Click = (Button)findViewById(R.id.button);
        Dialog1Click = (Button)findViewById(R.id.button3);
        Dialog2Click = (Button)findViewById(R.id.button4);
        LAB5 = (Button)findViewById(R.id.button5);

        Toast1Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast1 = Toast.makeText(MainActivity.this,"Hello World",Toast.LENGTH_SHORT);
                toast1.show();
            }
        });

        Toast2Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //初始化 Toast
                Toast toast2 = new Toast(MainActivity.this);
                //Toast 在畫面中顯示
                toast2.setGravity(Gravity.BOTTOM,0,0);
                //Toast 在畫面中顯示時間
                toast2.setDuration(Toast.LENGTH_SHORT);
                //放入自定義的畫面
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.activity_toast_layout,null);
                toast2.setView(layout);
                //顯示畫面
                toast2.show();
            }
        });

        Dialog1Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("基本訊息對話按鈕");
                dialog.setMessage("基本訊息對話按鈕");

                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"我了解",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"我尚未了解",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();

            }
        });

        Dialog2Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] list = {"message 1 ","message 2","message 3","message 4","message 5"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("利用 List 呈現");
                dialog.setItems(list, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"你選擇了"+list[which],Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

        LAB5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("請選擇功能");
                dialog.setMessage("根據下方按鈕選擇顯示的物件");

                dialog.setPositiveButton("顯示 LIST", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showListDialog();
                    }
                });

                dialog.setNegativeButton("自定義 TOAST", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToast();
                    }
                });

                dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"取消",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }

        });
    }

    private void showToast() {
        Toast toast = new Toast(MainActivity.this);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        View layout = getLayoutInflater().inflate(R.layout.activity_toast_layout,null);
        toast.setView(layout);
        toast.show();
    }

    private void showListDialog(){
        final String[] list = {"message 1","message 2","message 3","message 4","message 5"};

        AlertDialog.Builder dialog_list = new AlertDialog.Builder(MainActivity.this);
        dialog_list.setItems(list, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"你選擇了"+list[which],Toast.LENGTH_SHORT).show();
            }
        });
        dialog_list.show();
    }
}
