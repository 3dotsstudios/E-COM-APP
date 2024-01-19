package com.example.project1442.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;

import com.example.project1442.Adapter.CartListAdapter;
import com.example.project1442.Helper.ChangeNumberItemsListener;
import com.example.project1442.Helper.ManagmentCart;
import com.example.project1442.R;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagmentCart managmentCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private EditText editTextDeliveryAddress;
    private double tax;
    private ScrollView scrollView;
    private ImageView backBtn, imageViewEditAddress;
    private Spinner paymentMethodSpinner;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managmentCart = new ManagmentCart(this);

        initView();
        setupPaymentMethodSpinner();
        setVariable();
        initList();
        calculateCart();
        setupEditAddress();

        // Initialize GestureDetector for swipe gesture
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if (e2.getX() > e1.getX()) {
                    // Detects a left-to-right swipe
                    finish(); // Finish activity to go back
                    return true;
                }
                return false;
            }
        });
    }

    private void setupPaymentMethodSpinner() {
        // Initialize the spinner
        paymentMethodSpinner = findViewById(R.id.paymentMethodSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.payment_methods_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        paymentMethodSpinner.setAdapter(spinnerAdapter);

        // Set up a listener for the spinner
        paymentMethodSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get selected item
                String selectedItem = parent.getItemAtPosition(position).toString();
                // Handle the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managmentCart.getListCart(), this, new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });

        recyclerView.setAdapter(adapter);
        if (managmentCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double percentTax = 0.02;  //you can change this value for tax price
        double delivery = 10;
        tax = Math.round((managmentCart.getTotalFee() * percentTax * 100.0)) / 100.0;

        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText("$" + itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);
    }

    private void setVariable() {
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> finish()); // Listener for the back button
    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        recyclerView = findViewById(R.id.view3);
        scrollView = findViewById(R.id.scrollView2);
        emptyTxt = findViewById(R.id.emptyTxt);
        paymentMethodSpinner = findViewById(R.id.paymentMethodSpinner);
        editTextDeliveryAddress = findViewById(R.id.editTextDeliveryAddress);
        imageViewEditAddress = findViewById(R.id.imageViewEditAddress);
    }

    private void setupEditAddress() {
        imageViewEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDeliveryAddress();
            }
        });
    }

    private void saveDeliveryAddress() {
        String address = editTextDeliveryAddress.getText().toString();
        // Save the address to your database or preferences
        // Show confirmation message if needed
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass all touch events to the GestureDetector
        return gestureDetector.onTouchEvent(event) || super.onTouchEvent(event);
    }
}
