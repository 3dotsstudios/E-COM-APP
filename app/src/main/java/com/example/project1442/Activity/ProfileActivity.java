package com.example.project1442.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project1442.R;

public class ProfileActivity extends AppCompatActivity {

    private ImageView backBtn;
    private Button signInBtn, signUpBtn;
    private TextView infoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Set the layout for this activity

        // Initialize the views
        backBtn = findViewById(R.id.backBtn);
        signInBtn = findViewById(R.id.signIn);
        signUpBtn = findViewById(R.id.signUp);
        infoTextView = findViewById(R.id.textView9);

        // Set onClickListener for the back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
                onBackPressed();
            }
        });

        // Set onClickListener for the sign in button
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Login activity
                Intent signInIntent = new Intent(ProfileActivity.this, Login.class);
                startActivity(signInIntent);
            }
        });

        // Set onClickListener for the sign up button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the CreateAccount activity
                Intent signUpIntent = new Intent(ProfileActivity.this, CreateAccount.class);
                startActivity(signUpIntent);
            }
        });

        // Additional functionality can be added as needed
    }
}
