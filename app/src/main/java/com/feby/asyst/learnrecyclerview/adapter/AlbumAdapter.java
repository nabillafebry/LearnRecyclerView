package com.feby.asyst.learnrecyclerview.adapter;

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
import com.feby.asyst.learnrecyclerview.R;
import com.feby.asyst.learnrecyclerview.model.Album;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder>{

    Context mContext;
    ArrayList<Album> mListAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> listAlbum) {
        this.mContext = context;
        this.mListAlbum = listAlbum;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView ivAlbum;
        TextView tvAlbum, tvArtist;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivAlbum = itemView.findViewById(R.id.album_imageview);
            tvAlbum = itemView.findViewById(R.id.album_textview);
            tvArtist = itemView.findViewById(R.id.artist_name_textview);

            cardView = itemView.findViewById(R.id.cardview);


        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemVI = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false);

        return new AlbumAdapter.MyViewHolder(itemVI);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Album album = mListAlbum.get(position);

        holder.tvArtist.setText(album.getArtist());
        holder.tvAlbum.setText(album.getTitle());

        Glide.with(mContext).load(album.getImage()).into(holder.ivAlbum);

    }

    @Override
    public int getItemCount() {
        return mListAlbum.size();
    }

}
