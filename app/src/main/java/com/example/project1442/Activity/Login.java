package com.example.project1442.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1442.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private EditText email, password;
    private Button loginButton;
    private TextView textViewRegister, textViewForgotPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        // Initialize view elements
        email = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        loginButton = findViewById(R.id.logIn);
        textViewRegister = findViewById(R.id.textviewl);
        textViewForgotPassword = findViewById(R.id.forgotpassword);

        // Set OnClickListener for the register TextView
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, CreateAccount.class);
                startActivity(intent);
                finish();
            }
        });

        // Set OnClickListener for the forgot password TextView
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ForgotPasswordActivity
                Intent intent = new Intent(Login.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        // Set OnClickListener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();

        if (TextUtils.isEmpty(emailText)) {
            email.setError("Enter Email");
            return;
        }

        if (TextUtils.isEmpty(passwordText)) {
            password.setError("Enter Password");
            return;
        }

        mAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
