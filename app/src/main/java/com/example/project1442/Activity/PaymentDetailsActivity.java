package com.example.project1442.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.content.Intent;

import com.example.project1442.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PaymentDetailsActivity extends AppCompatActivity {
    private Button payButton;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private String itemName;
    private Double itemPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        mAuth = FirebaseAuth.getInstance();
        initializeViews();
        fetchItemDetailsFromIntent();

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

    private void fetchItemDetailsFromIntent() {
        Intent intent = getIntent();
        itemName = intent.getStringExtra("itemName");
        itemPrice = intent.getDoubleExtra("itemPrice", 0.0); // Default price is 0.0
    }

    private void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        progressBar.setVisibility(View.GONE);
    }

    private void sendEmail() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if (currentUser != null && itemName != null) {
                        String userEmail = currentUser.getEmail();
                        if (userEmail == null || userEmail.isEmpty()) {
                            runOnUiThread(() -> Toast.makeText(PaymentDetailsActivity.this, "User email is not available", Toast.LENGTH_SHORT).show());
                            return;
                        }

                        String emailSubject = "Order Confirmation";
                        String emailBody = "Congratulations, you have successfully ordered " + itemName + " for $" + itemPrice + ".";

                        Properties props = new Properties();
                        props.put("mail.smtp.host", "smtp.titan.email"); // Titan Email SMTP host
                        props.put("mail.smtp.socketFactory.port", "465"); // SSL port for Titan Email
                        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                        props.put("mail.smtp.auth", "true");
                        props.put("mail.smtp.port", "465");

                        Session session = Session.getDefaultInstance(props,
                                new javax.mail.Authenticator() {
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication("dev@kofisefah.online", "Admin123$"); // Titan email and password
                                    }
                                });

                        Message message = new MimeMessage(session);
                        message.setFrom(new InternetAddress("dev@kofisefah.online")); // Titan email
                        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
                        message.setSubject(emailSubject);
                        message.setText(emailBody);

                        Transport.send(message);

                        runOnUiThread(() -> Toast.makeText(PaymentDetailsActivity.this, "Email sent successfully", Toast.LENGTH_SHORT).show());
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    runOnUiThread(() -> Toast.makeText(PaymentDetailsActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show());
                }
            }
        }).start();
    }
}
