package com.example.nguyenthanhxuan.quanlychitieubaocao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Nguyen Thanh Xuan on 4/5/2018.
 */

public class XinChaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_chao);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(XinChaoActivity.this,DangNhapActivity.class);
                startActivity(i);
                finish();
            }
        },1000);
    }
}
