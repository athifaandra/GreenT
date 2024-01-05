package com.example.banksampah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Uploadimg extends AppCompatActivity {

   com.google.firebase.storage.StorageReference storageReference;
//    LinearProgressIndicator progress;
    Uri image;

    MaterialButton selectImage, saveImage;
    ImageView imageView;
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                if (result.getData() != null) {
                    image = result.getData().getData();
                    saveImage.setEnabled(true);
                    Glide.with(getApplicationContext()).load(image).into(imageView);
                }
            }else {
                Toast.makeText(Uploadimg.this, "Please Select an Image", Toast.LENGTH_SHORT).show();
            }
        }
    });

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadimg);

        FirebaseApp.initializeApp(Uploadimg.this);
        storageReference = FirebaseStorage.getInstance().getReference();
        
//        progress = findViewById(R.id.progress);
        imageView = findViewById(R.id.imgview);
        selectImage = findViewById(R.id.btn_selectimg);
        saveImage = findViewById(R.id.btn_simpanimg);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });

        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveImage(image);
            }
        });
    }

    private void saveImage(Uri image) {
        com.google.firebase.storage.StorageReference reference = storageReference.child("image/" + UUID.randomUUID().toString());
        reference.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // Dapatkan URL gambar yang diunggah
                reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Simpan URL gambar ke variabel imageUrl
                        String imageUrl = uri.toString();

                        // Kirim URL gambar ke halaman Pick Up
                        Intent intent = new Intent(Uploadimg.this, PickUp.class);
                        intent.putExtra("IMAGE_URL", imageUrl);
                        startActivity(intent);
                        finish(); // Hapus ini jika Anda ingin kembali ke halaman Uploadimg
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Uploadimg.this, "There was an error while uploading image", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class StorageReference {
    }
}