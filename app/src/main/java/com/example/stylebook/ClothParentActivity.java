package com.example.stylebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.stylebook.db.Hoodie;
import com.example.stylebook.db.HoodieAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClothParentActivity extends AppCompatActivity {
    public static final String CLOTH_NAME = "cloth_name";
    public static final String CLOTH_IMAGE_ID = "cloth_image_id";
    private Hoodie[] clothes = {
            new Hoodie("hoodie_red", R.drawable.hoddie_red_cotton_spring),
            new Hoodie("hoodie_coffee", R.drawable.hoodie_coffee_cotton_spring),
            new Hoodie("hoodie_oliver", R.drawable.hoodie_oliver_cotton_spring),
            new Hoodie("hoodie_white", R.drawable.hoodie_white_cotton_spring)};
    private List<Hoodie> clothList = new ArrayList<>();
    private HoodieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_cloth_parent);
        Intent intent = getIntent();
        String clothName = intent.getStringExtra("CLOTH_NAME");
        int clothImageId=intent.getIntExtra("CLOTH_IMAGE_ID",0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView clothImageView = (ImageView) findViewById(R.id.clothimage_view);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(clothName);
        Glide.with(this).load(clothImageId).into(clothImageView);
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.fab_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClothParentActivity.this, ClosetAddActivity.class);
                startActivity(intent);
            }
        });
        initCloth();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleview_cloth);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new HoodieAdapter(clothList);
        recyclerView.setAdapter(adapter);
    }
    private void initCloth(){
        clothList.clear();
        for (int i=0;i<clothes.length;i++){
            clothList.add(clothes[i]);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
