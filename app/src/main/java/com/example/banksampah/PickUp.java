package com.example.banksampah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;

public class PickUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up);

        // Tempatkan potongan kode untuk menampilkan gambar di sini
        Intent intent = getIntent();
        if (intent != null) {
            String imageUrl = intent.getStringExtra("IMAGE_URL");
            if (imageUrl != null && !imageUrl.isEmpty()) {
                // Tampilkan gambar menggunakan Glide atau cara lain
                ImageView imageView = findViewById(R.id.imgview1); // Gantilah dengan ID yang sesuai
                Glide.with(this).load(imageUrl).into(imageView);
            }
        }

        Toolbar toolbar = findViewById(R.id.appbar_widget_pickup);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });


        Button btnEdit = findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickUp.this, alamat.class);
                startActivity(intent);
            }
        });

        Button btnUpload = findViewById(R.id.btn_upload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PickUp.this, Uploadimg.class);
                startActivity(intent);
            }
        });
    }
}