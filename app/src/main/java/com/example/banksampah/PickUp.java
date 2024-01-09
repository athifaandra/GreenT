package com.example.banksampah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.banksampah.databinding.ActivityPickUpBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PickUp extends AppCompatActivity {

    private ActivityPickUpBinding binding;
    private ArrayList<String> dataType;
    private int totalKg = 0;
    private int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the binding
        binding = ActivityPickUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataType = new ArrayList<>();

        getData();

        if (savedInstanceState != null) {
            // Restore the saved user input
            String phone = savedInstanceState.getString("phone");
            String address = savedInstanceState.getString("address");
            String date = savedInstanceState.getString("date");
            String time = savedInstanceState.getString("time");
            totalKg = savedInstanceState.getInt("organicKg");

            binding.inputNoTelp.setText(phone);
            binding.inputAddress.setText(address);
            binding.inputTanggal.setText(date);
            binding.inputWaktu.setText(time);
        }

//        // Tempatkan potongan kode untuk menampilkan gambar di sini
//        Intent intent = getIntent();
//        if (intent != null) {
//            String imageUrl = intent.getStringExtra("IMAGE_URL");
//            if (imageUrl != null && !imageUrl.isEmpty()) {
//                // Tampilkan gambar menggunakan Glide atau cara lain
//                ImageView imageView = findViewById(R.id.imgview1); // Gantilah dengan ID yang sesuai
//                Glide.with(this).load(imageUrl).into(imageView);
//            }
//        }

        Toolbar toolbar = findViewById(R.id.appbar_widget_pickup);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        initData();
        retrieveIntValue();


        Button btnEdit = findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickUp.this, alamat.class);
                startActivity(intent);
            }
        });

//        Button btnUpload = findViewById(R.id.btn_upload);
//        btnUpload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(PickUp.this, Uploadimg.class);
//                startActivity(intent);
//            }
//        });

        binding.btnOrganic.setOnClickListener(v -> {
            Intent organicIntent = new Intent(this, OrganicActivity.class);
            organicIntent.putExtra("trashType", "organic_items");
            startActivity(organicIntent);
        });

        binding.btnAnorganic.setOnClickListener(v -> {
            Intent organicIntent = new Intent(this, OrganicActivity.class);
            organicIntent.putExtra("trashType", "anorganic_items");
            startActivity(organicIntent);
        });

        binding.btnNext.setOnClickListener(v -> {
            Intent payIntent = new Intent(this, PaymentActivity.class);

            String phone = binding.inputNoTelp.getText().toString();
            String address = binding.inputAddress.getText().toString();
            String date = binding.inputTanggal.getText().toString();
            String time = binding.inputWaktu.getText().toString();
            payIntent.putExtra("telepon", phone);
            payIntent.putExtra("address", address);
            payIntent.putExtra("date", date);
            payIntent.putExtra("time", time);
            payIntent.putExtra("organicKg", totalKg);
            payIntent.putStringArrayListExtra("organicType", dataType);
            startActivity(payIntent);
            finish();
        });
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<String> yourArrayList = intent.getStringArrayListExtra("organicType");
            totalKg = intent.getIntExtra("organicKg", 0);

            if (yourArrayList != null) {
                dataType = yourArrayList;
            }
        }

        totalPrice = 2500 * totalKg;
    }

    private void retrieveIntValue() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("alamats");
        // Retrieve the int value from Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get the int value
                    alamatsg dataAlamat = dataSnapshot.getValue(alamatsg.class);
                    String address = dataAlamat.getAddress() + ", " + dataAlamat.getSubdis() + ", " + dataAlamat.getWard() + ", " + dataAlamat.getPostalcode();
                    binding.inputAddress.setText(address);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors if needed]
                Toast.makeText(PickUp.this, "Error get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        binding.btnNext.setText("Total " + totalPrice + " | " + totalKg + " Kg" + "\n Next");
        Log.d("dsdasda", String.valueOf(dataType.size()));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        String phone = binding.inputNoTelp.getText().toString();
        String address = binding.inputAddress.getText().toString();
        String date = binding.inputTanggal.getText().toString();
        String time = binding.inputWaktu.getText().toString();
        outState.putString("telepon", phone);
        outState.putString("address", address);
        outState.putString("date", date);
        outState.putString("time", time);
        outState.putInt("organicKg", totalKg);
    }
}