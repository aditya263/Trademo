package com.codersoftech.Trademo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MarketActivity extends AppCompatActivity {

    Button b,s;
    TextView c,Back;
    String Company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
        /*getSupportActionBar().hide();*/

        Intent intent = getIntent();
        Company=intent.getStringExtra("stock");

        c=(TextView)findViewById(R.id.title);
        c.setText(Company);
        b=(Button)findViewById(R.id.buy);
        s=(Button)findViewById(R.id.sell);
        Back=(TextView)findViewById(R.id.back);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketActivity.this,FinalTrade.class);
                intent.putExtra("Type","BUY");
                intent.putExtra("Company",Company);
                startActivity(intent);
            }
        });

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketActivity.this,FinalTrade.class);
                intent.putExtra("Type","SELL");
                intent.putExtra("Company",Company);
                startActivity(intent);
            }
        });
    }
}