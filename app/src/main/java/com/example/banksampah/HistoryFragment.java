package com.example.banksampah;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.banksampah.databinding.FragmentHistoryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private FragmentHistoryBinding binding;
    private HistoryAdapter historyAdapter;
    private ArrayList<PaymentItem> itemList;
    private DatabaseReference databaseReference;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize and set up data for the adapter
        itemList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("payment_history");

        binding.recyclerViewItems.setLayoutManager(new LinearLayoutManager(requireContext()));

        retrieveIntValue();
        // Initialize the adapter
        historyAdapter = new HistoryAdapter(itemList, requireContext());
        // Set the adapter to the RecyclerView
        binding.recyclerViewItems.setAdapter(historyAdapter);
    }

    private void retrieveIntValue() {
        binding.pbLoading.setVisibility(View.VISIBLE);
        // Retrieve the int value from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    PaymentItem organicItem = itemSnapshot.getValue(PaymentItem.class);
                    itemList.add(organicItem);
                }
                historyAdapter.notifyDataSetChanged();

                if(binding != null) {
                    binding.pbLoading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors if needed
                binding.pbLoading.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}