package com.example.woody_lin.lab7;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class BMI extends AppCompatActivity {

    EditText input_high, input_kg;
    RadioGroup radioGroup;
    Button start;
    TextView std_kg,std_bodyfat,errormessage;
    int gender = 0; // 0還沒選擇    1男生     2女生

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        input_high = (EditText)findViewById(R.id.editText1);
        input_kg = (EditText)findViewById(R.id.editText2);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        start = (Button)findViewById(R.id.button);
        std_kg = (TextView)findViewById(R.id.textView4);
        std_bodyfat = (TextView)findViewById(R.id.textView5);
        errormessage = (TextView)findViewById(R.id.textView6);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_high.getText().toString().equals("")) {
                    errormessage.setText("錯誤訊息 : 請輸入身高");
                } else if (input_kg.getText().toString().equals("")) {
                    errormessage.setText("錯誤訊息 : 體重");
                } else if (gender == 0) {
                    errormessage.setText("錯誤訊息 : 性別");
                } else {
                    runAsyncTask();
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                        gender = 1;
                        break;
                    case R.id.radioButton2:
                        gender = 2;
                        break;
                }
            }
        });
    }

    private void runAsyncTask(){
        new AsyncTask<Void , Integer , Boolean>(){
            ProgressDialog dialog = new ProgressDialog(BMI.this);

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                dialog.setTitle("計算中...");  //進度條標題
                dialog.setCancelable(false);    //進度條是否手動關閉
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);   //進度條樣式
                dialog.show();
            }

            @Override
            protected Boolean doInBackground(Void... params) {
                int progress = 0;
                while (progress < 100) {
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(Integer.valueOf(progress));
                    progress ++;
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                dialog.setProgress(values[0]);      //進度條更新
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);
                dialog.dismiss();       //結束
                double cal_High = Double.parseDouble(input_high.getText().toString());
                double cal_kg = Double.parseDouble(input_kg.getText().toString());
                double cal_std_kg = 0;
                double cal_std_bodyfat = 0;
                if (gender == 1) {
                    cal_std_kg = 22 * cal_High / 100 * cal_High / 100;
                    cal_std_bodyfat = (cal_kg - (0.88*cal_std_kg)) / cal_kg * 100;
                } else {
                    cal_std_kg = 22 * cal_High / 100 * cal_High / 100;
                    cal_std_bodyfat = (cal_kg - (0.82 * cal_std_kg)) / cal_kg *100;
                }
                std_kg.setText(String.format("標準體重 %.2f",cal_std_kg));
                std_bodyfat.setText(String.format("體脂肪 %.2f",cal_std_bodyfat));
            }
        }.execute();
    }
}
