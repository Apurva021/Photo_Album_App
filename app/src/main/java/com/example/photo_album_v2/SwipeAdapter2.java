package com.example.photo_album_v2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SwipeAdapter2 extends FragmentStatePagerAdapter {

    ArrayList<String> image_description;
    ArrayList<byte []> images;
    PhotoAlbumOpenHelper mDbOpenHelper;
    Context mContext;


    public SwipeAdapter2(@NonNull FragmentManager fm, String photoDescription, Context context) {
        super(fm);
        mContext=context;
        image_description=new ArrayList<>();
        images=new ArrayList<>();
        mContext=context;
        mDbOpenHelper=new PhotoAlbumOpenHelper(mContext);
        SQLiteDatabase db =mDbOpenHelper.getReadableDatabase();

        String query2="SELECT PhotoDescription,PhotoImage FROM AlbumPhotoPic WHERE PhotoDescription LIKE"+"'%"+photoDescription+"%'";
        Cursor cursor=db.rawQuery(query2,null);

        int imageDescriptionPos=cursor.getColumnIndex(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.COLUMN_PHOTO_DESCRIPTION);
        int imagePos=cursor.getColumnIndex(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.COLUMN_PHOTO_IMAGE);

        while(cursor.moveToNext())
        {
            image_description.add(cursor.getString(imageDescriptionPos));
            images.add(cursor.getBlob(imagePos));
        }

        String t="Description:"+photoDescription+" size is:"+image_description.size();
        Toast.makeText(mContext,t,Toast.LENGTH_LONG).show();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment pageFragment=new FragmentPage(image_description.get(position),images.get(position));

        return pageFragment;
    }

    @Override
    public int getCount() {
        return images.size();
    }
}
