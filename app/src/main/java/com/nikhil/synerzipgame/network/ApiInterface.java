package com.nikhil.synerzipgame.network;

import com.nikhil.synerzipgame.models.GameResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiInterface
{

	@GET("/us/rss/newfreeapplications/limit=2/json")
	Single<GameResponse> getEntries();
}
