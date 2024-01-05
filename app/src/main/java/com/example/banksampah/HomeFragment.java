package com.example.banksampah;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerViewMenu3;
    RecyclerView recyclerViewMenu2;
    ArrayList<SetterGetter> menuData;
    ArrayList<SetterGetter2> menuData2;
    HomeAdapter adapterMenu;
    HomeAdapter2 adapterMenu2;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewMenu3 = view.findViewById(R.id.rv_menu3);
        recyclerViewMenu2 = view.findViewById(R.id.rv_menu2);

        setupMenuRecyclerView();
        setupMenu2RecyclerView();

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
}
