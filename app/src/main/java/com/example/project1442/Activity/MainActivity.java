package com.example.project1442.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project1442.Adapter.PopularListAdapter;
import com.example.project1442.Domain.PopularDomain;
import com.example.project1442.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPupolar;
    private RecyclerView recyclerViewPupolar;
    private TextView userTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userTextView = findViewById(R.id.textView2);
        retrieveUserData();
        initRecyclerview();
        bottom_navigation();
    }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout wishlistBtn = findViewById(R.id.wishlistBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);
        ImageView sandalImage = findViewById(R.id.sandalImage);
        ImageView perfumeImage = findViewById(R.id.perfumeImage);
        ImageView walletImage = findViewById(R.id.walletImage);
        ImageView allCatImage = findViewById(R.id.allCatImage);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
        wishlistBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WishlistActivity.class)));
        profileBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
        sandalImage.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SandalsActivity.class)));
        perfumeImage.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PerfumeActivity.class)));
        walletImage.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WalletActivity.class)));
        allCatImage.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ViewAllActivity.class)));
    }

    private void retrieveUserData() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(userId);

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        String userName = dataSnapshot.child("name").getValue(String.class);
                        userTextView.setText(userName);
                    } else {
                        userTextView.setText("GUEST");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("MainActivity", "Failed to read user data.", databaseError.toException());
                    Toast.makeText(MainActivity.this, "Failed to load user data.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            userTextView.setText("GUEST");
        }
    }

    private void initRecyclerview() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        // Add your items here
        // Example:
        // items.add(new PopularDomain("Item Name", "Item Description", "image_url", 0, 0, 0));

        items.add(new PopularDomain("Peninsula Leather Sandals", "These Peninsula sandals from Private Collection are an open-toe style, inspired by the traditional Arabic sandal. Made from leather, they're made complete with a smooth leather lining for a comfortable stride while the durable platform sole offers a subtle lift.\n" +
                "\n" +
                "Product detail:\n" +
                "SKU: LMBML2177N-WHT\n" +
                "Quantity: IS IN STOCK\n" +
                "Made in: Italy\n" +
                "Colour: Brown\n" +
                "Designer: Private Collection\n" +
                "Material: Leather", "pic1", 15, 4, 500));
        items.add(new PopularDomain("Creed Perfume", "Discover the luxury of Creed Perfume, a blend of top notes, florals, and spices that embody sophistication and elegance. Ideal for those who value style and grace, it's not just a fragrance but a statement of refinement for any occasion.\n"+
                "\n" +
                "Product detail:\n" +
                "SKU: CRPFL2177N-WHT\n" +
                "Quantity: IS IN STOCK\n" +
                "Made in: France\n" +
                "Fragrance: Floral Spice B\n" +
                "Producer: Creed\n" +
                "Ingredients: Top notes of exotic spices, a heart of floral essences, and a base of rose oil",
                "pic2", 10, 4.5, 450));
        items.add(new PopularDomain("DOLCE&GABBANA\n" +
                "Jacquard Wallet", "This Logo printed wallet from Dolce&Gabbana is\n" +
                "designed with woven fabric,silver logo detail,\n" +
                " six card slots, and a central bill fold. It's ideal for everyday carrying.\n" +
                " \n" +
                "Product detail:\n" +
                "SKU: BP1321AJ70589875\n" +
                "Quantity: IS IN STOCK\n" +
                "Made in: Italy\n" +
                "Colour: Brown\n" +
                "Designer: Dolce&Gabbana\n" +
                "Dimension: 11cm x 9.3cm x 1.5cm\n" +
                "Material: Fabric\n"

                , "pic3", 13, 4.2, 1500));

        recyclerViewPupolar = findViewById(R.id.view1);
        recyclerViewPupolar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapterPupolar = new PopularListAdapter(items);
        recyclerViewPupolar.setAdapter(adapterPupolar);
    }
}
