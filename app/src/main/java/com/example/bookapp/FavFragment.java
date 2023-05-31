package com.example.bookapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class FavFragment  extends Fragment {

    RecyclerView recyclerView;
    AdapterFav adapterFav;
    int mpage;
    public static final String ARG_PAGE="ARG_PAGE";



    public static FavFragment newInstance(int page){
        Bundle args=new Bundle();
        args.putInt(ARG_PAGE,page);
        FavFragment fragment= new FavFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        assert getArguments() != null;
//        mpage=getArguments().getInt(ARG_PAGE);
    }


    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fav,container,false);

            recyclerView = view.findViewById(R.id.recyclerview);
            adapterFav = new AdapterFav(MainActivity.context);
            recyclerView.setAdapter(adapterFav);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.context));


        return  view;

    }


    @Override
    public void onResume() {
        super.onResume();
        if (adapterFav != null){
            adapterFav.notifyDataSetChanged();
        }
    }
}