package com.example.photo_album_v2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PhotoAlbumOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="PhotoAlbum.db";
    public static final int DATABASE_VERSION=1;
    public PhotoAlbumOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PhotoAlbumDatabaseContract.AlbumNameDetailsEntry.CREATE_TABLE);
        db.execSQL(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query1="DROP TABLE IF EXISTS "+PhotoAlbumDatabaseContract.AlbumNameDetailsEntry.TABLE_NAME;
        String query2="DROP TABLE IF EXISTS "+PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.TABLE_NAME;
        db.execSQL(query1);
        db.execSQL(query2);

    }
}
