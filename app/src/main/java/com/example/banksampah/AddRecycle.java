package com.example.banksampah;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddRecycle extends AppCompatActivity {

    private ImageView imgBefore, imgAfter;
    EditText titleBefore, titleAfter;
    private Button btnSave, btnSave2, btnPost;

//    private StorageReference storageReference;
//    private DatabaseReference databaseReference;

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUriBefore, imageUriAfter;

    //baru ikut video ungu
    final private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recycle);

        imgBefore = findViewById(R.id.add_img_before);
        imgAfter = findViewById(R.id.add_img_after);
        titleBefore = findViewById(R.id.add_title_before);
        titleAfter = findViewById(R.id.add_title_after);
        btnSave = findViewById(R.id.btn_send);
        btnSave2 = findViewById(R.id.btn_send2);
        btnPost = findViewById(R.id.add_save);

//        storageReference = FirebaseStorage.getInstance().getReference();
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("recycles");


        imgBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser(1);
            }
        });

        imgAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser(2);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage(imageUriBefore, titleBefore.getText().toString());
            }
        });

        btnSave2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage(imageUriAfter, titleAfter.getText().toString());
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTextToDatabase(titleBefore.getText().toString(), titleAfter.getText().toString());
                Intent intent = new Intent(AddRecycle.this, Recycle.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void openFileChooser(int requestCode) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == 1) {
                imageUriBefore = data.getData();
                imgBefore.setImageURI(imageUriBefore);
            } else if (requestCode == 2) {
                imageUriAfter = data.getData();
                imgAfter.setImageURI(imageUriAfter);
            }
        }
    }

    private void uploadImage(Uri imageUri, final String title) {
        if (imageUri != null) {
            StorageReference fileRef = storageReference.child("imagesre/" + System.currentTimeMillis() + ".jpg");

            fileRef.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    saveImageToDatabase(title, imageUrl);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddRecycle.this, "Gagal mengunggah gambar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(this, "Pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveImageToDatabase(String title, String imageUrl) {
        DatabaseReference recycleRef = databaseReference.push();
        recycleRef.child("title").setValue(title);
        recycleRef.child("imageUrl").setValue(imageUrl);
    }

    private void saveTextToDatabase(String titleBefore, String titleAfter) {
        DatabaseReference recycleRef = databaseReference.push();
        recycleRef.child("titleBefore").setValue(titleBefore);
        recycleRef.child("titleAfter").setValue(titleAfter);
    }
}
