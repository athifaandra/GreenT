package com.example.banksampah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsFragment extends Fragment {

    private ListNewsAdapter listNewsAdapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = rootView.findViewById(R.id.rv_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        listNewsAdapter = new ListNewsAdapter(NewsData.getListData());
        recyclerView.setAdapter(listNewsAdapter);

        listNewsAdapter.setOnItemClickListener(new ListNewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(News data) {
                Intent moveIntent = new Intent(requireContext(), DetailNewsActivity.class);
                moveIntent.putExtra(DetailNewsActivity.ITEM_EXTRA, data);
                startActivity(moveIntent);

                Log.d("NewsFragment", "Data yang dikirim: " + data.getTitle());
            }
        });

        return rootView;
    }
}