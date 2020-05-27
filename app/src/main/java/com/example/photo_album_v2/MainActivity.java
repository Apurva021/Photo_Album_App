package com.example.photo_album_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addAlbumNameDetails;
    Button addPhotoPic;
    Button searchAlbumName;
    Button searchPhotoDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addAlbumNameDetails=(Button)findViewById(R.id.addAlbumNameDetailsButton);
        addAlbumNameDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddAlbumNameDetails.class);
                startActivity(intent);
            }
        });

        addPhotoPic=(Button)findViewById(R.id.addPhotoPicButton);
        addPhotoPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AddPhotoPic.class);
                startActivity(intent);
            }
        });

        searchAlbumName=(Button)findViewById(R.id.searchAlbumNameButton);
        searchAlbumName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SearchAlbum.class);
                startActivity(intent);
            }
        });

        searchPhotoDescription=(Button)findViewById(R.id.searchImageDescriptionButton);
        searchPhotoDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SearchPhotoDescription.class);
                startActivity(intent);
            }
        });

    }
}
