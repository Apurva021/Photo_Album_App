package com.example.photo_album_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddPhotoPicToAlbum extends AppCompatActivity {

    final int PICK_IMAGE=100;
    Uri imageUri;
    ImageView selectImage;
    Button saveImage;
    EditText imageDescription;
    PhotoAlbumOpenHelper mDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo_pic_to_album);

        mDbOpenHelper=new PhotoAlbumOpenHelper(this);
        selectImage=(ImageView)findViewById(R.id.selectImageView);
        saveImage=(Button) findViewById(R.id.saveImageButton);
        imageDescription=(EditText)findViewById(R.id.imageDescriptionEditText);

        Bundle extras=getIntent().getExtras();
        final String userId,albumName;
        userId=extras.getString("userid");
        albumName=extras.getString("albumName");

        String check="userid:"+userId+" albumName:"+albumName;
        Toast.makeText(getApplicationContext(), check, Toast.LENGTH_SHORT).show();

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent,PICK_IMAGE);
            }
        });

        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BitmapDrawable drawable = (BitmapDrawable) selectImage.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                bitmap.recycle();

                SQLiteDatabase db =mDbOpenHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.COLUMN_USER_ID,userId);
                values.put(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.COLUMN_ALBUM_NAME,albumName);
                values.put(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.COLUMN_PHOTO_DESCRIPTION,imageDescription.getText().toString());
                values.put(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.COLUMN_PHOTO_IMAGE,byteArray);
                db.insert(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.TABLE_NAME,null,values);
                db.close();
                imageDescription.setText("");
                selectImage.setImageResource(0);
                Toast.makeText(getApplicationContext(),"Recorded Succesfully",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUri = data.getData();
            selectImage.setImageURI(imageUri);
        }
    }


}


