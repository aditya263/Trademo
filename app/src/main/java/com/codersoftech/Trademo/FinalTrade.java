package com.codersoftech.Trademo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codersoftech.Trademo.ModelResponse.RegisterResponse;
import com.codersoftech.Trademo.ModelResponse.TradeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalTrade extends AppCompatActivity {

    Button Action;
    TextView c,Back,sql;
    String company,Trade,Stock_Symbol,Stock_Name,Demat;
    EditText qty,rate;
    int Wallet,Qty,Price,Ammount;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_trade);

        Action=(Button)findViewById(R.id.command);
        c=findViewById(R.id.trade);
        Back=findViewById(R.id.back);
        qty=(EditText)findViewById(R.id.qty);
        rate=(EditText)findViewById(R.id.rate);
        Intent intent = getIntent();
        company=intent.getStringExtra("Company");
        Trade=intent.getStringExtra("Type");
        c.setText(company);
        sql=(TextView)findViewById(R.id.query);



        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Wallet=sharedPreferences.getInt("wallet",0);
        Demat=sharedPreferences.getString("Demat","TEST1");
        Action.setText(Trade);

        Toast.makeText(FinalTrade.this,Trade,Toast.LENGTH_LONG).show();

        if(Trade=="BUY"){
            Action.setBackgroundColor(R.color.green);
            Toast.makeText(FinalTrade.this,"Buy is running ",Toast.LENGTH_LONG).show();
        }
        else{
            if(Trade=="SELL"){
                Action.setBackgroundColor(R.color.red);
                Toast.makeText(FinalTrade.this,"Sell is running ",Toast.LENGTH_LONG).show();
            }
        }

        Action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Trade=="BUY"){
                    // prepare the order
                Price=Integer.parseInt(rate.getText().toString());
                Qty=Integer.parseInt(qty.getText().toString());
                Ammount=Price*Qty;
                Stock_Name=company;
                Stock_Symbol="";


                // cheking the eligibility for Buy
                    if(Ammount<=Wallet){
                        String st="Buy"+Stock_Name+" "+Qty+ "@ "+Price;
                        sql.setText(st);
                        Execute();
                    }
                    else{
                        Toast.makeText(FinalTrade.this,"No Sufficient Fund to Buy these Shares",Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    if(Trade=="SELL"){
                        // prepare the order
                        Price=Integer.parseInt(rate.getText().toString());
                        Qty=Integer.parseInt(qty.getText().toString());
                        String Stock_Name=company;
                        String Stock_Symbol="";
                        Toast.makeText(FinalTrade.this,"Sell  Function ",Toast.LENGTH_LONG).show();

                    /* // cheking the eligibility for Sell
                    if(sell_qty<=have_shares(Demat,Stock_Symbol,sell_qty)){
                        sql.setText("Sell"+Stock_Name+" "+sell_qty+ "@ "+sell_price);
                    }
                    else{
                        Toast.makeText(FinalTrade.this,"You Don't Have this much Shares",Toast.LENGTH_LONG).show();
                    }*/
                    }
                }
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public void Execute(){
        Call<TradeResponse> call = RetrofitClient.getInstance().getApi().trade(Trade,Stock_Name,Stock_Symbol,Qty,Demat,Price);
        call.enqueue(new Callback<TradeResponse>() {
            @Override
            public void onResponse(Call<TradeResponse> call, Response<TradeResponse> response) {
                TradeResponse registerResponse = response.body();
                if(response.isSuccessful()){

                }
                else{

                }

            }

            @Override
            public void onFailure(Call<TradeResponse> call, Throwable t) {

            }
        });
    }
}