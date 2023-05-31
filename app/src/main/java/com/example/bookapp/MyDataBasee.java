package com.example.bookapp;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class MyDataBasee extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "mydb.db";
    public static final int DATABASE_VERSION = 2;


    public MyDataBasee(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


}

