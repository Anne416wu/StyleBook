package com.example.stylebook;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.stylebook.db.Cloth;
import com.example.stylebook.db.ClothAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClosetFragment extends Fragment {
    private Cloth[] clothes = {
            new Cloth("Coat", R.drawable.coat),
            new Cloth("Sweater", R.drawable.sweater),
            new Cloth("Hoodie", R.drawable.hoodie),
            new Cloth("Shirt", R.drawable.shirt),
            new Cloth("Tshirt", R.drawable.tshirt),
            new Cloth("Dress", R.drawable.dress),
            new Cloth("Jeans", R.drawable.jeans),
            new Cloth("Trouser", R.drawable.trouser),
            new Cloth("Shorts", R.drawable.shorts),
            new Cloth("Skirt", R.drawable.skirt)};
    private List<Cloth> clothList = new ArrayList<>();
    private ClothAdapter adapter;
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.closet_fragment,container,false);
        FloatingActionButton add = (FloatingActionButton)view.findViewById(R.id.fab_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ClosetAddActivity.class);
                startActivity(intent);
            }
        });
        initCloth();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycleview_cloth);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ClothAdapter(clothList);
        recyclerView.setAdapter(adapter);
        return view;

    }
    private void initCloth(){
        clothList.clear();
        for (int i=0;i<10;i++){
            clothList.add(clothes[i]);
        }
    }
}
