package com.example.banksampah;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class AddNews extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText add_title, add_teks;
    private ImageView add_img;
    private Button add_save;
    private News newsToEdit;
    private String userEmail;

    private static final String CHANNEL_ID = "channel_id01";
    private static final int NOTIFICATION_ID = 1;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        Toolbar toolbar = findViewById(R.id.appbar_widget_createnews);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        mDatabase = FirebaseDatabase.getInstance().getReference().child("News");

        add_title = findViewById(R.id.add_title);
        add_teks = findViewById(R.id.add_teks);
        add_save = findViewById(R.id.add_save);
        add_img = findViewById(R.id.add_img);

        Intent intent = getIntent();
        newsToEdit = intent.getParcelableExtra("EDIT_NEWS");
        if (newsToEdit != null) {
            add_title.setText(newsToEdit.getTitle());
            add_teks.setText(newsToEdit.getDetail());

            Glide.with(this)
                    .load(newsToEdit.getPhotoUrl())
                    .into(add_img);
        }


        add_img.setOnClickListener(V -> selectImage());

        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String currentUserID = prefs.getString("currentUserID", null);
        userEmail = prefs.getString("userEmail", null);

        add_save.setOnClickListener(v -> {
            if (add_title.getText().length() > 0 && add_teks.getText().length() > 0) {
                String title = add_title.getText().toString();
                String detail = add_teks.getText().toString();
                if (currentUserID != null) {
                    if (newsToEdit != null) {
                        updateNews(newsToEdit.getId(), title, detail, currentUserID);
                    } else {
                        upload(title, detail, currentUserID);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "User data not available", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Please fill in the fields", Toast.LENGTH_SHORT).show();
            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving News...");
        progressDialog.setCancelable(false);
    }

    private void updateNews(String newsId, String title, String detail, String currentUserID) {
        DatabaseReference newsRef = mDatabase.child(newsId);
        News news = new News(newsId, title, detail, newsToEdit.getPhotoUrl(), currentUserID);
        newsRef.setValue(news)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(getApplicationContext(), "News updated successfully", Toast.LENGTH_SHORT).show();
                    onBackPressed();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getApplicationContext(), "Failed to update news", Toast.LENGTH_SHORT).show();
                });
    }


    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(AddNews.this);
        builder.setTitle(getString(R.string.app_name));
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, (dialog, item) -> {
            if (items[item].equals("Take Photo")) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 10);
            } else if (items[item].equals("Choose from Gallery")) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 20);
            } else if (items[item].equals("Cancel")) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == RESULT_OK && data != null) {
            final Uri path = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(path);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                add_img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == 10 && resultCode == RESULT_OK && data != null) {
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            add_img.setImageBitmap(bitmap);
        }
    }

    private void upload(String title, String detail, String currentUserID) {
        add_img.setDrawingCacheEnabled(true);
        add_img.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) add_img.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference("image_news").child("IMG-News" + new Date().getTime() + ".jpeg");

        progressDialog.show();
        UploadTask uploadTask = reference.putBytes(data);
        uploadTask.addOnSuccessListener(taskSnapshot -> {
            progressDialog.dismiss();
            if (taskSnapshot.getMetadata() != null && taskSnapshot.getMetadata().getReference() != null) {
                taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        String photoUrl = task.getResult().toString();
                        saveAdd(title, detail, photoUrl, currentUserID, userEmail);
                        showNotification();
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void saveAdd(String title, String detail, String photoUrl, String currentUserID, String userEmail) {
        DatabaseReference newsRef = mDatabase.push();
        String newsId = newsRef.getKey();
        News news = new News(newsId, title, detail, photoUrl, currentUserID);
        news.setUserEmail(userEmail); // Simpan userEmail di sini
        newsRef.setValue(news)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(getApplicationContext(), "News added successfully", Toast.LENGTH_SHORT).show();
                    showNotification();
                    onBackPressed();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(getApplicationContext(), "Failed to add news", Toast.LENGTH_SHORT).show();
                    Log.e("AddNews", "Failed to add news", e);
                });
    }

    private void showNotification() {
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);

        SpannableStringBuilder title = new SpannableStringBuilder("Congratulations to Add News");
        title.setSpan(new StyleSpan(Typeface.BOLD), 0, title.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        builder.setSmallIcon(R.drawable.baseline_grass_24);
        builder.setContentTitle(title);

        String notificationText = "Thank you for sharing your experiences.";
        builder.setContentText(notificationText);

        String bigText = "Continue to share your exciting experiences in \nmanaging waste to motivate yourself and others to protect the environment. \nBecause protecting the earth starts with definite steps.";
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d_notif);
        builder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap)
                .bigLargeIcon(null)
                .setSummaryText(bigText));

        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification";
            String description = "Desc";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            notificationChannel.setDescription(description);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}
