package com.example.banksampah;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private ListNewsAdapter listNewsAdapter;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = rootView.findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("News");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<News> newsList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    News news = snapshot.getValue(News.class);
                    if (news != null) {
                        newsList.add(news);
                    }
                }
                // Setelah mendapatkan data dari Firebase, atur adapter RecyclerView
                listNewsAdapter = new ListNewsAdapter(newsList);
                recyclerView.setAdapter(listNewsAdapter);

                // Tambahkan item click listener ke adapter di sini
                listNewsAdapter.setOnItemClickListener(new ListNewsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(News news) {
                        Intent intent = new Intent(requireContext(), DetailNewsActivity.class);
                        intent.putExtra(DetailNewsActivity.ITEM_EXTRA, news);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        FloatingActionButton addButton = rootView.findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), AddNews.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}
