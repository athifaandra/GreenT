package com.example.banksampah;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class alamat extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText nohp, alamat, kecamatan, kelurahan, kodepos;
    private ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progressBarAlamat);


        mDatabase = FirebaseDatabase.getInstance().getReference("alamats");
    }

    public void saveChanges(View view) {
        writeNewUser();
        progressBar.setVisibility(View.VISIBLE);
    }

    public void writeNewUser() {
        alamatsg alamatsege = new alamatsg(nohp.getText().toString(),
                alamat.getText().toString(),
                kecamatan.getText().toString(),
                kelurahan.getText().toString(),
                kodepos.getText().toString()
        );

        mDatabase.setValue(alamatsege).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                finish();
                Toast.makeText(alamat.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            } else {
                // There was an error saving the data
                Exception exception = task.getException();
                if (exception != null) {
                    Log.e("Firebase", "Error adding data: " + exception.getMessage());
                    Toast.makeText(alamat.this, "Error adding data: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}