package com.example.banksampah;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.banksampah.databinding.ActivityPaymentBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    private ActivityPaymentBinding binding;
    private String phone = "";
    private String address = "";
    private String date = "";
    private String time = "";
    private ArrayList<String> dataType;
    private int totalKg = 0;
    private int totalPrice = 0;
    private int totalIncome = 0;
    private int currentMoney = 0;
    private DatabaseReference incomeRef;
    private DatabaseReference paymentRef;
    private String lisItems = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dataType = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        paymentRef = FirebaseDatabase.getInstance().getReference("payment_history");
        incomeRef = database.getReference("user_income");
        retrieveIntValue();
        getData();
        initListener();
        initData();

        lisItems = concatenateArrayList(dataType);
    }

    public static String concatenateArrayList(ArrayList<String> arrayList) {
        return String.join(", ", arrayList);
    }

    private void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            ArrayList<String> yourArrayList = intent.getStringArrayListExtra("organicType");
            totalKg = intent.getIntExtra("organicKg", 0);
            phone = intent.getStringExtra("telepon");
            address = intent.getStringExtra("address");
            date = intent.getStringExtra("date");
            time = intent.getStringExtra("time");

            if (yourArrayList != null) {
                dataType = yourArrayList;
            }
        }

        totalPrice = 2500 * totalKg;
        totalIncome = 3000 * totalKg;
    }

    private void initListener() {
        binding.btnSend.setOnClickListener(v -> {
            uploadData();
        });
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        binding.txtName.setText(phone);
        binding.tvAmount.setText(address);
        binding.txtAmount.setText(date);
        binding.tvTime.setText(time);

        binding.txtDetailSales.setText("Organic" + " | " + totalKg + " Kg");
        binding.tvPriceDetail.setText("Rp. " + totalPrice);
        binding.tvEstimatedIncome.setText("Rp. " + totalIncome);
    }

    private void retrieveIntValue() {
        // Retrieve the int value from Firebase
        incomeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get the int value
                    String uangData = dataSnapshot.getValue(String.class);
                    if (uangData != null) {
                        currentMoney = Integer.parseInt(uangData);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors if needed
                Toast.makeText(PaymentActivity.this, "Error get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadData() {
        binding.pbLoading.setVisibility(View.VISIBLE);

        int totalMoney = currentMoney + totalPrice;
        String value = "" + totalMoney + "";

        incomeRef.setValue(value)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        uploadPayment();
                        Toast.makeText(PaymentActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        // There was an error saving the data
                        Exception exception = task.getException();
                        if (exception != null) {
                            binding.pbLoading.setVisibility(View.GONE);
                            Log.e("Firebase", "Error adding data: " + exception.getMessage());
                            Toast.makeText(PaymentActivity.this, "Error adding data: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void uploadPayment() {
        Long currentTimeMillis = System.currentTimeMillis();

        PaymentItem newItem = new PaymentItem();
        newItem.setBerat(totalKg);
        newItem.setItems(lisItems);
        newItem.setPrice((long) totalPrice);
        newItem.setDate(currentTimeMillis);
        newItem.setStatus("Picked Up");

        paymentRef.push().setValue(newItem)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        Intent intent = new Intent(this, mainscreen.class);
                        intent.putExtra("organicKg", totalPrice);
                        startActivity(intent);
                        finish();

                        Log.d("Firebase", "Data added successfully");
                        Toast.makeText(PaymentActivity.this, "Data added successfully", Toast.LENGTH_SHORT).show();
                        binding.pbLoading.setVisibility(View.GONE);
                    } else {
                        // There was an error saving the data
                        Exception exception = task.getException();
                        if (exception != null) {
                            Log.e("Firebase", "Error adding data: " + exception.getMessage());
                            Toast.makeText(PaymentActivity.this, "Error adding data: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        binding.pbLoading.setVisibility(View.GONE);
                    }
                });
    }
}