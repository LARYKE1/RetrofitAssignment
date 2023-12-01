package com.example.retrofitassignment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface api {


    @GET("photos")
    Call<List<model>> getProducts();
}
