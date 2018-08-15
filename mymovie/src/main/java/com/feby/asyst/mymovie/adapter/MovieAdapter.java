package com.feby.asyst.mymovie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.feby.asyst.mymovie.R;
import com.feby.asyst.mymovie.model.Movie;
import com.feby.asyst.mymovie.utility.Constant;
import com.feby.asyst.mymovie.utility.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder>{


    public interface OnItemClickListener{
        void onItemClick(Movie movie);
    }

    Context mContext;
    ArrayList<Movie> mListMovie;
    OnItemClickListener listener;


    public MovieAdapter(Context context, ArrayList<Movie> mListMovie, OnItemClickListener listener) {
        this.mContext = context;
        this.mListMovie = mListMovie;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView ivMovie;
        TextView tvTitle, tvRelease, tvOverview, tvReadMore;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivMovie = itemView.findViewById(R.id.movie_imageview);
            tvTitle = itemView.findViewById(R.id.title_textview);
            tvRelease = itemView.findViewById(R.id.release_date_textview);
            tvOverview = itemView.findViewById(R.id.overview_textview);
            tvReadMore = itemView.findViewById(R.id.readmore_textview);


            cardView = itemView.findViewById(R.id.cardview);



        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemVI = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);

        return new MovieAdapter.MyViewHolder(itemVI);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Movie movie = mListMovie.get(position);

        holder.tvTitle.setText(movie.getTitle());
        if (!movie.getReleaseDate().isEmpty()) {
            holder.tvRelease.setText(DateUtils.formatDate("yyyy-MM-dd", "dd MMMM yyyy", movie.getReleaseDate()));
        }else {
            holder.tvRelease.setText("Movie Not Available");
        }
        holder.tvOverview.setText(movie.getOverview());

        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_broken).error(R.drawable.ic_broken);
        Glide.with(mContext).load(Constant.KEY_URL_IMAGE+movie.getImage()).apply(requestOptions).into(holder.ivMovie);

        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(movie);
            }
        });

    }

    @Override
    public int getItemCount() {

        return mListMovie.size();
    }
}
