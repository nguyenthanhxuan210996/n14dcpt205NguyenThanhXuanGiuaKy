package com.example.nguyenthanhxuan.quanlychitieubaocao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Nguyen Thanh Xuan on 4/5/2018.
 */

public class DangNhapActivity extends AppCompatActivity {
    private String pin_mac_dinh="12345";
    private EditText etPinCu;
    private EditText etPinMoi;
    private EditText etPinReset;
    private Button btnPin;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        final EditText etLogin=(EditText)findViewById(R.id.etLogin);
        Button btnLogin=(Button)findViewById(R.id.btnLogin);
        Button btnReset=(Button)findViewById(R.id.btnReset);
        preferences=getSharedPreferences("account",MODE_PRIVATE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String storedPin=preferences.getString("xuan","12345");
                if(etLogin.getText().toString().equals(storedPin)){
                    Intent i=new Intent(DangNhapActivity.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    etLogin.setError("Bạn nhập sai mã Pin, mời bạn nhập lại");
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogPin();
            }
        });
        etLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if((event.getAction()==KeyEvent.ACTION_DOWN)&&(keyCode==KeyEvent.KEYCODE_ENTER)){
                    String storedPin=preferences.getString("xuan","12345");
                    if(etLogin.getText().toString().equals(storedPin)){
                        Intent i=new Intent(DangNhapActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        etLogin.setError("Bạn nhập sai mã Pin, mời bạn nhập lại");
                    }
                    return true;
                }
                return false;
            }
        });
    }
    private void showDialogPin(){
        AlertDialog.Builder alertBuidler=new AlertDialog.Builder(this);
        LayoutInflater inflater=this.getLayoutInflater();
        View dialogView =inflater.inflate(R.layout.activity_thay_doi_ma_pin,null);
        alertBuidler.setView(dialogView);
        final AlertDialog alertDialog=alertBuidler.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
        etPinCu=(EditText)alertDialog.findViewById(R.id.etold);
        etPinMoi=(EditText)alertDialog.findViewById(R.id.etnew);
        etPinReset=(EditText)alertDialog.findViewById(R.id.etrepin);
        btnPin=(Button)alertDialog.findViewById(R.id.savechange);
        btnPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strPinCu=etPinCu.getText().toString();
                String strPinMoi=etPinMoi.getText().toString();
                String strPinReset=etPinReset.getText().toString();

                editor=preferences.edit();
                String storedPin=preferences.getString("xuan","12345");
                if(strPinCu.equals("")||strPinMoi.equals("")||strPinReset.equals("")){
                    Toast.makeText(DangNhapActivity.this,"Không được để trống",Toast.LENGTH_SHORT).show();
                }else {
                    if(strPinCu.equals(storedPin)){
                        if(strPinMoi.equals(storedPin)){
                            editor.putString("xuan",strPinMoi);
                            editor.commit();
                            editor.clear();
                            Toast.makeText(DangNhapActivity.this,"PIN đã được lưu",Toast.LENGTH_SHORT).show();
                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(DangNhapActivity.this,"Pin bị sai",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(DangNhapActivity.this,"PIN cũ bị sai",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}
