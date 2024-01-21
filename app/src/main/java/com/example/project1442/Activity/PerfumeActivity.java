package com.example.project1442.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project1442.Adapter.PopularListAdapter;
import com.example.project1442.Domain.PopularDomain;
import com.example.project1442.R;

import java.util.ArrayList;

public class PerfumeActivity extends AppCompatActivity {

        private RecyclerView.Adapter adapterPupolar;
        private RecyclerView recyclerViewPupolar;

        private ImageView backBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.perfume_page);
            initRecyclerview();
            bottom_navigation();


        }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout wishlistBtn = findViewById(R.id.wishlistBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(PerfumeActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(PerfumeActivity.this, CartActivity.class)));
        wishlistBtn.setOnClickListener(v -> startActivity(new Intent(PerfumeActivity.this, WishlistActivity.class)));
        profileBtn.setOnClickListener(v -> startActivity(new Intent(PerfumeActivity.this, ProfileActivity.class)));
    }
        private void initRecyclerview() {
            ArrayList<PopularDomain> items = new ArrayList<>();
            items.add(new PopularDomain(" Maison Francis Kurkdjian\n" +
                    "Baccarat Rouge 540 Extrait 70ml", "This Baccarat Rouge 540 Extrait de Parfum by Maison Francis Kurkdjian is formulated with a blend of Egyptian jasmine grandiflorum, saffron, Moroccan bitter almond notes, and is enhanced with cedar, musky wood, ambergris accords.\n" +
                    "\n" +
                    "Product detail:\n" +
                    "SKU: CRPFL2177N-WHT\n" +
                    "Quantity: IS IN STOCK\n" +
                    "Made in: Egypt\n" +
                    "Fragrance: Grandiflorum\n" +
                    "Producer: Francis Kurkdjian\n" +
                    "Ingredients: Egyptian jasmine grandiflorum, saffron, Moroccan bitter almond notes, cedar, musky wood, ambergris accords.",
                    "pic6", 10, 4.5, 1650));

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


            recyclerViewPupolar = findViewById(R.id.view1);
            recyclerViewPupolar.setLayoutManager(new GridLayoutManager(this, 2));

            adapterPupolar = new PopularListAdapter(items);
            recyclerViewPupolar.setAdapter(adapterPupolar);
            backBtn = findViewById(R.id.backBtn);
            backBtn.setOnClickListener(v -> startActivity(new Intent(com.example.project1442.Activity.PerfumeActivity.this, MainActivity.class)));
        }
    }

