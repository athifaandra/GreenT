package com.example.banksampah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class alamat extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText nohp, alamat, kecamatan, kelurahan, kodepos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alamat);

        Toolbar toolbar = findViewById(R.id.appbar_widget_alamat);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOnBackPressedDispatcher().onBackPressed();
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextInputLayout textInputLayout = findViewById(R.id.dd_subdistric);
        MaterialAutoCompleteTextView autoCompleteTextView = findViewById(R.id.input_subdistric);


        nohp = findViewById(R.id.input_phonum_add);
        alamat = findViewById(R.id.input_add_add);
        kecamatan = findViewById(R.id.input_subdistric);
        kelurahan = findViewById(R.id.input_ward);
        kodepos = findViewById(R.id.input_add_postalcode);


        mDatabase = FirebaseDatabase.getInstance().getReference();



    }

    public void saveChanges(View view) {
        writeNewUser();

        Intent intent = new Intent(this, PickUp.class);
        startActivity(intent);
    }

    public void writeNewUser() {
        alamatsg  alamatsege = new alamatsg (nohp.getText().toString(),
                alamat.getText().toString(),
                kecamatan.getText().toString(),
                kelurahan.getText().toString(),
                kodepos.getText().toString());


        mDatabase.child("alamats").child(alamatsege.getPhonum()).setValue(alamatsege);
    }
}