package com.example.cararchives_with_roomdatbase;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SActivity extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s);


        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}
