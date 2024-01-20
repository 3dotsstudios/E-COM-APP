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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.example.project1442.R;

public class CreateAccount extends AppCompatActivity {

    private EditText name, email, password, confirmPassword;
    private Button signupButton;
    private FirebaseAuth mAuth;
    private TextView textViewRedirectLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        // Initialize UI components
        name = findViewById(R.id.name2);
        email = findViewById(R.id.email2);
        password = findViewById(R.id.password2);
        confirmPassword = findViewById(R.id.password3); // Confirm password field
        signupButton = findViewById(R.id.signupbtn); // Corrected to Button
        textViewRedirectLogin = findViewById(R.id.textviewr);

        // Redirect to Login screen
        textViewRedirectLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        // Set onClickListener for the signup button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                String confirmPasswordText = confirmPassword.getText().toString();

                // Check for empty fields
                if (TextUtils.isEmpty(nameText)) {
                    Toast.makeText(CreateAccount.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(emailText)) {
                    Toast.makeText(CreateAccount.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passwordText)) {
                    Toast.makeText(CreateAccount.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if passwords match
                if (!passwordText.equals(confirmPasswordText)) {
                    Toast.makeText(CreateAccount.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Firebase user registration
                mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(CreateAccount.this, "Account created", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(CreateAccount.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
