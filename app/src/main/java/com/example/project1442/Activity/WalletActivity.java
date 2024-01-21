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


public class WalletActivity extends AppCompatActivity {


    private RecyclerView.Adapter adapterPupolar;
    private RecyclerView recyclerViewPupolar;

    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_page);
        initRecyclerview();
        bottom_navigation();


    }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout wishlistBtn = findViewById(R.id.wishlistBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(WalletActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(WalletActivity.this, CartActivity.class)));
        wishlistBtn.setOnClickListener(v -> startActivity(new Intent(WalletActivity.this, WishlistActivity.class)));
        profileBtn.setOnClickListener(v -> startActivity(new Intent(WalletActivity.this, ProfileActivity.class)));
    }

    private void initRecyclerview() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("GUCCI\n" +
                "Animalier Leather Coin Wallet", "Part of Gucci's Animalier collection, the leather coin wallet features their iconic metal bee detail. Made from tanned leather, it's completed with four credit card slots, one bill compartment and a coin pocket. Wallet measures approximately 11cm x 9cm.\n" +
                "\n" +
                "Prdct detail:\n" +
                "SKU: 522915DJ20T1000\n" +
                "Quantity: IS IN STOCK\n" +
                "Made in: Italy\n" +
                "Colour: Black\n" +
                "Designer: Gucci\n" +
                "Material: Leather", "pic5", 15, 4, 500));

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
        recyclerViewPupolar.setLayoutManager(new GridLayoutManager(this, 2));

        adapterPupolar = new PopularListAdapter(items);
        recyclerViewPupolar.setAdapter(adapterPupolar);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> startActivity(new Intent(com.example.project1442.Activity.WalletActivity.this, MainActivity.class)));
    }
}


