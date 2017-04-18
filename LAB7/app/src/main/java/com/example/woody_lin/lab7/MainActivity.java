package com.example.woody_lin.lab7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button game_button,bmi_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game_button = (Button)findViewById(R.id.button);
        bmi_button = (Button)findViewById(R.id.button2);

        game_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game_intent = new Intent(MainActivity.this,game.class);
                startActivity(game_intent);
            }
        });

        bmi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bmi_intent = new Intent(MainActivity.this,BMI.class);
                startActivity(bmi_intent);
            }
        });

    }
}
