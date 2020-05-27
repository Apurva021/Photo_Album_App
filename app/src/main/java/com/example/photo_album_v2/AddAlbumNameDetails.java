package com.example.photo_album_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAlbumNameDetails extends AppCompatActivity {

    Button addData;
    PhotoAlbumOpenHelper mDbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_album_name_details);
        mDbOpenHelper=new PhotoAlbumOpenHelper(this);

        addData=(Button)findViewById(R.id.addDataButton);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userId=(EditText)findViewById(R.id.userIdEditText);
                EditText userCompleteName=(EditText)findViewById(R.id.userCompleteNameEditText);
                EditText albumName=(EditText)findViewById(R.id.albumNameEditText);

                if(userId.getText().length()==0 || userCompleteName.getText().length()==0 || albumName.getText().length()==0)
                {
                    //in case any of the field is empty. toasting to ask user to enter all fields
                    Toast.makeText(getApplicationContext(),"All fields are required",Toast.LENGTH_LONG).show();
                }
                else
                {
                    SQLiteDatabase db=mDbOpenHelper.getWritableDatabase();
                    ContentValues val = new ContentValues();
                    val.put(PhotoAlbumDatabaseContract.AlbumNameDetailsEntry.COLUMN_USER_ID,userId.getText().toString());
                    val.put(PhotoAlbumDatabaseContract.AlbumNameDetailsEntry.COLUMN_USER_COMPLETE_NAME,userCompleteName.getText().toString());
                    val.put(PhotoAlbumDatabaseContract.AlbumNameDetailsEntry.COLUMN_ALBUM_NAME,albumName.getText().toString());
                    db.insert(PhotoAlbumDatabaseContract.AlbumNameDetailsEntry.TABLE_NAME,null,val);
                    userId.setText("");
                    userCompleteName.setText("");
                    albumName.setText("");
                    //Toast to inform user that the data is recorded successfully
                    Toast.makeText(getApplicationContext(),"Record Added Successfully.",Toast.LENGTH_LONG).show();
                    db.close();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
