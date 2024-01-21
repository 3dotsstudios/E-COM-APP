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
public class ViewAllActivity extends AppCompatActivity{


    private RecyclerView.Adapter adapterPupolar;
    private RecyclerView recyclerViewPupolar;

    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        initRecyclerview();
        bottom_navigation();


    }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);
        LinearLayout wishlistBtn = findViewById(R.id.wishlistBtn);
        LinearLayout profileBtn = findViewById(R.id.profileBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(ViewAllActivity.this, MainActivity.class)));
        cartBtn.setOnClickListener(v -> startActivity(new Intent(ViewAllActivity.this, CartActivity.class)));
        wishlistBtn.setOnClickListener(v -> startActivity(new Intent(ViewAllActivity.this, WishlistActivity.class)));
        profileBtn.setOnClickListener(v -> startActivity(new Intent(ViewAllActivity.this, ProfileActivity.class)));
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



        recyclerViewPupolar = findViewById(R.id.view1);
        recyclerViewPupolar.setLayoutManager(new GridLayoutManager(this, 2));

        adapterPupolar = new PopularListAdapter(items);
        recyclerViewPupolar.setAdapter(adapterPupolar);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> startActivity(new Intent(ViewAllActivity.this, MainActivity.class)));
    }
}
