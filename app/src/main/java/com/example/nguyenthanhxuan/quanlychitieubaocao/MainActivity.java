package com.example.nguyenthanhxuan.quanlychitieubaocao;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferences=getSharedPreferences("account",MODE_PRIVATE);
        String storedPin=preferences.getString("xuan","12345");
        if(storedPin.equals("12345")){
            Toast.makeText(MainActivity.this,"Thay đổi mật khẩu",Toast.LENGTH_SHORT).show();
        }

        final TabHost tabHost=getTabHost();

        TabHost.TabSpec thuchi = tabHost.newTabSpec("Thu Chi");
        thuchi.setIndicator("Thu Chi", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent i = new Intent(MainActivity.this, ThuChiMainActivity.class);
        thuchi.setContent(i);

        TabHost.TabSpec thongke = tabHost.newTabSpec("Thống Kê");

        thongke.setIndicator("Thống Kê", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent o = new Intent(MainActivity.this, MenuThongKe.class);
        thongke.setContent(o);

        tabHost.addTab(thuchi);
        tabHost.addTab(thongke);
        tabHost.setCurrentTab(0);

        new DoGets().execute("http://192.168.1.21:6000/api/money");

    }

    class DoGets extends AsyncTask<String, Void, Integer>{

        @Override
        protected void onPostExecute(Integer integer) {
            //super.onPostExecute(integer);
            if(integer==500){
                Log.d("test","Failed");
            }else if(integer==200){
                Log.d("test","Successful");
            }
        }

        @Override
        protected Integer doInBackground(String... params) {
            String urlString=params[0];
            URL url=null;
            HttpURLConnection httpURLConnection=null;
            InputStream inputStream=null;
            String result="";
            int c;

            try{
                url=new URL(urlString);
                httpURLConnection=(HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);

                httpURLConnection.connect();
                inputStream=httpURLConnection.getInputStream();
                while ((c=inputStream.read())!=-1){
                    result+=(char)c;

                }
                Log.d("test",result);
                JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject;
                List<Money> monies =new ArrayList<>();

                for(int i=0;i<jsonArray.length();i++){
                    jsonObject=jsonArray.getJSONObject(i);// lan luot lay du lieu ra
                    monies.add(new Money(jsonObject.getInt("id"),jsonObject.getString("content"),jsonObject.getString("date"),jsonObject.getLong("money1"),jsonObject.getString("category"),jsonObject.getString("note")));
                }

                for(Money money : monies){
                    Log.d("test", money.toString());
                }
            }catch (Exception ex){
                ex.printStackTrace();
                return 400;
            }

            return 200;

        }
    }
}
