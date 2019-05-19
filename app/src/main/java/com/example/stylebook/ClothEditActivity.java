package com.example.stylebook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ClothEditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.closet_item_add);
        FloatingActionButton floartingdone=(FloatingActionButton)findViewById(R.id.floatingbar_done);
        floartingdone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClothEditActivity.this.finish();
            }
        });
    }
}