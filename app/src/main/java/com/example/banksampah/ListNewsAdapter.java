package com.example.banksampah;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsAdapter.ListViewHolder> {
    private ArrayList<News> listNews;
    private OnItemClickListener onItemClickListener;

    public ListNewsAdapter(ArrayList<News> list) {
        this.listNews = list;
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
        News news = listNews.get(position);
        holder.title.setText(news.getTitle());
        holder.detail.setText(news.getDetail());
        holder.image.setImageResource(news.getPhoto());
    }

    @Override
    public int getItemCount() {
        return listNews.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, detail;

        public ListViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_news);
            title = itemView.findViewById(R.id.title_news);
            detail = itemView.findViewById(R.id.detail_news);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            onItemClickListener.onItemClick(listNews.get(position));
                        }
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(News data);
    }
}