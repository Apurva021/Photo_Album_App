package com.example.photo_album_v2;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photo_album_v2.PhotoAlbumDatabaseContract.AlbumNameDetailsEntry;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AlbumListRecyclerAdapter extends RecyclerView.Adapter<AlbumListRecyclerAdapter.ViewHolder>{

    private final Context mContext;
    private final LayoutInflater layoutInflater;

    private PhotoAlbumOpenHelper mDbOpenHelper;
    private ArrayList<String> list_user_id;
    private ArrayList<String> list_album_name;



    public AlbumListRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
        layoutInflater = LayoutInflater.from(mContext);
        mDbOpenHelper=new PhotoAlbumOpenHelper(mContext);
        list_user_id=new ArrayList<>();
        list_album_name=new ArrayList<>();

        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
        Cursor cursor = db.query(AlbumNameDetailsEntry.TABLE_NAME,
                new String[]{AlbumNameDetailsEntry.COLUMN_USER_ID, AlbumNameDetailsEntry.COLUMN_ALBUM_NAME},
                null, null, null, null, null);


        int userIdPos=cursor.getColumnIndex(AlbumNameDetailsEntry.COLUMN_USER_ID);
        int albumNamePos=cursor.getColumnIndex(AlbumNameDetailsEntry.COLUMN_ALBUM_NAME);
        while (cursor.moveToNext())
        {
            list_user_id.add(cursor.getString(userIdPos));
            list_album_name.add(cursor.getString(albumNamePos));
        }

        /*SQLiteDatabase db=mDbOpenHelper.getReadableDatabase();
        Cursor cursor=db.query(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.TABLE_NAME,
                new String[]{PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.COLUMN_ALBUM_NAME, PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.COLUMN_PHOTO_DESCRIPTION},
                null,null,null,null,null);
        int id1=cursor.getColumnIndex(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.COLUMN_ALBUM_NAME);
        int id2=cursor.getColumnIndex(PhotoAlbumDatabaseContract.AlbumPhotoPicEntry.COLUMN_PHOTO_DESCRIPTION);

        while (cursor.moveToNext())
        {
            list_user_id.add(cursor.getString(id1));
            list_album_name.add(cursor.getString(id2));
        }*/
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=layoutInflater.inflate(R.layout.item_album_list,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.listUserId.setText(list_user_id.get(position));
        holder.listAlbumName.setText(list_album_name.get(position));
        holder.mCurrentPosition=position;
    }

    @Override
    public int getItemCount() {
        return list_user_id.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        public final TextView listUserId;
        public final TextView listAlbumName;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listUserId = (TextView)itemView.findViewById(R.id.listUserIdTextView);
            listAlbumName = (TextView)itemView.findViewById(R.id.listAlbumNameTextView);

            final String user_id=listUserId.getText().toString();
            final String album_name=listAlbumName.getText().toString();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"userid:"+user_id+"  album_name:"+album_name,Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(mContext,AddPhotoPicToAlbum.class);
                    intent.putExtra("Position",mCurrentPosition);
                    intent.putExtra("userid",list_user_id.get(mCurrentPosition));
                    intent.putExtra("albumName",list_album_name.get(mCurrentPosition));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
