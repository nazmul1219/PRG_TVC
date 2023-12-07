package com.pranrflgroup.prg_tvc;

import java.util.List;

import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApInterface {

    @GET("videos/{id}")
    Call<List<Video>> getAllData(@Path("id") String id);
    @FormUrlEncoded
    @POST("/videos/video")
    Call<DeviceID>getDeviceID(@Field("D_ID") String ID);

}
