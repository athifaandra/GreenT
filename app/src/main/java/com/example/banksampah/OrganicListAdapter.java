package com.example.banksampah;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banksampah.databinding.ListOrganicBinding;

import java.util.List;

public class OrganicListAdapter extends RecyclerView.Adapter<OrganicListAdapter.OrganicViewHolder> {

    private Context context;
    private List<OrganicItem> organicItemList;
    private OnCheckedChangeListener onCheckedChangeListener;

    public OrganicListAdapter(Context context, List<OrganicItem> organicItemList, OnCheckedChangeListener onCheckedChangeListener) {
        this.context = context;
        this.organicItemList = organicItemList;
        this.onCheckedChangeListener = onCheckedChangeListener;
    }

    @NonNull
    @Override
    public OrganicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListOrganicBinding binding = ListOrganicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrganicViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrganicViewHolder holder, int position) {
        OrganicItem organicItem = organicItemList.get(position);

        holder.binding.imageOrganic.setImageResource(organicItem.getImageResource());
        holder.binding.nameTrash.setText(organicItem.getTrashName());
        holder.binding.checkBoxItem.setChecked(organicItem.isChecked());

        // Set the listener
        holder.binding.checkBoxItem.setOnCheckedChangeListener((buttonView, isChecked) -> {
            onCheckedChangeListener.onCheckedChanged(organicItem, isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return organicItemList.size();
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(OrganicItem organicItem, boolean isChecked);
    }

    public static class OrganicViewHolder extends RecyclerView.ViewHolder {

        ListOrganicBinding binding;

        public OrganicViewHolder(@NonNull ListOrganicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}