package com.example.woody_lin.lab11;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText bookin,picin;
    TextView ordershow,bookshow,picshow;
    Button addSQL,changeSQL,delSQL,askSQL;
    SQLiteDatabase dbrw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bookin = (EditText) findViewById(R.id.editText1);
        picin = (EditText) findViewById(R.id.editText2);
        addSQL = (Button) findViewById(R.id.button1);
        changeSQL = (Button) findViewById(R.id.button2);
        delSQL = (Button) findViewById(R.id.button3);
        askSQL = (Button) findViewById(R.id.button4);
        ordershow = (TextView) findViewById(R.id.textView3);
        bookshow = (TextView) findViewById(R.id.textView4);
        picshow = (TextView) findViewById(R.id.textView5);
        MyDBHelper dbhelper = new MyDBHelper(this);
        dbrw = dbhelper.getWritableDatabase();

        addSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newBook();
            }
        });

        changeSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chnewBook();
            }
        });

        delSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delnewBook();
            }
        });

        askSQL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asknewBook();
            }
        });
    }

    public void newBook() {
        if (bookin.getText().toString().equals("") || picin.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "輸入資料不完全", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues cv = new ContentValues();
            cv.put("title",bookin.getText().toString());
            cv.put("price",Double.parseDouble(picin.getText().toString()));
            dbrw.insert("myTable", null, cv);

            Toast.makeText(MainActivity.this,"新增書名:"+bookin.getText().toString()+"   價格"+picin.getText().toString(),Toast.LENGTH_SHORT).show();
            bookin.setText("");
            picin.setText("");
        }
    }

    public void chnewBook() {
        if (bookin.getText().toString().equals("") || picin.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "更新資料不完全", Toast.LENGTH_SHORT).show();
        } else {

            ContentValues cv = new ContentValues();
            cv.put("price",Double.parseDouble(picin.getText().toString()));
            dbrw.update("myTable", cv, "title=" + "'" + bookin.getText().toString() + "'", null);

            Toast.makeText(MainActivity.this,"更改成功",Toast.LENGTH_SHORT).show();
            bookin.setText("");
            picin.setText("");
        }
    }

    public void delnewBook() {
        if (bookin.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "刪除資料不完全", Toast.LENGTH_SHORT).show();
        } else {
            dbrw.delete("myTable","title="+"'"+bookin.getText().toString()+"'",null );

            Toast.makeText(MainActivity.this,"刪除成功",Toast.LENGTH_SHORT).show();
            bookin.setText("");
            picin.setText("");
        }
    }

    public void asknewBook() {
        String index = "順序\n", title = "書名\n", price ="價格\n" ;
        String[] colum = {"title", "price"};

        Cursor c;
        if (bookin.getText().toString().equals("")) {
            c = dbrw.query("myTable", colum, null, null, null, null, null);
        } else {
            c = dbrw.query("myTable", colum, "title=" + "'" + bookin.getText().toString() + "'", null, null, null, null);
        }
        if (c.getCount() > 0) {
            c.moveToFirst();
            for (int i=0 ; i < c.getCount() ; i++) {
                index += (i+1) + "\n";
                title += c.getString(0) + "\n";
                price += c.getString(1) + "\n";
                c.moveToNext();
            }
            ordershow.setText(index);
            bookshow.setText(title);
            picshow.setText(price);
            Toast.makeText(MainActivity.this,"共有"+c.getCount()+"筆紀錄",Toast.LENGTH_SHORT).show();
        }
    }
}
