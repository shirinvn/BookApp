package com.example.bookapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ActivityInnerpage extends AppCompatActivity {

    String title,more,img1,pagename;
    int id,layoutid;
    TextView moreDescription, txttoolbarinner;
    ImageView avatar;

    FloatingActionButton favorite;
    CollapsingToolbarLayout collapsingToolbarLayout;
    SQLiteDatabase database;
    String favoriteState;
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
        setContentView(R.layout.activity_innerpage);
        avatar=findViewById(R.id.avatar);
        moreDescription=findViewById(R.id.more_description);
        favorite=findViewById(R.id.favorite);
//        toolbarinner=findViewById(R.id.toolbarinner);
        txttoolbarinner=findViewById(R.id.txttoolbarinner);
       // collapsingToolbarLayout=findViewById(R.id.collappsing_toolbar);



        Typeface Bnazanin=Typeface.createFromAsset(MainActivity.context.getAssets(),"fonts/samim.ttf");
        moreDescription.setTypeface(Bnazanin);
        txttoolbarinner.setTypeface(Bnazanin);
       // collapsingToolbarLayout.setCollapsedTitleTypeface(Bnazanin);


        Bundle extras=getIntent().getExtras();
        if(extras !=null){

            layoutid=Integer.parseInt(extras.getString("id"));
            pagename=extras.getString("name");

            assert pagename != null;
            if (pagename.equals("first")){
                id=MainActivity.first.get(layoutid).getId();
                title=MainActivity.first.get(layoutid).getTitle();
                more=MainActivity.first.get(layoutid).getMore();
                img1=MainActivity.first.get(layoutid).getImg1();
            }
            else  if (pagename.equals("moq")){
                id=MainActivity.moqadame.get(layoutid).getId();
                title=MainActivity.moqadame.get(layoutid).getTitle();
                more=MainActivity.moqadame.get(layoutid).getMore();
                img1=MainActivity.moqadame.get(layoutid).getImg1();
            }else  if (pagename.equals("fav")) {
                id = MainActivity.fav.get(layoutid).getId();
                title = MainActivity.fav.get(layoutid).getTitle();
                more = MainActivity.fav.get(layoutid).getMore();
                img1 = MainActivity.fav.get(layoutid).getImg1();

            }
        }

        txttoolbarinner.setText(title);
        txttoolbarinner.setTextColor(getResources().getColor(R.color.black));

      //  collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.white));

        int imageid = MainActivity.context.getResources().getIdentifier(img1,"drawable",MainActivity.context.getPackageName());
        avatar.setImageResource(imageid);
        moreDescription.setText(more);

        selectdb();
        if (selectFavoriteState()){
            favorite.setImageResource(R.drawable.iconsunstar);
        }else {
            favorite.setImageResource(R.drawable.iconsstar);
        }

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectFavoriteState()){
                    favorite.setImageResource(R.drawable.iconsstar);
                    updateUnFavorite();
                }else {
                    favorite.setImageResource(R.drawable.iconsunstar);
                    updateFavorite();
                }
            }
        });


        SharedPreferences perfs=getSharedPreferences("font_size",MODE_PRIVATE);
        int value=perfs.getInt("font_size",16);
        moreDescription.setTextSize(value);





        SharedPreferences perfs1=getSharedPreferences("app_day",MODE_PRIVATE);
        if ( perfs1.getBoolean("app_day",true)){

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        }else
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);






    }

    private void selectdb(){

        DataBaseHelper myydb= new  DataBaseHelper(this);
        myydb.openDatabase();
        database=myydb.getReadableDatabase();
    }


    private boolean selectFavoriteState(){
        Cursor cursor=database.rawQuery("SELECT * FROM main WHERE id=" +id,null);
        while (cursor.moveToNext()){
            favoriteState =cursor.getString(cursor.getColumnIndexOrThrow("fav"));
        }if (favoriteState.equals("1")){
            return true;
        }else {
            return false;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void updateFavorite(){


        database.execSQL("UPDATE main SET fav = 1 WHERE id = " + id);


    }

    private void updateUnFavorite(){

        database.execSQL("UPDATE main SET fav = 0 WHERE id = " + id);

    }


}
