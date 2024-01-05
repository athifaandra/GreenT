package com.example.banksampah;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private ArrayList<SetterGetter> listdata;
    private Context context;

    public HomeAdapter(Context context, ArrayList<SetterGetter> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        final SetterGetter getData = listdata.get(position);
        String titlemenu = getData.getTitle();
        String logomenu = getData.getImg();

        holder.titleMenu.setText(titlemenu);
        if (logomenu.equals("img_trash")) {
            holder.imgMenu.setImageResource(R.drawable.icons8_trash_100);
        } else if (logomenu.equals("img_recycle")) {
            holder.imgMenu.setImageResource(R.drawable.icons8_recycle_100);
        } else if (logomenu.equals("img_info")) {
            holder.imgMenu.setImageResource(R.drawable.icons8_information_100);
        }

        holder.parentLayout.setOnClickListener(v -> {
            switch (listdata.get(position).getTitle()) {
                case "Recycle":
                    Intent intent = new Intent(context, Recycle.class);
                    context.startActivity(intent);
                    break;
                case "Type of Trash":
                    Intent typeintent = new Intent(context, TypeTrash.class);
                    context.startActivity(typeintent);
                    break;
                case "Information":
                    Intent infoIntent = new Intent(context, Information.class);
                    context.startActivity(infoIntent);
                    break;
                default:
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class HomeHolder extends RecyclerView.ViewHolder {

        TextView titleMenu;
        ImageView imgMenu;
        LinearLayout parentLayout;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);

            titleMenu = itemView.findViewById(R.id.tvtxt_menu);
            imgMenu = itemView.findViewById(R.id.ivlogo_menu);
            parentLayout = itemView.findViewById(R.id.parentLayout);
        }
    }
}
