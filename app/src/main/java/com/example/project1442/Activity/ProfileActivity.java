package com.example.project1442.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        setContentView(R.layout.activity_profile);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        backBtn = findViewById(R.id.backBtn);
        signInBtn = findViewById(R.id.signIn);
        signUpBtn = findViewById(R.id.signUp);
        infoTextView = findViewById(R.id.textView9);
        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.email);
        logoutBtn = findViewById(R.id.logoutbtn);

        logoutBtn.setOnClickListener(v -> {
            mAuth.signOut();
            Intent mainIntent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        });

        backBtn.setOnClickListener(v -> {
            Intent mainIntent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        });

        signInBtn.setOnClickListener(v -> {
            Intent signInIntent = new Intent(ProfileActivity.this, Login.class);
            startActivity(signInIntent);
        });

        signUpBtn.setOnClickListener(v -> {
            Intent signUpIntent = new Intent(ProfileActivity.this, CreateAccount.class);
            startActivity(signUpIntent);
        });

        updateUIBasedOnLoginStatus();
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUIBasedOnLoginStatus();
    }

    private void updateUIBasedOnLoginStatus() {
        if (isLoggedIn()) {
            findViewById(R.id.layoutNewUser).setVisibility(View.GONE);
            findViewById(R.id.layoutLoggedInUser).setVisibility(View.VISIBLE);
            loadUserProfile();
        } else {
            findViewById(R.id.layoutNewUser).setVisibility(View.VISIBLE);
            findViewById(R.id.layoutLoggedInUser).setVisibility(View.GONE);
        }
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
                    Toast.makeText(ProfileActivity.this, "Error loading profile.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
