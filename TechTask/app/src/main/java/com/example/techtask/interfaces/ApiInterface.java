package com.example.techtask.interfaces;

import com.example.techtask.classes.JSON;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("2.3/users?order=desc&sort=reputation&site=stackoverflow&filter=!*MZqiH2U.gWyd0q6")
    Call<JSON> getUsers();
}

