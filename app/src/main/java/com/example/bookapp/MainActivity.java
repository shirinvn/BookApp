package com.example.bookapp;

import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;


public class MainActivity extends RuntimePermissionsActivity {
    public static ArrayList<Structure> moqadame= new ArrayList<>();
    public static ArrayList<Structure> first= new ArrayList<>();
    public static ArrayList<Structure> fav= new ArrayList<>();
    SQLiteDatabase database;
    MeowBottomNavigation btnnavigation;

    FrameLayout frameLayout;
    public static Context context;
    CardView cardView;
    TextView toolbartxt;
    NavigationView navigationView;
    ImageView hamburger;
    DrawerLayout drawerLayout;
    SharedPerf sharedPerf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Night mode
        sharedPerf=new SharedPerf(this);
        if (sharedPerf.LoadNightModeState()) {

            setTheme(R.style.Darkmode);
        }else {
            setTheme(R.style.Testapp);
        }
        //end Night mode
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity_main);
        context=getApplicationContext();

        navigationView=findViewById(R.id.navigation_view);
        drawerLayout=findViewById(R.id.navigation_drawer);
        hamburger=findViewById(R.id.menu_navigation);
        btnnavigation=findViewById(R.id.bottom_navigation);
        frameLayout=findViewById(R.id.viewpager);
        cardView=findViewById(R.id.cardview1);
        toolbartxt=findViewById(R.id.toolbartxt);
        int READ_EXTERNAL = 20;
        MainActivity.super.requestAppPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL);
        int WRITE_EXTERNAL = 30;
        MainActivity.super.requestAppPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL);
        Typeface Bnazanin=Typeface.createFromAsset(MainActivity.context.getAssets(),"fonts/samim.ttf");
        toolbartxt.setTypeface(Bnazanin);
        selectfav();
        selectmoqadame();
        selectfirst();



        SharedPreferences perfs1=getSharedPreferences("app_day",MODE_PRIVATE);
        if ( perfs1.getBoolean("app_day",true)){

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }else
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        //bottom_navigation
        btnnavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_favorite_24));
        btnnavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_baseline_home_24));
        btnnavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_baseline_menu_book_24));

        btnnavigation.setOnClickMenuListener(item -> {

        });


        btnnavigation.setOnReselectListener(item -> Toast.makeText(context, "الان در اين آيتم هستيد", Toast.LENGTH_LONG).show());
        btnnavigation.setOnShowListener(item -> {

            switch (item.getId()){
                case 1:
                    setfragment(new FavFragment()) ;
                    break;
                case 2:
                    setfragment(new MoqadameFragment());
                    break;
                case 3:
                    setfragment(new FirstFragment()) ;

                    break;
            }
        });
        btnnavigation.show(2,true);



        hamburger.setOnClickListener(v -> drawerLayout.openDrawer(Gravity.RIGHT));



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.call){
                    SendEmail();
                }


                if (id == R.id.setting){
                    Intent intent= new Intent(MainActivity.this,ActivitySetting.class);
                    MainActivity.this.startActivity(intent);
                    finish();


                }
                if (id== R.id.about){
                    Intent intent= new Intent(MainActivity.this,Abute.class);
                    MainActivity.this.startActivity(intent);
                    finish();


                }
                return true;
            }
        });


    }

    private void SendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "appiiinfoo@gmail.com", null));
        intent.putExtra("android.intent.extra.SUBJECT", "Send From Application Android");
        startActivity(Intent.createChooser(intent, "لطفا انتخاب کنید...  :"));
        finish();
    }
    @Override
    protected void onResume() {

        super.onResume();

        // if (!fav.isEmpty()){
        fav.clear();
        selectfav();

        //  }else if (! first.isEmpty()){
        first.clear();
        selectfirst();
        //  }else if (!moqadame.isEmpty()){
        moqadame.clear();
        selectmoqadame();
        //  }
    }


    @Override
    public void onPermissionsGranted(int requestCode) {
        //  if (requestCode == READ_EXTERNAL)
        //  Toast.makeText(getApplicationContext()," READ its OK",Toast.LENGTH_SHORT).show();
        //   else if (requestCode == WRITE_EXTERNAL)
        //   Toast.makeText(getApplicationContext()," WRITE its  OK",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDeny(int requestCode) {
        // Toast.makeText(getApplicationContext(),"its NOT OK",Toast.LENGTH_LONG).show();

    }

    private void setfragment(Fragment fragment){

        FragmentTransaction fm= getSupportFragmentManager()
                .beginTransaction();
        fm.replace(R.id.viewpager,fragment);
        fm.commit();
    }


    public void selectmoqadame(){

        DataBaseHelper myydb= new  DataBaseHelper(this);
        myydb.createDatabase();
        myydb.openDatabase();
        database=myydb.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM main WHERE subject ='moqadame'",null);
        while (cursor.moveToNext()) {
            String tittle = cursor.getString(cursor.getColumnIndexOrThrow("tittle"));
            String more = cursor.getString(cursor.getColumnIndexOrThrow("more"));
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String subject = cursor.getString(cursor.getColumnIndexOrThrow("subject"));
            String img1 = cursor.getString(cursor.getColumnIndexOrThrow("img1"));
            Structure struct = new Structure(tittle, more, subject, img1, id);
            struct.setImg1(img1);
            struct.setTitle(tittle);
            struct.setSubject(subject);
            struct.setMore(more);
            struct.setId(id);
            moqadame.add(struct);

        }
       cursor.close();
        database.close();
        myydb.closeDataBase();

    }


    public void selectfav(){
        DataBaseHelper myydb= new  DataBaseHelper(this);
        myydb.createDatabase();
        myydb.openDatabase();
        database=myydb.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM main WHERE fav =1 ",null);
        while (cursor.moveToNext()){
            String tittle=cursor.getString(cursor.getColumnIndexOrThrow("tittle"));
            String more=cursor.getString(cursor.getColumnIndexOrThrow("more"));
            int id=cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String subject=cursor.getString(cursor.getColumnIndexOrThrow("subject"));
            String img1=cursor.getString(cursor.getColumnIndexOrThrow("img1"));
            Structure struct=new Structure(tittle,more,subject,img1,id);
            struct.setImg1(img1);
            struct.setTitle(tittle);
            struct.setSubject(subject);
            struct.setMore(more);
            struct.setId(id);
            fav.add(struct);
        }
        cursor.close();
       database.close();
        myydb.closeDataBase();

    }

    public void selectfirst(){

        DataBaseHelper myydb= new  DataBaseHelper(this);
        myydb.createDatabase();
        myydb.openDatabase();
        database=myydb.getReadableDatabase();
        Cursor cursor=database.rawQuery("SELECT * FROM main WHERE subject ='first'",null);
        while (cursor.moveToNext()){
            String tittle=cursor.getString(cursor.getColumnIndexOrThrow("tittle"));
            String more=cursor.getString(cursor.getColumnIndexOrThrow("more"));
            int id=cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String subject=cursor.getString(cursor.getColumnIndexOrThrow("subject"));
            String img1=cursor.getString(cursor.getColumnIndexOrThrow("img1"));

            Structure struct=new Structure(tittle,more,subject,img1,id);
            struct.setImg1(img1);
            struct.setTitle(tittle);
            struct.setSubject(subject);
            struct.setMore(more);
            struct.setId(id);
            first.add(struct);
        }
        cursor.close();
      database.close();
       myydb.closeDataBase();


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("از برنامه خارج میشی؟");
        alertDialogBuilder
                .setMessage("برای خروج بله رو بزن !")
                .setCancelable(false)
                .setPositiveButton("بله",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        })

                .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}