package com.example.nguyenthanhxuan.quanlychitieubaocao;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Thanh Xuan on 4/6/2018.
 */

public class ThongKeAdapter extends ArrayAdapter<ThongKeMainActivity> {
    private Activity activity;
    private int id;
    private ArrayList<ThongKeMainActivity> list;


    public ThongKeAdapter(Activity activity, int id, ArrayList<ThongKeMainActivity> list) {
        super(activity,id,list);
        this.activity=activity;
        this.id=id;
        this.list=list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(activity);
        convertView=inflater.inflate(id,null);
        TextView txtsotien=(TextView)convertView.findViewById(R.id.txtsotien);
        txtsotien.setText(list.get(position).getSotien().toString());

        return convertView;
    }
}
