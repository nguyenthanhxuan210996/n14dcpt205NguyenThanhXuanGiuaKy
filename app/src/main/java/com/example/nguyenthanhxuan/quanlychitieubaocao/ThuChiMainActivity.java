package com.example.nguyenthanhxuan.quanlychitieubaocao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

/**
 * Created by Nguyen Thanh Xuan on 4/6/2018.
 */

public class ThuChiMainActivity extends Activity {
    private Button themGD;
    private Button exit;
    DatabaseHandler db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHandler(this);
        db.open();
        setContentView(R.layout.activity_thu_chi);
        themGD = (Button) findViewById(R.id.imgadd);
        exit=(Button)findViewById(R.id.quit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        themGD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(ThuChiMainActivity.this,
                        ThemCacKhoanThuChiCaNhanActivity.class);
                startActivity(i);
            }
        });
    }
}
