package com.example.a2rough;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    EditText  email, password;
    Button imagebtn;
    FirebaseAuth mAuth;
    TextView textview;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth= FirebaseAuth.getInstance();
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        imagebtn=findViewById(R.id.imagebtn);
        textview =findViewById(R.id.textviewl);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CreateAccount.class);
                startActivity(intent);
                finish();
            }
        });

        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email1, password1;
                ;
                email1 = String.valueOf(email.getText());
                password1 = String.valueOf(password.getText());

                if (TextUtils.isEmpty(email1)) {
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Login successfull", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

            }
        });

    }
}