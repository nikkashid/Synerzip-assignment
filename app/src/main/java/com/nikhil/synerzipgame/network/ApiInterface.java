package com.nikhil.synerzipgame.network;

import com.nikhil.synerzipgame.models.GameResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/us/rss/newfreeapplications/limit=2/json")
    Call<GameResponse> getEntries();
}
