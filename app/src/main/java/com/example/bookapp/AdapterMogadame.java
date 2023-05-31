package com.example.bookapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterMogadame extends RecyclerView.Adapter<Viewholder> {


    LinearLayout cardadapter;
    TextView title;
    ImageView avatar;
    Context context;
    LayoutInflater inflater;


    public AdapterMogadame(Context context){
        this.context=context;
        inflater=LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.adapter_card,parent,false);
        Viewholder viewholder=new Viewholder(view);
        avatar=view.findViewById(R.id.avatar);
        title=view.findViewById(R.id.title);
        cardadapter=view.findViewById(R.id.card_adapter);

        return viewholder;
    }

    @Override
    public void onBindViewHolder( Viewholder holder, int position) {
        holder.title.setText(MainActivity.moqadame.get(position).getTitle());
        String imageaddress=MainActivity.moqadame.get(position).getImg1();
        int id=MainActivity.context.getResources().getIdentifier(imageaddress,"drawable",MainActivity.context.getPackageName());
        holder.avatar.setImageResource(id);
        holder.cardadapter.setOnClickListener(clicklistener);
        holder.cardadapter.setId(position);

        Typeface Bnazanin=Typeface.createFromAsset(MainActivity.context.getAssets(),"fonts/samim.ttf");
        holder.title.setTypeface(Bnazanin);
    }

    View.OnClickListener clicklistener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.context, ActivityInnerpage.class);
            int position =view.getId();
            intent.putExtra("name","moq");
            intent.putExtra("id",position + "");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MainActivity.context.startActivity(intent);


        }
    };

    @Override
    public int getItemCount() {
        return MainActivity.moqadame.size();
    }


}