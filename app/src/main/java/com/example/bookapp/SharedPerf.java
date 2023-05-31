package com.example.bookapp;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class SharedPerf extends AppCompatActivity {
    SharedPreferences sharedP;

    public SharedPerf(Context context){
       sharedP=context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }

    public void setNightModeState (Boolean state){
        SharedPreferences.Editor editor=sharedP.edit();
        editor.putBoolean("Nightmode",state);
        editor.apply();

    }


    public Boolean LoadNightModeState(){
        Boolean state= sharedP.getBoolean("Nightmode",false);
        return state;

    }
}

