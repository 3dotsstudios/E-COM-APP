package com.example.project1442.Activity;

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

public class SandalsActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterPupolar;
    private RecyclerView recyclerViewPupolar;

    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sandal_page);
        initRecyclerview();

        bottom_navigation();


    }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout wishlistBtn = findViewById(R.id.wishlistBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(SandalsActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(SandalsActivity.this, CartActivity.class)));
        wishlistBtn.setOnClickListener(v -> startActivity(new Intent(SandalsActivity.this, WishlistActivity.class)));
        profileBtn.setOnClickListener(v -> startActivity(new Intent(SandalsActivity.this, ProfileActivity.class)));
    }


    private void initRecyclerview() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("Peninsula Leather Sandals", "These Peninsula sandals from Private Collection are an open-toe style, inspired by the traditional Arabic sandal. Made from leather, they're made complete with a smooth leather lining for a comfortable stride while the durable platform sole offers a subtle lift.\n" +
                "\n" +
                "Product detail:\n" +
                "SKU: LMBML2177N-WHT\n" +
                "Quantity: IS IN STOCK\n" +
                "Made in: Italy\n" +
                "Colour: Brown\n" +
                "Designer: Private Collection\n" +
                "Material: Leather", "pic1", 15, 4, 500));
        items.add(new PopularDomain("FERRAGAMO\n" +
                "Murray Sandals", "Turn heads with these Murray sandals from Salvatore Ferragamo, which come with an open-toe silhouette and embossed leather craftsmanship. Style them with a white kandora for a traditional look.\n" +
                "\n" +
                "prdct detail:\n" +
                "SKU: 020769-754404\n" +
                "Quantity: IS IN STOCK\n" +
                "Made in: Italy\n" +
                "Colour: White\n" +
                "Designer: Ferragamo\n" +
                "Material: Leather",
                "pic4", 10, 4.5, 450));


        recyclerViewPupolar = findViewById(R.id.view1);
        recyclerViewPupolar.setLayoutManager(new GridLayoutManager(this, 2));

        adapterPupolar = new PopularListAdapter(items);
        recyclerViewPupolar.setAdapter(adapterPupolar);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> startActivity(new Intent(SandalsActivity.this, MainActivity.class)));
    }
}
