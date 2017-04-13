package com.example.woody_lin.lab4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    Button button;
    EditText drink;
    RadioGroup radioGroup1,radioGroup2;
    String ice="",suger="";
    TextView errormessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button = (Button)findViewById(R.id.sent);
        drink = (EditText)findViewById(R.id.drink);
        errormessage = (TextView)findViewById(R.id.errormessage);
        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        radioGroup2 = (RadioGroup)findViewById(R.id.radioGroup2);


        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1:
                        suger = "少糖";
                        break;
                    case R.id.radioButton2:
                        suger = "半糖";
                        break;
                    case R.id.radioButton3:
                        suger = "全糖";
                        break;
                    case R.id.radioButton4:
                        suger = "無糖";
                        break;
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton5:
                        ice = "微冰";
                        break;
                    case R.id.radioButton6:
                        ice = "少冰";
                        break;
                    case R.id.radioButton7:
                        ice = "正常";
                        break;
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(drink.getText().toString().equals("")) {
                    errormessage.setText("請選擇飲料");
                }
                else if (suger == "") {
                    errormessage.setText("請選擇甜度");
                }
                else if (ice == "") {
                    errormessage.setText("請選擇冰塊");
                }
                else {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("drink",drink.getText().toString());
                    bundle.putString("suger",suger);
                    bundle.putString("ice",ice);
                    intent.putExtras(bundle);
                    int resultCode=101;
                    setResult(resultCode,intent);
                    finish();
                }

            }
        });
    }
}
