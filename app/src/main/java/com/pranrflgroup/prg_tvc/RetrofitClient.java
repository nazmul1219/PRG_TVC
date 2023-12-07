package com.pranrflgroup.prg_tvc;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
 private String BASE_URL="http://172.17.107.160:3030";
 private Retrofit retrofit;
 private static RetrofitClient retrofitClient;
 private RetrofitClient(){

     retrofit=new Retrofit.Builder()
             .baseUrl(BASE_URL)
             .addConverterFactory(GsonConverterFactory.create()).build();
 }
public static synchronized RetrofitClient getInstance(){
     if (retrofitClient==null){
         retrofitClient=new RetrofitClient();
     }
     return retrofitClient;
}
public ApInterface getApi(){
     return retrofit.create(ApInterface.class);
}
}
