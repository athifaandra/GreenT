package com.example.banksampah;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerViewMenu3;
    RecyclerView recyclerViewMenu2;
    ArrayList<SetterGetter> menuData;
    ArrayList<SetterGetter2> menuData2;
    HomeAdapter adapterMenu;
    HomeAdapter2 adapterMenu2;
    private DatabaseReference databaseReference;
    TextView uangText;
    ProgressBar progressBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewMenu3 = view.findViewById(R.id.rv_menu3);
        recyclerViewMenu2 = view.findViewById(R.id.rv_menu2);
        uangText = view.findViewById(R.id.uang);
        progressBar = view.findViewById(R.id.progressBar);

        ImageView soCuteImageView = view.findViewById(R.id.cute);
        soCuteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementasi untuk kembali ke halaman login
                // Misalnya, menggunakan Intent untuk kembali ke halaman login
                Intent intent = new Intent(requireContext(), login.class);
                startActivity(intent);
                requireActivity().finish();
            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("user_income");

        setupMenuRecyclerView();
        setupMenu2RecyclerView();

        retrieveIntValue();

        return view;
    }

    private void setupMenuRecyclerView() {
        menuData = new ArrayList<>();
        menuData.add(new SetterGetter("Type of Trash", "img_trash"));
        menuData.add(new SetterGetter("Recycle", "img_recycle"));
        menuData.add(new SetterGetter("Information", "img_info"));

        adapterMenu = new HomeAdapter(requireContext(), menuData);
        recyclerViewMenu3.setLayoutManager(new GridLayoutManager(requireContext(), 3));
        recyclerViewMenu3.setAdapter(adapterMenu);
    }

    private void setupMenu2RecyclerView() {
        menuData2 = new ArrayList<>();
        menuData2.add(new SetterGetter2("Pick Up", "img_pickup", "panah1", "We will pick up your trash"));
        menuData2.add(new SetterGetter2("Drop Off", "img_dropoff", "panah2", "Drop off your trash"));

        adapterMenu2 = new HomeAdapter2(menuData2);
        recyclerViewMenu2.setLayoutManager(new GridLayoutManager(requireContext(), 1));
        recyclerViewMenu2.setAdapter(adapterMenu2);
    }

    private void retrieveIntValue() {
        progressBar.setVisibility(View.VISIBLE);
        // Retrieve the int value from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get the int value
                    String uangData = dataSnapshot.getValue(String.class);
                    uangText.setText(uangData);
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors if needed
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error get data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
