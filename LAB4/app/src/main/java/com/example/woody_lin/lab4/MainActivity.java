package com.example.woody_lin.lab4;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView drink,ice,suger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.sent);
        drink = (TextView)findViewById(R.id.drink);
        ice = (TextView)findViewById(R.id.ice);
        suger = (TextView)findViewById(R.id.suger);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Main2Activity.class);
                int requestCode = 0;
                startActivityForResult(intent,requestCode);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if (resultCode == 101) {
                    Bundle bundle = data.getExtras();
                    drink.setText(bundle.getString("drink"));
                    ice.setText(bundle.getString("ice"));
                    suger.setText(bundle.getString("suger"));
                }
                break;
            default:
                break;
        }
    }
}
