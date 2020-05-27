package com.example.photo_album_v2;

public final class PhotoAlbumDatabaseContract {
    private PhotoAlbumDatabaseContract(){}; //not creatable

    public static final class AlbumNameDetailsEntry
    {
        public static final String TABLE_NAME="AlbumNameDetails";
        public static final String COLUMN_USER_ID="UserId";
        public static final String COLUMN_USER_COMPLETE_NAME="UserCompleteName";
        public static final String COLUMN_ALBUM_NAME="AlbumName";

        public static final String CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+ " ("+
                COLUMN_USER_ID+" TEXT NOT NULL, "+
                COLUMN_USER_COMPLETE_NAME+" TEXT NOT NULL, "+
                COLUMN_ALBUM_NAME+" TEXT NOT NULL)";
    }

    public static final class AlbumPhotoPicEntry
    {
        public static final String TABLE_NAME="AlbumPhotoPic";
        public static final String COLUMN_USER_ID="UserId";
        public static final String COLUMN_ALBUM_NAME="AlbumName";
        public static final String COLUMN_PIC_ID="PicId";
        public static final String COLUMN_PHOTO_DESCRIPTION="PhotoDescription";
        public static final String COLUMN_PHOTO_IMAGE="PhotoImage";

        public static final String CREATE_TABLE="CREATE TABLE "+
                TABLE_NAME+" ("+
                COLUMN_USER_ID+" TEXT NOT NULL, "+
                COLUMN_ALBUM_NAME+" TEXT NOT NULL, "+
                COLUMN_PIC_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_PHOTO_DESCRIPTION+" TEXT NOT NULL, "+
                COLUMN_PHOTO_IMAGE+" BLOB NOT NULL)";
    }
}
