package com.codersoftech.Trademo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import com.codersoftech.Trademo.ModelResponse.RegisterResponse;
import com.codersoftech.Trademo.ModelResponse.TradeResponse;

import java.util.Date;

public interface Api {
        @POST("register.php")
        @FormUrlEncoded
        Call<RegisterResponse> register(
                @Field("Name") String Name,
                @Field("Phone") String Phone,
                @Field("Email") String Email,
                @Field("Pan") String Pan,
                @Field("Aadhar") String Aadhar,
                @Field("Demat") String Demat,
                @Field("Wallet") String Wallet

        );

    @POST("trade.php")
    @FormUrlEncoded
    Call<TradeResponse> trade(
            @Field("Type") String Type,
            @Field("Stock_Name") String Stock_Name,
            @Field("Stock_Symbol") String Stock_Symbol,
            @Field("Qty") int Qty,
            @Field("Demat") String Demat,
            @Field("Price") int Price


    );
    }

