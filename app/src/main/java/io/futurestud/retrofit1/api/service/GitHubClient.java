package io.futurestud.retrofit1.api.service;

import java.util.List;

import io.futurestud.retrofit1.api.model.Game;
import io.futurestud.retrofit1.api.model.GitHubRepo;
import io.futurestud.retrofit1.api.model.Move;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by norman on 12/26/16.
 */

public interface GitHubClient {

    @GET("/aaaaaaa")
    Call<List<GitHubRepo>> reposForUser(@Path("user") String user);

    @GET("about")
    Call<String> getabout();

    @GET("games")
    Call<List<Game>> getgames();

    @POST("games")
    Call<Game> postgames(@Body Game game);

    @POST("games/{id}/moves")
    Call<Move> makeMove(
            @Path("id") Long id,
            @Body Move move);

    @GET("games/{id}")
    Call<Game> getGame(
            @Path("id") Long id
    );


}
