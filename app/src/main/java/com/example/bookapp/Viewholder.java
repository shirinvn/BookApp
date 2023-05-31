package com.example.bookapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class Viewholder extends RecyclerView.ViewHolder {

    public LinearLayout cardadapter;
   public ImageView avatar;

    public TextView title;
    public Viewholder( View itemView) {
        super(itemView);
        avatar= itemView.findViewById(R.id.avatar);
        title=(TextView)itemView.findViewById(R.id.title);
        cardadapter=(LinearLayout)itemView.findViewById(R.id.card_adapter);


    }
}
