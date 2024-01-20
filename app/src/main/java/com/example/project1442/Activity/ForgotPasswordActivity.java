package com.example.project1442.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project1442.R;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private TextView loginTextView;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password); // Using the correct layout name

        // Initialize Firebase Auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize your view elements
        emailEditText = findViewById(R.id.email2);
        resetPasswordButton = findViewById(R.id.resetpassbtn); // Reset password button
        loginTextView = findViewById(R.id.textviewr); // TextView for navigating to login

        // Set OnClickListener for reset password button
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });

        // Set OnClickListener for login TextView
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin();
            }
        });
    }

    private void resetPassword() {
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return;
        }

        // Attempt to send a password reset email
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(ForgotPasswordActivity.this, "Check your email to reset your password", Toast.LENGTH_LONG).show();
                navigateToLogin();
            } else {
                Toast.makeText(ForgotPasswordActivity.this, "Failed to send reset email. Please check the email provided.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void navigateToLogin() {
        // Navigate to Login activity
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish(); // Close this activity
    }
}
