package com.caoductin.ca2database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {
    public static final String DB_Name = "product.db";
    public static final String tbl_name = "phone";
    public static final int Db_version = 1;
    public static final String col_ma = "masp";
    public static final String col_ten = "tensp";
    public static final String col_price = "giasp";


    public database(@Nullable Context context) {
        super(context, DB_Name, null, Db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table if not exists " + tbl_name + "(" + col_ma + " Integer primary key," + col_ten + " nvarchar(20), " + col_price + " real)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if  exists " + tbl_name);
        onCreate(db);
    }

    //readable data
    public Cursor querydata(String sql) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql, null);

    }

    //insert update , insert
    public void exec(String sql) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);

    }

    public int getRowNumber() {
        Cursor cursor = querydata("Select * from " + tbl_name);
        int numofrow = cursor.getCount();
        cursor.close();
        return numofrow;
    }

    public void CreateSampleData() {
        if (getRowNumber() == 0) {
            try {
                exec("Insert into " + tbl_name + " values(1,'iphone SE',6000)");
                exec("Insert into " + tbl_name + " values(3,'iphone 5',6000)");
                exec("Insert into " + tbl_name + " values(2,'iphone 6',7000)");
            } catch (Exception ex) {
                Log.e("error", ex.getMessage().toString());
            }
        }
    }
}