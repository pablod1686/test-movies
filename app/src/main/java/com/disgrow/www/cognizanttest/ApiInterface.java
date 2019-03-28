package com.disgrow.www.cognizanttest;


import android.graphics.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("marvel")
    Call<List<Movies>> getMovieInformation();

}
