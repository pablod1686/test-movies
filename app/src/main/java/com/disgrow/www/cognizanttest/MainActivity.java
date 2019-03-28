package com.disgrow.www.cognizanttest;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<Movies> listMovies = new ArrayList<Movies>();
    RecyclerView recyclerView;
    public static ApiInterface apiInterface;
    private int widthScreen;
    private int heightScreen;
    private Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        c = getApplicationContext();
        widthScreen = Methods.getWidthScreen(this);
        heightScreen = Methods.getHeightScreen(this, getResources());

        recyclerView = findViewById(R.id.recyclerView);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        getMovieInformation();
    }

    private void getMovieInformation() {

        Call<List<Movies>> call = apiInterface.getMovieInformation();

        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {

                for (Movies movie : response.body()) {
                    listMovies.add(movie);
                }

                if(listMovies.size() > 0){
                    iniRecyclerView();
                }

            }

            @Override
            public void onFailure(Call<List<Movies>> call, Throwable t) {
                System.out.println(t.getMessage());
                System.out.println(t.getStackTrace());
            }
        });

    }

    private void iniRecyclerView() {

        MoviesAdapter mAdapter = new MoviesAdapter(
                listMovies,
                widthScreen,
                heightScreen,
                c
        );

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(c);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter);

    }
}
