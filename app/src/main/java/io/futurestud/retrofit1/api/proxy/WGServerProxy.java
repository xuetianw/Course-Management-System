package io.futurestud.retrofit1.api.proxy;

import java.util.List;

import io.futurestud.retrofit1.api.model.Course;
import io.futurestud.retrofit1.api.model.Game;
import io.futurestud.retrofit1.api.model.Move;
import io.futurestud.retrofit1.api.model.Student;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WGServerProxy {
    @GET("games")
    Call<List<Game>> getgames();

    @GET("about")
    Call<String> getabout();

    @POST("games")
    Call<Game> postgames(@Body Game game);

    @POST("games/{id}/moves")
    Call<Move> makeMove(
            @Path("id") Long id,
            @Body Move move);

    @GET("games")
    Call<List<Game>> getAllGames(
            @Body List<Game> game);

    @GET("games/{id}")
    Call<Game> getGame(@Path("id") Long id);

    @GET("players")
    Call<Student> getAllPlayer();

    @HTTP(method = "GET", path = "/student", hasBody = true)
    Call<Student> getlPlayer(@Body Student student);

    @POST("student")
    Call<Student> make_player(@Body Student student);

    @DELETE("student")
    Call<Student> delete_player(@Body Student student);

    @PUT("student")
    Call <Student> updatePlayer(@Body Student student);

    @PUT("login")
    Call <Student> login(@Body Student student);

    @POST("/signup")
    Call<Student> sign_up(@Body Student student);

    @GET("/courses")
    Call<List<Course>> get_courses();

    @POST("/courses")
    Call<Course> add_course(@Body Course course);

}
