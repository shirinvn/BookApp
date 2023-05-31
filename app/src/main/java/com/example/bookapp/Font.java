package com.example.bookapp;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class Font extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("samim.ttf")
                .setFontAttrId(uk.co.chrisjenx.calligraphy.R.attr.fontPath)
                .build()
        );
    }
}


