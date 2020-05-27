package com.example.photo_album_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class AddPhotoPic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo_pic);
        displayList();
    }

    protected void displayList()
    {
        final RecyclerView listOfAlbum=(RecyclerView)findViewById(R.id.listOfAlbumRecyclerView);
        final LinearLayoutManager albumLayoutManager=new LinearLayoutManager(this);
        listOfAlbum.setLayoutManager(albumLayoutManager);

        final AlbumListRecyclerAdapter albumListRecyclerAdapter=new AlbumListRecyclerAdapter(getApplicationContext());
        listOfAlbum.setAdapter(albumListRecyclerAdapter);

    }
}
