package com.example.stylebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ClothChildActivity extends AppCompatActivity {
    public static final String CLOTH_NAME = "cloth_name";
    public static final String CLOTH_IMAGE_ID = "cloth_image_id";
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_cloth_child);
        Intent intent = getIntent();
        String clothName = intent.getStringExtra("CLOTH_NAME");
        int clothImageId=intent.getIntExtra("CLOTH_IMAGE_ID",0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        ImageView clothImageView = (ImageView) findViewById(R.id.imageView);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(clothName);
        Glide.with(this).load(clothImageId).into(clothImageView);
        FloatingActionButton editButton = (FloatingActionButton) findViewById(R.id.fab);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClothChildActivity.this, ClothEditActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
