package com.codersoftech.Trademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 3000;      // Delay of 3 Seconds
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.imageView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Boolean chek = sharedPreferences.getBoolean("login",false);

                if (chek){
                    Intent i = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(i);
                }
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
}