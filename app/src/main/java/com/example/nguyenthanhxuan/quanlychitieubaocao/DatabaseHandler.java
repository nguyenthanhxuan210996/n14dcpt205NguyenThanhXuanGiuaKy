package com.example.nguyenthanhxuan.quanlychitieubaocao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Thanh Xuan on 4/6/2018.
 */

public class DatabaseHandler {
    private static final String DATABASE_NAME = "quanlychitieu";
    private static final int DATABASE_ver = 1;
    static final String TABLE_NAME = "giaodich";
    static final String TABLE_NAME2 = "thuchi";
    static final String COLUM_ID = "id";
    static final String COLUM_KHOANTHUKHOANCHI = "khoanthukhoanchi";
    static final String COLUM_LOAIGIAODICH = "loaigiaodich";
    static final String COLUM_SOTIEN = "sotien";
    static final String COLUM_NOIDUNG = "noidung";
    static final String COLUM_NGAYGIAODICH = "ngaygiaodich";
    static final String COLUM_NGAY = "ngay";
    static final String COLUM_THANG = "thang";
    static final String COLUM_NAM = "nam";
    private Context _context;
    private static Context context;
    static SQLiteDatabase db;
    SQLiteDatabase db1;
    static OpenHelper openHelper;

    public DatabaseHandler(Context context) {
        DatabaseHandler.context = context;

    }

    public DatabaseHandler open() throws SQLException {
        openHelper = new OpenHelper(context);
        db = openHelper.getWritableDatabase();

        return this;

    }

    public void close() {
        openHelper.close();
    }

    public long themgiaodich(String loaigiaodich, String sotien, String noidung,String ngaygiaodich, String ngay, String thang, String nam) {
        ContentValues cv1 = new ContentValues();
        cv1.put(COLUM_LOAIGIAODICH, loaigiaodich);
        cv1.put(COLUM_SOTIEN, sotien);
        cv1.put(COLUM_NOIDUNG, noidung);
        cv1.put(COLUM_NGAYGIAODICH, ngaygiaodich);
        cv1.put(COLUM_NGAY, ngay);
        cv1.put(COLUM_THANG, thang);
        cv1.put(COLUM_NAM, nam);

        return db.insert(TABLE_NAME, null, cv1);

    }

    static class OpenHelper extends SQLiteOpenHelper {

        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_ver);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TABLE_NAME + " ( "
                    + COLUM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                    + COLUM_LOAIGIAODICH + " text,"
                    + COLUM_SOTIEN + " text,"
                    + COLUM_NOIDUNG + " text,"
                    + COLUM_NGAYGIAODICH + " text,"
                    + COLUM_NGAY + " text," + COLUM_THANG + " text,"
                    + COLUM_NAM + " text);"

            );
            db.execSQL("create table " + TABLE_NAME2 + " ( " + COLUM_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + COLUM_KHOANTHUKHOANCHI + " text);"

            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }

    }
    public List<String> getAllNames(String thuchi) {
        List<String> names = new ArrayList<String>();
        String selectQuery = "SELECT " + " FROM "
                + TABLE_NAME2 + " WHERE " + COLUM_KHOANTHUKHOANCHI + " = "
                + "'" + thuchi + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                names.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return names;
    }
    public ArrayList<ThongKeMainActivity> getloggiaodichThongkethangnay(String phanloai) {
        ArrayList<ThongKeMainActivity> names = new ArrayList<ThongKeMainActivity>();
        String selectQuery = "SELECT distinct "
                + COLUM_SOTIEN
                + " FROM "
                + TABLE_NAME
                + " WHERE "
                + COLUM_LOAIGIAODICH
                + " = "
                + "'"
                + phanloai
                + "'  and  thang = strftime('%m','now') and nam =strftime('%Y','now')";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ThongKeMainActivity thongke=new ThongKeMainActivity();
                thongke.setSotien(cursor.getString(cursor.getColumnIndex(COLUM_SOTIEN)));
                names.add(thongke);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return names;
    }
}
