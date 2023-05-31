package com.example.bookapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FirstFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterFav adapterFav;
    int mpage;
    public static final String ARG_PAGE="ARG_PAGE";



    public static FirstFragment newInstance(int page){
        Bundle args=new Bundle();
        args.putInt(ARG_PAGE,page);
        FirstFragment fragment= new FirstFragment();
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
        View view =inflater.inflate(R.layout.fragment_first,container,false);

            recyclerView = view.findViewById(R.id.recyclerview);
            AdapterFirst adapter = new AdapterFirst(MainActivity.context);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.context));


        return  view;

    }



}