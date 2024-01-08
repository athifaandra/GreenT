package com.example.banksampah;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = rootView.findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("News");

        SharedPreferences prefs = requireContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String currentUserID = prefs.getString("currentUserID", null);

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

                listNewsAdapter = new ListNewsAdapter(newsList, currentUserID);
                recyclerView.setAdapter(listNewsAdapter);

                listNewsAdapter.setCurrentUserID(currentUserID);
                listNewsAdapter.notifyDataSetChanged();
                
                listNewsAdapter.setOnItemClickListener((news, userId) -> {
                    if (currentUserID != null && currentUserID.equals(news.getUserId())) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                        builder.setItems(new CharSequence[]{"View Detail", "Edit News", "Delete News"}, (dialog, which) -> {
                            switch (which) {
                                case 0:
                                    Intent intent = new Intent(requireContext(), DetailNewsActivity.class);
                                    intent.putExtra(DetailNewsActivity.ITEM_EXTRA, news);
                                    startActivity(intent);
                                    break;
                                case 1:
                                    if (currentUserID.equals(news.getUserId())) {
                                        Intent editIntent = new Intent(requireContext(), AddNews.class);
                                        editIntent.putExtra("EDIT_NEWS", news);
                                        startActivity(editIntent);
                                    } else {
                                        Toast.makeText(requireContext(), "You are not permitted to edit this news", Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                case 2:
                                    deleteNews(news);
                                    break;
                            }
                        });
                        builder.show();
                    } else {
                        Intent intent = new Intent(requireContext(), DetailNewsActivity.class);
                        intent.putExtra(DetailNewsActivity.ITEM_EXTRA, news);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle onCancelled
            }
        });

        FloatingActionButton addButton = rootView.findViewById(R.id.button_add);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AddNews.class);
            startActivity(intent);
        });

        return rootView;
    }

    private void deleteNews(News news) {
        DatabaseReference newsRef = mDatabase.child(news.getId());
        newsRef.removeValue().addOnSuccessListener(aVoid -> {
            Toast.makeText(requireContext(), "News deleted successfully", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(requireContext(), "Failed to delete news: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }).addOnCanceledListener(() -> {
            Toast.makeText(requireContext(), "Deletion canceled", Toast.LENGTH_SHORT).show();
        });
    }
}
