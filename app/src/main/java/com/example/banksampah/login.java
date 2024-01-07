package com.example.banksampah;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText Email, Password;
    private Button Btnlogin;
    private TextView Signup;

    private FirebaseAuth auth;

    //inisialisasi firebase auth
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.et_email);
        Password = findViewById(R.id.et_password);
        Btnlogin = findViewById(R.id.btn_login);
        Signup = findViewById(R.id.sign_up);


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
                //baru ditambahkan
                finish();
            }
        });

        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email, password;
                email = String.valueOf(Email.getText());
                password = String.valueOf(Password.getText());
//                String email = Email.getText().toString().trim();
//                String password = Password.getText().toString().trim();

                //if (email.isEmpty()) {
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email wajib diisi!");
                    return;
                }
                //if (passsword.isEmpty()) {
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password wajib diisi!");
                    return;
                }
                //firebase
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    String currentUserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                    String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();


                                    SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
                                    editor.putString("currentUserID", currentUserID);
                                    editor.putString("userEmail", userEmail);
                                    editor.apply();

                                    //intent ke home
                                    Intent intent = new Intent(login.this, mainscreen.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(login.this, "Email or Password is Wrong", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

}