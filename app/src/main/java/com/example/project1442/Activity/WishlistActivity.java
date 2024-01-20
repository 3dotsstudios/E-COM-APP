package com.example.project1442.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;

import com.example.project1442.Adapter.CartListAdapter;
import com.example.project1442.Helper.ChangeNumberItemsListener;
import com.example.project1442.Helper.ManagmentCart;
import com.example.project1442.R;
public class WishlistActivity extends AppCompatActivity {


    private RecyclerView wishlistRecyclerView;
    private TextView emptyTextView;
    private ImageView backBtn;
    private GestureDetector gestureDetector;


    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout wishlistBtn = findViewById(R.id.wishlistBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(WishlistActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(WishlistActivity.this, CartActivity.class)));
        wishlistBtn.setOnClickListener(v -> startActivity(new Intent(WishlistActivity.this, WishlistActivity.class)));
        profileBtn.setOnClickListener(v -> startActivity(new Intent(WishlistActivity.this, ProfileActivity.class)));
    }



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_wishlist);


        bottom_navigation();

            // Initialize components
            wishlistRecyclerView = findViewById(R.id.view3);
            emptyTextView = findViewById(R.id.emptyTxt);
            backBtn = findViewById(R.id.backBtn);

            // Set up the RecyclerView (Adapter, LayoutManager)

            // Set the on click listener for the back button
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the MainActivity
                Intent mainIntent = new Intent(WishlistActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish(); // This will finish the ProfileActivity, preventing it from staying in the back stack
            }
        });


        // Additional logic (e.g., loading wishlist items)
        }

    // Additional methods as needed
}