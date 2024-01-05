package com.example.banksampah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SampahAdapter extends RecyclerView.Adapter<SampahAdapter.ViewHolder> {

    private ArrayList<TipeSampah> jenisSampah;

    public SampahAdapter(ArrayList<TipeSampah> jenisSampah) {
        this.jenisSampah = jenisSampah;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.desc_type.setText(jenisSampah.get(position).getJenisSampah());
        holder.img_type.setImageResource(jenisSampah.get(position).getLogoSampah());

    }

    @Override
    public int getItemCount() {
        return jenisSampah.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView desc_type;
        ImageView img_type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            desc_type = itemView.findViewById(R.id.desc_type1);
            img_type = itemView.findViewById(R.id.img_type1);

        }
    }
}
