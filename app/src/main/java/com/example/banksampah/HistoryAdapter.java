package com.example.banksampah;

import static com.example.banksampah.Utils.convertMillisToDateString;
import static com.example.banksampah.Utils.formatPrice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banksampah.databinding.ItemHistoryBinding;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryItemViewHolder> {

    private final List<PaymentItem> itemList;
    private final Context context;

    public HistoryAdapter(List<PaymentItem> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemHistoryBinding binding = ItemHistoryBinding.inflate(layoutInflater, parent, false);
        return new HistoryItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, int position) {
        PaymentItem currentItem = itemList.get(position);

        long currentTimeMillis = currentItem.getDate();
        String formattedDate = convertMillisToDateString(currentTimeMillis);

        String formattedPrice = formatPrice(currentItem.getPrice());

        String berat = currentItem.getBerat()+ "    Kg";
        String totalKg = currentItem.getBerat()+ " Kg";

        holder.binding.txtDate.setText(formattedDate);
        holder.binding.txtBerat.setText(berat);
        holder.binding.txtData.setText(currentItem.getItems());
        holder.binding.txtPrice.setText(formattedPrice);
        holder.binding.txtTotal.setText(totalKg);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateData(List<PaymentItem> newData) {
        itemList.clear();
        itemList.addAll(newData);
        notifyDataSetChanged();
    }

    public static class HistoryItemViewHolder extends RecyclerView.ViewHolder {
        public ItemHistoryBinding binding;

        public HistoryItemViewHolder(ItemHistoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}