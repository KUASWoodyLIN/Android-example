package com.example.woody_lin.lab6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {

    class Data{
        int photo;
        String name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Data[] transData = new Data[7];
        for (int i=0;i<transData.length;i++)
            transData[i] = new Data();
        transData[0].name = "腳踏車";
        transData[1].name = "機車";
        transData[2].name = "汽車";
        transData[3].name = "公車";
        transData[4].name = "高鐵";
        transData[5].name = "船";
        transData[6].name = "飛機'";
        transData[0].photo = R.drawable.bicycle;
        transData[1].photo = R.drawable.scooter;
        transData[2].photo = R.drawable.car;
        transData[3].photo = R.drawable.bus;
        transData[4].photo = R.drawable.train;
        transData[5].photo = R.drawable.ship;
        transData[6].photo = R.drawable.airplane;
        myAdapter transAdpter = new myAdapter(transData,R.layout.trans_list);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(transAdpter);

        //建立資料來源
        final String[] messageData = new String[]{ "訊息1", "訊息2", "訊息3", "訊息4", "訊息5", "訊息6" };
        //建立 adapter 物件，並放入資料來源與要顯示的項目畫面
        ArrayAdapter<String> messageAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,messageData);
        //建立清單元建，並連結adpater
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(messageAdapter);
        //建立項目 Item 點擊事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,messageData[position], Toast.LENGTH_SHORT).show();
            }
        });

        Data[] phizData = new Data[10];
        for (int i=0;i<phizData.length;i++)
            phizData[i]=new Data();
        phizData[0].name = "慶生";
        phizData[1].name = "哭哭";
        phizData[2].name = "裝死";
        phizData[3].name = "偵探";
        phizData[4].name = "親嘴";
        phizData[5].name = "喜歡";
        phizData[6].name = "玩手機";
        phizData[7].name = "尖叫";
        phizData[8].name = "問號";
        phizData[9].name = "工作";
        phizData[0].photo= R.drawable.birthday;
        phizData[1].photo= R.drawable.cry;
        phizData[2].photo= R.drawable.dead;
        phizData[3].photo= R.drawable.founding;
        phizData[4].photo= R.drawable.kiss;
        phizData[5].photo= R.drawable.like;
        phizData[6].photo= R.drawable.playphone;
        phizData[7].photo= R.drawable.shouting;
        phizData[8].photo= R.drawable.unknow;
        phizData[9].photo= R.drawable.work;

        myAdapter phizAdapter = new myAdapter(phizData,R.layout.phiz_list);
        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setNumColumns(3);
        gridView.setAdapter(phizAdapter);
    }

    public class myAdapter extends BaseAdapter {
        private Data[] data;
        private  int view;
        public myAdapter(Data[] data,int view){
            this.data = data;
            this.view = view;
        }
        @Override
        public int getCount() {
            return data.length;
        }
        @Override
        public Data getItem(int position) {
            return data[position];
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(view,parent,false);

            TextView name = (TextView)convertView.findViewById(R.id.name);
            ImageView imageView = (ImageView)convertView.findViewById(R.id.imageView);

            name.setText(data[position].name);
            imageView.setImageResource(data[position].photo);
            return convertView;
        }
    }

}
