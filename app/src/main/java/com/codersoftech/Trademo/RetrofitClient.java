package com.codersoftech.Trademo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static String Base_url="https://codersoftech.com/Trademo/api/";
    public static RetrofitClient retrofitClient;
    public static Retrofit retrofit;
    private RetrofitClient(){
        retrofit= new Retrofit.Builder().baseUrl(Base_url).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public static synchronized RetrofitClient getInstance(){
        if (retrofitClient==null){
            retrofitClient  = new RetrofitClient();
        }
        return retrofitClient;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
