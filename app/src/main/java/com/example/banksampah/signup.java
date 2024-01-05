package com.example.banksampah;

import android.content.Intent;
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

public class signup extends AppCompatActivity {

    private Button BtnSignup;
    private TextView Login;
    private EditText  Email, Password;

    private FirebaseAuth auth;

    //firebase



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        BtnSignup = findViewById(R.id.btn_signup);
        Login = findViewById(R.id.login_su);
//        Username = findViewById(R.id.username);
        Email = findViewById(R.id.su_email);
//        Phonenum = findViewById(R.id.phone_number);
        Password = findViewById(R.id.su_pass);

        auth = FirebaseAuth.getInstance();
//        Confpass = findViewById(R.id.conf_pass);

        BtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(Email.getText());
                password = String.valueOf(Password.getText());

//                String username = Username.getText().toString().trim();
//                String email = Email.getText().toString().trim();
//                String phonenum = Phonenum.getText().toString().trim();
//                String password = Password.getText().toString().trim();
//                String confpasss = Confpass.getText().toString().trim();

//                if (username.isEmpty()) {
//                    Username.setError("Username wajib diisi!");
//                    return;
//                }
                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email wajib diisi!");
                    return;
                }
//                if (phonenum.isEmpty()) {
//                    Phonenum.setError("Phone Number wajib diisi!");
//                    return;
//                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password wajib diisi!");
                    return;
                }
//                if (confpasss.isEmpty()) {
//                    Confpass.setError("Confirmation Password wajib diisi!");
//                    return;
//                }

                //firebase create
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    //intent
                                    Intent intent = new Intent(signup.this, login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(signup.this, "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, login.class);
                startActivity(intent);
            }
        });
    }
}