package com.example.woody_lin.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText gamer;
    TextView status,name,winner,myMora,computerMora;
    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2,radioButton3;
    Button play;
    int playerMora = -1;
    String[] mora = {"剪刀","石頭","布"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamer =(EditText) findViewById(R.id.gamer);
        status = (TextView)findViewById(R.id.status);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioButton1 = (RadioButton)findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton)findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton)findViewById(R.id.radioButton3);
        play = (Button)findViewById(R.id.play);
        name =(TextView)findViewById(R.id.name);
        winner = (TextView)findViewById(R.id.winner);
        myMora = (TextView)findViewById(R.id.myMora);
        computerMora =(TextView)findViewById(R.id.computerMora);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gamer.getText().toString().equals("")){
                    status.setText("請選擇玩家名稱");
                }
                else if(playerMora == -1){
                    status.setText("請選擇出拳的種類");
                }
                else{
                    name.setText(gamer.getText());
                    myMora.setText(mora[playerMora]);
                    int computer_random = (int) (Math.random()*3);
                    computerMora.setText(mora[computer_random]);
                    if (playerMora == computer_random){
                        winner.setText("平手");
                        status.setText("平手!再試一場看看");
                    }
                    else if ((playerMora==0 && computer_random==1)||(playerMora==1 && computer_random==2)||(playerMora==2 && computer_random==0))
                    {
                        winner.setText("電腦");
                        status.setText("可惜電腦獲勝了");
                    }
                    else
                    {
                        winner.setText(gamer.getText());
                        status.setText("恭喜你獲勝了");
                    }
                }
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioButton1:
                        playerMora = 0;
                        break;
                    case R.id.radioButton2:
                        playerMora = 1;
                        break;
                    case R.id.radioButton3:
                        playerMora = 2;
                        break;
                }
            }
        });


    }
}
