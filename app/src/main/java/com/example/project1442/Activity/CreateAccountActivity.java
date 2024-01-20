package com.example.a2rough;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {

    EditText  name,email, password;
    ImageButton imagebtn;
    FirebaseAuth mAuth;
    TextView textview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth= FirebaseAuth.getInstance();
        name=findViewById(R.id.name2);
        email=findViewById(R.id.email2);
        password=findViewById(R.id.password2);
        imagebtn=findViewById(R.id.imagebtn2);
        textview =findViewById(R.id.textviewr);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        imagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1,email1,password1;
                name1= String.valueOf(name.getText());
                email1= String.valueOf(email.getText());
                password1=String.valueOf(password.getText());


                if (TextUtils.isEmpty(name1)){
                    Toast.makeText(CreateAccount.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(email1)){
                    Toast.makeText(CreateAccount.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password1)){
                    Toast.makeText(CreateAccount.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(CreateAccount.this, "Account created", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();



                                } else {
                                    Toast.makeText(CreateAccount.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });



            }
        });

    }
}