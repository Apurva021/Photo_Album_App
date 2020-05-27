package com.example.photo_album_v2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchPhotoDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_photo_description);

        final ViewPager viewPager =(ViewPager)findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);

        final EditText searchDescription=(EditText)findViewById(R.id.searchDescriptionEditText);
        Button viewSearchDescription=(Button)findViewById(R.id.viewSearchDescriptionButton);
        viewSearchDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String photo_description=searchDescription.getText().toString();
                SwipeAdapter2 swipeAdapter2=new SwipeAdapter2(getSupportFragmentManager(),photo_description,getApplicationContext());
                viewPager.setAdapter(swipeAdapter2);
                viewPager.setCurrentItem(0);
            }
        });
    }
}
