package com.feby.asyst.learnrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.feby.asyst.learnrecyclerview.adapter.AlbumAdapter;
import com.feby.asyst.learnrecyclerview.model.Album;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AlbumAdapter albumAdapter;
    ArrayList<Album> listAlbum = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);

        for (int i=0; i<10; i++){
            Album album = new Album();
            album.setTitle("Title : "+i);
            album.setArtist("Artist : "+i);
            album.setImage("https://images-na.ssl-images-amazon.com/images/I/61McsadO1OL.jpg");
            listAlbum.add(album);
        }

        albumAdapter = new AlbumAdapter(this, listAlbum);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(albumAdapter);


    }
}
