package com.example.photo_album_v2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.photo_album_v2.PhotoAlbumDatabaseContract.AlbumPhotoPicEntry;

import java.util.ArrayList;

public class SwipeAdapter extends FragmentStatePagerAdapter {

    ArrayList<String> image_description;
    ArrayList<byte []> images;
    PhotoAlbumOpenHelper mDbOpenHelper;
    Context mContext;

    //albumName required to display images from speific album
    public SwipeAdapter(@NonNull FragmentManager fm, String albumName, Context context) {
        super(fm);
        image_description=new ArrayList<>();
        images=new ArrayList<>();
        mContext=context;
        mDbOpenHelper=new PhotoAlbumOpenHelper(mContext);
        SQLiteDatabase db =mDbOpenHelper.getReadableDatabase();

        String query="SELECT "+AlbumPhotoPicEntry.COLUMN_PHOTO_DESCRIPTION+","+AlbumPhotoPicEntry.COLUMN_PHOTO_IMAGE+
                " FROM "+AlbumPhotoPicEntry.TABLE_NAME+
                " WHERE "+AlbumPhotoPicEntry.COLUMN_ALBUM_NAME+"=?";
        String query2="SELECT PhotoDescription,PhotoImage FROM AlbumPhotoPic WHERE AlbumName="+"'"+albumName+"'";
        Cursor cursor=db.rawQuery(query2,null);



        /*String selection= AlbumPhotoPicEntry.COLUMN_ALBUM_NAME+"=?";
        String selectionArgs[]={albumName};

        String columns[]={AlbumPhotoPicEntry.COLUMN_PHOTO_DESCRIPTION,
            AlbumPhotoPicEntry.COLUMN_PHOTO_IMAGE};

        Cursor cursor=db.query(AlbumPhotoPicEntry.TABLE_NAME,columns,selection,selectionArgs,null,null,null);
        //Cursor cursor=db.query(AlbumPhotoPicEntry.TABLE_NAME,columns,selection,selectionArgs,null,null,null);*/
        int imageDescriptionPos=cursor.getColumnIndex(AlbumPhotoPicEntry.COLUMN_PHOTO_DESCRIPTION);
        int imagePos=cursor.getColumnIndex(AlbumPhotoPicEntry.COLUMN_PHOTO_IMAGE);

        while(cursor.moveToNext())
        {
            image_description.add(cursor.getString(imageDescriptionPos));
            images.add(cursor.getBlob(imagePos));
        }

        String t="Album Name:"+albumName+" size is:"+image_description.size();
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
        return image_description.size();
    }

}
