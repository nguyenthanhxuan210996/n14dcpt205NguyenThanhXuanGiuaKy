package com.example.nguyenthanhxuan.quanlychitieubaocao;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * Created by Nguyen Thanh Xuan on 4/6/2018.
 */

public class MenuThongKe extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_thong_ke);

        final TabHost tabHost = getTabHost();

        TabHost.TabSpec tuannay = tabHost.newTabSpec("Tuần này");
        tuannay.setIndicator("Tuần này", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent hn = new Intent(MenuThongKe.this, ThongKeTuan.class);
        tuannay.setContent(hn);

        TabHost.TabSpec thangnay = tabHost.newTabSpec("Tháng Này");
        thangnay.setIndicator("Tháng Này", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent tn = new Intent(this, ThongKeThang.class);
        thangnay.setContent(tn);

        TabHost.TabSpec quynay = tabHost.newTabSpec("Quý này");
        quynay.setIndicator("Quý Này", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent qn = new Intent(this, ThongKeQuy.class);
        quynay.setContent(qn);

        TabHost.TabSpec namnay = tabHost.newTabSpec("Năm này");
        namnay.setIndicator("Năm Này", getResources().getDrawable(R.mipmap.ic_launcher));
        Intent nn = new Intent(this, ThongKeNam.class);
        namnay.setContent(nn);

        tabHost.addTab(tuannay);
        tabHost.addTab(thangnay);
        tabHost.addTab(quynay);
        tabHost.addTab(namnay);
        tabHost.setCurrentTab(0);

    }
}
