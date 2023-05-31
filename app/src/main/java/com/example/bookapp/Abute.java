package com.example.bookapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Abute extends AppCompatActivity {

    SharedPerf sharedPerf;
    TextView abute,manabe,raheid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //Night mode
        sharedPerf=new SharedPerf(this);
        if (sharedPerf.LoadNightModeState()){
            setTheme(R.style.Darkmode);
        }else {
            setTheme(R.style.Testapp);
        }
        //end Night mode


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abute);

        abute=findViewById(R.id.abute);
        raheid=findViewById(R.id.raheid);
        manabe=findViewById(R.id.txtmanb);


        Typeface Bnazanin=Typeface.createFromAsset(MainActivity.context.getAssets(),"fonts/samim.ttf");

        abute.setTypeface(Bnazanin);
        raheid.setTypeface(Bnazanin);
        manabe.setTypeface(Bnazanin);
    }


    @Override
    public void onBackPressed() {
        startActivity( new Intent(Abute.this,MainActivity.class));


    }

}