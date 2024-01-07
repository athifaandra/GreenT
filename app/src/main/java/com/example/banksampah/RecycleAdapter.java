package com.example.banksampah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleViewHolder> {
    private ArrayList<RecycleItem> dataList;
    private Context context;

    public RecycleAdapter(ArrayList<RecycleItem> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycle, parent, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {

        Glide.with(context).load(dataList.get(position).getImageLeftResource()).into(holder.imageLeft);
        holder.titleLeft.setText(dataList.get(position).getTitleLeft());

        Glide.with(context).load(dataList.get(position).getImageRightResource()).into(holder.imageRight);
        holder.titleRight.setText(dataList.get(position).getTitleRight());


//        RecycleItem currentItem = recycleItemList.get(position);
//
//        holder.titleLeft.setText(currentItem.getTitleLeft());
//        holder.titleRight.setText(currentItem.getTitleRight());
//        holder.imageLeft.setImageResource(Integer.parseInt(currentItem.getImageLeftResource()));
//        holder.imageRight.setImageResource(Integer.parseInt(currentItem.getImageRightResource()));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        public TextView titleLeft, titleRight;
        public ImageView imageLeft, imageRight;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            titleLeft = itemView.findViewById(R.id.tv_titlekiri);
            titleRight = itemView.findViewById(R.id.tv_titlekanan);
            imageLeft = itemView.findViewById(R.id.iv_imgkiri);
            imageRight = itemView.findViewById(R.id.iv_imgkanan);
        }
    }
}

