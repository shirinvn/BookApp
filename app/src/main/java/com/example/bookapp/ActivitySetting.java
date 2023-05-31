package com.example.bookapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class ActivitySetting extends AppCompatActivity {


    SeekBar slider;
    TextView sample, txttitle,day,night,titletext;
    SharedPerf sharedPerf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPerf=new SharedPerf(this);
        if (sharedPerf.LoadNightModeState()) {

            setTheme(R.style.Darkmode);
        }else {
            setTheme(R.style.Testapp);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        slider =findViewById(R.id.slider);
        sample = findViewById(R.id.sample);
        txttitle=findViewById(R.id.settingtxt);
        day=findViewById(R.id.day);
        night=findViewById(R.id.night);

        titletext=findViewById(R.id.txtFontSizeTitle);

        Typeface Bnazanin=Typeface.createFromAsset(MainActivity.context.getAssets(),"fonts/samim.ttf");

        sample.setTypeface(Bnazanin);
        txttitle.setTypeface(Bnazanin);
        day.setTypeface(Bnazanin);
        night.setTypeface(Bnazanin);
        titletext.setTypeface(Bnazanin);





        //Night mode
        final SwitchMaterial switchnight=findViewById(R.id.switchnight);
        if (sharedPerf.LoadNightModeState()){
            switchnight.setChecked(true);
        }
        switchnight.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                sharedPerf.setNightModeState(true);
                reastartApp();
            } else
                sharedPerf.setNightModeState(false);
            reastartApp();
        });


        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int newValue, boolean b) {

                sample.setTextSize((float) newValue);
                SharedPreferences.Editor editor=getSharedPreferences("font_size",MODE_PRIVATE).edit();
                editor.putInt("font_size",newValue);
                editor.apply();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //stay activity
        final  SwitchMaterial switchStay= findViewById(R.id.switchstayday);
        SharedPreferences shaaerd=getPreferences(Context.MODE_PRIVATE);
        boolean highsScore=shaaerd.getBoolean("appps",false);
        switchStay.setChecked(highsScore);
        switchStay.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (switchStay.isChecked()){

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                Toast.makeText(ActivitySetting.this,"حالت روشن فعال شد",Toast.LENGTH_LONG).show();

                SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editoor = sharedPreferences.edit();
                editoor.putBoolean("appps", true);
                editoor.apply();

                SharedPreferences.Editor editor=getSharedPreferences("app_day",MODE_PRIVATE).edit();
                editor.putBoolean("app_day",isChecked);
                editor.apply();



            } else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
                Toast.makeText(ActivitySetting.this,"حالت روشن غير فعال شد",Toast.LENGTH_LONG).show();

                SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editoor = sharedPreferences.edit();
                editoor.putBoolean("appps", false);
                editoor.apply();

                SharedPreferences.Editor editor=getSharedPreferences("app_day",MODE_PRIVATE).edit();
                editor.putBoolean("app_day",isChecked);
                editor.apply();


            }
        });

    }




    //Nighht mode
    public void reastartApp(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
        finish();
    }



    @Override
    public void onBackPressed() {
        startActivity( new Intent (ActivitySetting.this,MainActivity.class));
        // super.onBackPressed();

    }

}