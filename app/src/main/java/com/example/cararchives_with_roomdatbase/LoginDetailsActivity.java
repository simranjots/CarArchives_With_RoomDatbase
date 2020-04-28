package com.example.cararchives_with_roomdatbase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

public class LoginDetailsActivity extends AppCompatActivity {

    AppCompatEditText Email,password;
    Button login;
    public static final String EMAIL_KEY = "email_key";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_details);

        Email = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtPassword);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Email.getText().toString().equals("admin@mail.com") && password.getText().toString().equals("admin")) {

                        Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong credential", Toast.LENGTH_SHORT).show();
                    }

                }

            });
    }
}
