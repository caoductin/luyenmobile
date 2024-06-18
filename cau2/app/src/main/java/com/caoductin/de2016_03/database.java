package com.caoductin.de2016_03;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    public static final String DB_name = "student.database";
    public static final int DB_version = 1;

    public static final String Tbl_name = "Student";
    public static final String Col_ten = "ten";
    public static final String Col_ma = "ma";
    public static final String Col_lop = "lop";





    public database(@Nullable Context context) {
        super(context, DB_name, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS "+ Tbl_name +" ("+ Col_ma +" INTEGER PRIMARY KEY, "+ Col_ten+" nvarchar(20), "+Col_lop +" nvarchar(30))";
//        String sql = "CREATE TABLE IF NOT EXISTS " + Tbl_name + " (" +
//                Col_ma + " INTEGER PRIMARY KEY, " +
//                Col_ten + " NVARCHAR(20), " +
//                Col_lop + " NVARCHAR(30))";
        db.execSQL(sql);

    }
    //select data
    public Cursor queryData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    //insert update , delete
    public void execSql(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public int getRowOfNumber(){
        Cursor cursor = queryData("Select * from "+ Tbl_name);
        int numOfRows = cursor.getCount();
        cursor.close();
        return numOfRows;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("Drop table if exists "+ Tbl_name);
    onCreate(db);
    }
    public void CreateSampleData(){
        if(getRowOfNumber() == 0){
            try{
                execSql("Insert into " + Tbl_name + " values (1,'tin','cntt_k62')");
                execSql("Insert into " + Tbl_name + " values (2,'tuan','cntt_k62')");


            }
            catch (Exception e){
                Log.e("Erorr",e.getMessage().toString());
            }
        }

    }

}
