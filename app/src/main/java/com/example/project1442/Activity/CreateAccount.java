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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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
        signupButton = findViewById(R.id.signupbtn);
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
                String nameText = name.getText().toString().trim();
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();
                String confirmPasswordText = confirmPassword.getText().toString().trim();

                // Check for empty fields and password match
                if (TextUtils.isEmpty(nameText) || TextUtils.isEmpty(emailText) ||
                        TextUtils.isEmpty(passwordText) || !passwordText.equals(confirmPasswordText)) {
                    Toast.makeText(CreateAccount.this, "Please check your inputs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Firebase user registration
                mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    if (firebaseUser != null) {
                                        String userId = firebaseUser.getUid();

                                        Map<String, Object> userData = new HashMap<>();
                                        userData.put("name", nameText);
                                        userData.put("email", emailText);

                                        FirebaseDatabase.getInstance().getReference("users")
                                                .child(userId)
                                                .setValue(userData)
                                                .addOnCompleteListener(task1 -> {
                                                    if (task1.isSuccessful()) {
                                                        Toast.makeText(CreateAccount.this, "Account created", Toast.LENGTH_SHORT).show();
                                                        // Redirect to ProfileActivity with user data
                                                        Intent intent = new Intent(CreateAccount.this, ProfileActivity.class);
                                                        intent.putExtra("UserName", nameText);
                                                        intent.putExtra("UserEmail", emailText);
                                                        startActivity(intent);
                                                        finish();
                                                    } else {
                                                        Toast.makeText(CreateAccount.this, "Failed to store user data", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(CreateAccount.this, "Failed to get user data", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(CreateAccount.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
