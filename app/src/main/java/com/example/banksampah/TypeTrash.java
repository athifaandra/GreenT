package com.example.banksampah;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TypeTrash extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SampahAdapter sampahAdapter;
    private ArrayList<TipeSampah> jenisSampah;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.type_trash);

        Toolbar toolbar = findViewById(R.id.appbar_widget_type);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        getData();

        recyclerView = findViewById(R.id.rv_type);
        sampahAdapter = new SampahAdapter(jenisSampah);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TypeTrash.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sampahAdapter);

    }

    private void getData() {
        jenisSampah = new ArrayList<>();
        jenisSampah.add(new TipeSampah("Container for organic food waste. Later this waste can be used as compost.", R.drawable.type1));
        jenisSampah.add(new TipeSampah("With this trash can it can be easier to use it as a recycled craft or recycling in the factory.", R.drawable.type2));
        jenisSampah.add(new TipeSampah("This type of waste must be separated because\n" + "it usually comes from B3 (Bahan  Berbahaya Beracun).", R.drawable.type3));
        jenisSampah.add(new TipeSampah("This type of trash can is intended for residual waste. Non-recyclable waste. such as diapers, cigarette butts, styrofoam and used tissues", R.drawable.type4));
    }
}