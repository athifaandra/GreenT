package com.example.banksampah;

import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.RecycleHolder> {

    private ArrayList<SetterGetter3> listdata3;

    public RecycleAdapter(ArrayList<SetterGetter3> listdata3){
        this.listdata3 = listdata3;
    }

    @NonNull
    @Override
    public RecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle,parent,false);
        RecycleHolder holder = new RecycleHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleHolder holder, int position) {
        final SetterGetter3 getData = listdata3.get(position);
        String titlekiri = getData.getTitlekiri(); //ambil dari settergetter
        String titlekanan = getData.getTitlekanan();
        String imgkiri = getData.getImgkiri();
        String imgkanan = getData.getImgkanan();
        String panah = getData.getPanah();

        holder.titleKiri.setText(titlekiri);
        if (imgkiri.equals("SampahOrganik")){
            holder.imgkiri.setImageResource(R.drawable.sampah_organik_1);
            holder.titleKanan.setText(titlekanan);
            holder.imgkanan.setImageResource(R.drawable.pupuk_1);
            holder.panah.setImageResource(R.drawable.panah_1);
        }else if(imgkiri.equals("BotolPlastik")){
            holder.imgkiri.setImageResource(R.drawable.botol_plastik_1);
            holder.titleKanan.setText(titlekanan);
            holder.imgkanan.setImageResource(R.drawable.pot_plastik_1);
            holder.panah.setImageResource(R.drawable.panah_1);
        }else if(imgkiri.equals("KantongPlastik")){
            holder.imgkiri.setImageResource(R.drawable.kantong_plastik_1);
            holder.titleKanan.setText(titlekanan);
            holder.imgkanan.setImageResource(R.drawable.bunga_plastik_1);
            holder.panah.setImageResource(R.drawable.panah_1);
        }else if(imgkiri.equals("SendokPlastik")){
            holder.imgkiri.setImageResource(R.drawable.sendok_plastik_1);
            holder.titleKanan.setText(titlekanan);
            holder.imgkanan.setImageResource(R.drawable.lampion_sendok_plastik_1);
            holder.panah.setImageResource(R.drawable.panah_1);
        }
    }

    @Override
    public int getItemCount() {
        return listdata3.size();
    }

    public class RecycleHolder extends RecyclerView.ViewHolder {

        TextView titleKiri, titleKanan;
        ImageView imgkiri, imgkanan, panah;

        public RecycleHolder(@NonNull View itemView) {
            super(itemView);

            titleKiri = itemView.findViewById(R.id.tv_titlekiri);
            titleKanan = itemView.findViewById(R.id.tv_titlekanan);
            imgkiri = itemView.findViewById(R.id.iv_imgkiri);
            imgkanan = itemView.findViewById(R.id.iv_imgkanan);
            panah = itemView.findViewById(R.id.iv_panah);
        }
    }
}
