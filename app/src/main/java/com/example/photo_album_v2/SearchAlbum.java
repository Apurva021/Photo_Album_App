package com.example.photo_album_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchAlbum extends AppCompatActivity {

    Button viewAlbum;
    EditText albumName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_album);

        final ViewPager viewPager =(ViewPager)findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);

        albumName=(EditText)findViewById(R.id.searchAlbumNameEditText);
        viewAlbum=(Button)findViewById(R.id.viewAlbumButton);
        viewAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String album_name=albumName.getText().toString();
                SwipeAdapter swipeAdapter= new SwipeAdapter(getSupportFragmentManager(),album_name,getApplicationContext());
                viewPager.setAdapter(swipeAdapter);
                viewPager.setCurrentItem(0);
            }
        });
    }
}
