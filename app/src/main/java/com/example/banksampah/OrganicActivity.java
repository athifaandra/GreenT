package com.example.banksampah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.banksampah.databinding.ActivityOrganicBinding;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OrganicActivity extends AppCompatActivity implements OrganicListAdapter.OnCheckedChangeListener {


    private ActivityOrganicBinding binding;
    private OrganicListAdapter organicListAdapter;
    private List<OrganicItem> organicItemList;
    private DatabaseReference mDatabase;
    private int totalKg = 0;
    private ArrayList<String> dataType;
    private String pathReference = "organic_items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrganicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.pbLoading.setVisibility(View.VISIBLE);

        Intent intent = getIntent();
        pathReference = intent.getStringExtra("trashType");

        dataType = new ArrayList<>();

        updateKg();
        initListener();

        mDatabase = FirebaseDatabase.getInstance().getReference(pathReference);


        // Initialize RecyclerView
        binding.recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));

        // Initialize and set up data for the adapter
        organicItemList = new ArrayList<>();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                organicItemList.clear();
                for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                    OrganicItem organicItem = itemSnapshot.getValue(OrganicItem.class);


                    organicItemList.add(organicItem);
                }
                organicListAdapter.notifyDataSetChanged();
                binding.pbLoading.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OrganicActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                binding.pbLoading.setVisibility(View.GONE);
            }
        });


        // Initialize the adapter
        organicListAdapter = new OrganicListAdapter(this, organicItemList, this);

        // Set the adapter to the RecyclerView
        binding.recyclerViewItems.setAdapter(organicListAdapter);
    }

    private void initListener() {
        binding.arrowBack.setOnClickListener( v -> {
            finish();
        });

        binding.btnPlus.setOnClickListener(v -> {
            totalKg += 1;
            updateKg();
        });

        binding.btnMinus.setOnClickListener(v -> {
            if (totalKg > 0) {
                totalKg -= 1;
                updateKg();
            }
        });

        binding.btnSave.setOnClickListener(v -> {
            Intent intent = new Intent(this, PickUp.class);
            intent.putExtra("organicKg", totalKg);
            intent.putStringArrayListExtra("organicType", dataType);
            startActivity(intent);
            finish();
        });

        binding.btnAddItem.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddTrashActivity.class);
            intent.putExtra("trashType", pathReference);
            startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateKg() {
        binding.weightTextView.setText(totalKg + " Kg");
    }

    @Override
    public void onCheckedChanged(OrganicItem organicItem, boolean isChecked) {
        if (isChecked) {
            dataType.add(organicItem.getTrashName());
        } else {
            dataType.remove(organicItem.getTrashName());
        }
    }
}