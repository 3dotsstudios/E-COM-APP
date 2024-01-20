package com.example.project1442.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project1442.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private ImageView backBtn;
    private Button signInBtn, signUpBtn;
    private TextView infoTextView;
    private TextView nameTextView, emailTextView;
    private Button logoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Set the layout for this activity

        // Initialize Firebase Auth and Database
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize the views
        backBtn = findViewById(R.id.backBtn);
        signInBtn = findViewById(R.id.signIn);
        signUpBtn = findViewById(R.id.signUp);
        infoTextView = findViewById(R.id.textView9);
        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.email);
        logoutBtn = findViewById(R.id.logoutbtn);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("UserName") && intent.hasExtra("UserEmail")) {
            String name = intent.getStringExtra("UserName");
            String email = intent.getStringExtra("UserEmail");

            nameTextView.setText(name);
            emailTextView.setText(email);
        } else {
            loadUserProfile(); // Load user profile from Firebase
        }

        // Determine the user's login status and set layout visibility
        if (isLoggedIn()) {
            findViewById(R.id.layoutNewUser).setVisibility(View.GONE);
            findViewById(R.id.layoutLoggedInUser).setVisibility(View.VISIBLE);
            loadUserProfile(); // Load user profile if logged in
        } else {
            findViewById(R.id.layoutNewUser).setVisibility(View.VISIBLE);
            findViewById(R.id.layoutLoggedInUser).setVisibility(View.GONE);
        }

        // Set onClickListener for the logout button
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Sign out from Firebase
                mAuth.signOut();

                // Redirect to MainActivity
                Intent mainIntent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish(); // Ensure this activity is removed from the back stack
            }
        });

        // Set onClickListener for the back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the MainActivity
                Intent mainIntent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish(); // This will finish the ProfileActivity
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
    }

    private boolean isLoggedIn() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser != null;
    }

    private void loadUserProfile() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();

            mDatabase.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String name = dataSnapshot.child("name").getValue(String.class);
                        String email = dataSnapshot.child("email").getValue(String.class);

                        nameTextView.setText(name != null ? name : "Unknown");
                        emailTextView.setText(email != null ? email : "No Email");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle errors
                }
            });
        }
    }
}
