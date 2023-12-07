package com.pranrflgroup.prg_tvc;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostRetrofitClient {
    public static Retrofit retrofit;
    public  static Retrofit PostRetrofitInstance(){
        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl("http://172.17.107.160:3030/").
                    addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
