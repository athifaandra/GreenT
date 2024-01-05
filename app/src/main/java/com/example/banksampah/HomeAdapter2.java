package com.example.banksampah;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter2 extends RecyclerView.Adapter<HomeAdapter2.HomeHolder2> {

    private ArrayList<SetterGetter2> listdata2;

    public HomeAdapter2(ArrayList<SetterGetter2> listdata2) {
        this.listdata2 = listdata2;
    }

    @NonNull
    @Override
    public HomeHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home2, parent, false);
        return new HomeHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder2 holder, final int position) {
        final SetterGetter2 getData = listdata2.get(position);
        String titlemenu2 = getData.getTitle2();
        final String logomenu2 = getData.getImg2();
        String descmenu = getData.getDesc();

        holder.titleMenu2.setText(titlemenu2);
        if (logomenu2.equals("img_pickup")) {
            holder.imgMenu2.setImageResource(R.drawable.icons8_pickup_100);
            holder.desC.setText("We will pick up your trash");
            holder.panah.setImageResource(R.drawable.icons8_arrow_100);
        } else if (logomenu2.equals("img_dropoff")) {
            holder.imgMenu2.setImageResource(R.drawable.icons8_breakable_100);
            holder.desC.setText("Drop Off your Trash");
            holder.panah.setImageResource(R.drawable.icons8_arrow_100);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logomenu2.equals("img_pickup")) {
                    Intent pickupIntent = new Intent(v.getContext(), PickUp.class);
                    v.getContext().startActivity(pickupIntent);
                } else if (logomenu2.equals("img_dropoff")) {
                    Intent dropoffIntent = new Intent(v.getContext(), Dropoff.class);
                    v.getContext().startActivity(dropoffIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata2.size();
    }

    public class HomeHolder2 extends RecyclerView.ViewHolder {
        TextView titleMenu2, desC;
        ImageView imgMenu2, panah;

        public HomeHolder2(@NonNull View itemView) {
            super(itemView);
            titleMenu2 = itemView.findViewById(R.id.tvtxt_menu2);
            imgMenu2 = itemView.findViewById(R.id.ivlogo_menu2);
            panah = itemView.findViewById(R.id.panah);
            desC = itemView.findViewById(R.id.desc_menu);
        }
    }
}
