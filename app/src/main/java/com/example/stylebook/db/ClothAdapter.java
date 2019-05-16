package com.example.stylebook.db;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.stylebook.ClosetFragment;
import com.example.stylebook.R;

import java.util.List;

public class ClothAdapter extends RecyclerView.Adapter<ClothAdapter.ViewHolder>{

    private static final String TAG = "ClothAdapter";

    private Context mContext;

    private List<Cloth> mClothList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView ClothImage;
        TextView ClothName;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            ClothImage = (ImageView) view.findViewById(R.id.cloth_image);
            ClothName = (TextView) view.findViewById(R.id.cloth_name);
        }
    }

    public ClothAdapter(List<Cloth> ClothList) {
        mClothList = ClothList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.cloth_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cloth Cloth = mClothList.get(position);
        holder.ClothName.setText(Cloth.getName());
        Glide.with(mContext).load(Cloth.getImageId()).into(holder.ClothImage);
    }

    @Override
    public int getItemCount() {
        return mClothList.size();
    }

}