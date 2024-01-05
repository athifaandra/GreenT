package com.example.banksampah;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recycle extends AppCompatActivity {

    RecyclerView recyclerView3;
    ArrayList<SetterGetter3> datamenu3;
    GridLayoutManager gridLayoutManager;
    RecycleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        Toolbar toolbar = findViewById(R.id.appbar_widget_recycle);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        recyclerView3 = findViewById(R.id.rv_recycle);

        addData3();
        gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView3.setLayoutManager(gridLayoutManager);
        adapter = new RecycleAdapter(datamenu3);
        recyclerView3.setAdapter(adapter);
    }

    public void addData3(){
        datamenu3 = new ArrayList<>();
        datamenu3.add(new SetterGetter3("Vegetables/Leave","SampahOrganik","Compost","Pupuk","Panah1"));
        datamenu3.add(new SetterGetter3("Plastic Bottle", "BotolPlastik","Plastic Pot", "PotPlastik","Panah2"));
        datamenu3.add(new SetterGetter3("Plastic Bags", "KantongPlastik","Plastic Flower","BungaPlastik","Panah3"));
        datamenu3.add(new SetterGetter3("Plastic Spoon","SendokPlastik","Plastic Spoons Lantern","Lampion","Panah4"));
    }
}