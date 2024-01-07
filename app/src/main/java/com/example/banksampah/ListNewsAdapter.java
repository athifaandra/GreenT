package com.example.banksampah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsAdapter.ListViewHolder> {
    private ArrayList<News> listNews;
    private OnItemClickListener onItemClickListener;
    private String currentUserID;

    public ListNewsAdapter(ArrayList<News> list, String currentUserID) {
        this.listNews = list;
        this.currentUserID = currentUserID;
    }

    public void setCurrentUserID(String currentUserID) {
        this.currentUserID = currentUserID;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        News newsItem = listNews.get(position);
        holder.title.setText(newsItem.getTitle());
        holder.detail.setText(newsItem.getDetail());
        holder.authorEmail.setText(newsItem.getUserEmail());

        Glide.with(holder.itemView.getContext())
                .load(newsItem.getPhotoUrl())
                .into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(newsItem, currentUserID);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, detail, authorEmail;

        public ListViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_news);
            title = itemView.findViewById(R.id.title_news);
            detail = itemView.findViewById(R.id.detail_news);
            authorEmail = itemView.findViewById(R.id.text_author_email);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(News news, String currentUserID);
    }
}
