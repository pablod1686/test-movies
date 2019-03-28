package com.disgrow.www.cognizanttest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.signature.StringSignature;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private final List<Movies> movieList;
    private int widthScreen;
    private int heightScreen;
    private Context c;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imMovie;
        private final LinearLayout llMovieInformation;
        private final TextView tvName;
        private final TextView tvRealName;
        private final TextView tvTeam;
        private final TextView tvFistAppearence;
        private final TextView tvCreatedBy;
        private final TextView tvPublished;
        private final TextView tvBio;

        public MyViewHolder(View view) {
            super(view);

            imMovie = view.findViewById(R.id.imMovie);
            llMovieInformation = view.findViewById(R.id.llMovieInformation);
            tvName = view.findViewById(R.id.tvName);
            tvRealName = view.findViewById(R.id.tvRealName);
            tvTeam = view.findViewById(R.id.tvTeam);
            tvFistAppearence = view.findViewById(R.id.tvFistAppearence);
            tvCreatedBy = view.findViewById(R.id.tvCreatedBy);
            tvPublished = view.findViewById(R.id.tvPublished);
            tvBio = view.findViewById(R.id.tvBio);

            Methods.setParamsView(imMovie, w(40), w(40));
            Methods.setParamsView(llMovieInformation, w( 60), 0);

            Methods.setMargenes(tvName,w(3),0,0,0);
            Methods.setMargenes(tvRealName,w(3),0,0,0);
            Methods.setMargenes(tvTeam,w(3),0,0,0);
            Methods.setMargenes(tvFistAppearence,w(3),0,0,0);
            Methods.setMargenes(tvCreatedBy,w(3),0,0,0);
            Methods.setMargenes(tvPublished,w(3),0,0,0);
            Methods.setMargenes(tvBio,w(3),0,0,0);

        }
    }

    public MoviesAdapter(List<Movies> moviesList, int widthScreen, int heightScreen, Context c) {
        this.movieList = moviesList;
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        this.c = c;
    }

    @NonNull
    @Override
    public MoviesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int position) {

        final Movies movie = movieList.get(position);
        myViewHolder.tvName.setText(movie.getName());
        myViewHolder.tvRealName.setText(movie.getRealname());
        myViewHolder.tvTeam.setText(movie.getTeam());
        myViewHolder.tvFistAppearence.setText(movie.getFirstappearance());
        myViewHolder.tvCreatedBy.setText(movie.getCreatedby());
        myViewHolder.tvPublished.setText(movie.getPublisher());
        myViewHolder.tvBio.setText(movie.getBio());

        getImageFromUrl(c,movie.getImageurl(),myViewHolder.imMovie);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public int w(float percent) {
        return (int) (widthScreen * (percent / 100));
    }

    public int h(float percent) {
        return (int) (heightScreen * (percent / 100));
    }


    public void getImageFromUrl(final Context c, String url, final ImageView imgView) {
        Glide.with(c)
                .load(url)
                .asBitmap()
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .format(DecodeFormat.PREFER_ARGB_8888)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bm, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap mBitmap = resizeBitmap(bm, w(60));
                        imgView.setImageBitmap(mBitmap);
                    }
                });
    }

    private Bitmap resizeBitmap(Bitmap bmap, int tam) {

        double percentaje = 0;
        double div = 100;
        double width = bmap.getWidth();
        double height = bmap.getHeight();

        if (bmap.getWidth() > bmap.getHeight()) {
            percentaje = (tam * 100) / width;
        } else if (bmap.getHeight() >= bmap.getWidth()) {
            percentaje = (tam * 100) / height;
        }

        percentaje = percentaje / div;
        Bitmap bm = Methods.resizeImage(bmap, (float) (bmap.getWidth() * percentaje), (float) (bmap.getHeight() * percentaje));
        return bm;

    }


}
