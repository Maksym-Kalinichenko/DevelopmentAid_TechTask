package com.example.techtask.interfaces;

import com.example.techtask.classes.JSONTags;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiInterfaceTags {

    @GET
    Call<JSONTags> getTags(@Url String url);
}
