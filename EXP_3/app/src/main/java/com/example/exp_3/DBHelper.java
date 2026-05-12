package com.example.exp_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context c){
        super(c,"login.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username text,password text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String u,String p){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("username",u);
        cv.put("password",p);
        db.insert("users",null,cv);

    }

    public  boolean  check(String u,String p){
        SQLiteDatabase db=getWritableDatabase();

        Cursor c= db.rawQuery(
                "select * from users where username=? and password=?",
                new String[]{u,p});
        return c.getCount()>0;

    }
}
