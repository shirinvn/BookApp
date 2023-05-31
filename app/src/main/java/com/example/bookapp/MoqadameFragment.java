package com.example.bookapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MoqadameFragment extends Fragment {

    RecyclerView recyclerView;
    AdapterFav adapterFav;
    int mpage;
    public static final String ARG_PAGE="ARG_PAGE";



    public static MoqadameFragment newInstance(int page){
        Bundle args=new Bundle();
        args.putInt(ARG_PAGE,page);
        MoqadameFragment fragment= new MoqadameFragment();
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
        View view =inflater.inflate(R.layout.fragment_moqadame,container,false);

            recyclerView =view.findViewById(R.id.recyclerview);
            AdapterMogadame adapter = new AdapterMogadame(MainActivity.context);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.context));

        return  view;

    }


}