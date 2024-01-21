package com.example.project1442.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;


import com.example.project1442.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PaymentDetailsActivity extends AppCompatActivity {
    private Button payButton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        initializeViews();
        fetchItemNameFromFirebase();

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoader();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        hideLoader();
                        sendEmail();
                    }
                }, 5000); // Delay for 5 seconds
            }
        });
    }

    private void initializeViews() {
        payButton = findViewById(R.id.pay);
        progressBar = findViewById(R.id.progressbar);
    }

    private void fetchItemNameFromFirebase() {
        DatabaseReference itemRef = databaseReference.child("items").child("itemName"); // Adjust this path based on your database structure
        itemRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemName = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(PaymentDetailsActivity.this, "Error fetching item data", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    private void sendEmail() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null && itemName != null) {
            String emailMessage = "Congratulations, you have successfully ordered " + itemName + ".";
            Toast.makeText(PaymentDetailsActivity.this, emailMessage, Toast.LENGTH_LONG).show();

            // Navigate back to MainActivity after showing the Toast
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(PaymentDetailsActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Close this activity
                }
            }, Toast.LENGTH_LONG); // Delay by the duration of the Toast
        } else {
            Toast.makeText(PaymentDetailsActivity.this, "Error: User not logged in or item name unavailable", Toast.LENGTH_SHORT).show();
        }
    }
}
