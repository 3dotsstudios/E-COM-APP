package com.example.project1442.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

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
        items.add(new PopularDomain("Creed Perfume", "Discover the luxury of Creed Perfume, a blend of top notes, florals, and spices that embody sophistication and elegance. Ideal for those who value style and grace, it's not just a fragrance but a statement of refinement for any occasion.\n"+
                "\n" +
                "Product detail:\n" +
                "SKU: CRPFL2177N-WHT\n" +
                "Quantity: IS IN STOCK\n" +
                "Made in: France\n" +
                "Fragrance: Floral Spice B\n" +
                "Producer: Creed\n" +
                "Ingredients: Top notes of exotic spices, a heart of floral essences, and a base of rose oil",
                "pic1", 10, 4.5, 450));


        recyclerViewPupolar = findViewById(R.id.view1);
        recyclerViewPupolar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterPupolar = new PopularListAdapter(items);
        recyclerViewPupolar.setAdapter(adapterPupolar);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> startActivity(new Intent(SandalsActivity.this, MainActivity.class)));
    }
}
