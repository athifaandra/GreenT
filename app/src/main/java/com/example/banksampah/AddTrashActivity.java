package com.example.banksampah;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banksampah.databinding.ActivityAddTrashBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTrashActivity extends AppCompatActivity {

    private ActivityAddTrashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddTrashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String pathReference = intent.getStringExtra("trashType");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference organicItemsRef = database.getReference(pathReference);

        binding.btnSave.setOnClickListener(v -> {
            OrganicItem newItem = new OrganicItem();
            newItem.setTrashName(binding.etTrash.getText().toString());
            organicItemsRef.push().setValue(newItem)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            finish();
                            Log.d("Firebase", "Data added successfully");
                            Toast.makeText(AddTrashActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            // There was an error saving the data
                            Exception exception = task.getException();
                            if (exception != null) {
                                Log.e("Firebase", "Error adding data: " + exception.getMessage());
                                Toast.makeText(AddTrashActivity.this, "Error adding data: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}