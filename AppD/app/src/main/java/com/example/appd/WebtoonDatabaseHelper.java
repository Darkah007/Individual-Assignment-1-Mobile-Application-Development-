package com.example.appd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class WebtoonDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "webtoons.db";
    public static final int DATABASE_VERSION = 1;

    public WebtoonDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_COMMENTS_TABLE = "CREATE TABLE " + WebtoonContract.CommentEntry.TABLE_NAME + " ("
                + WebtoonContract.CommentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WebtoonContract.CommentEntry.COLUMN_WEBTOON_ID + " INTEGER NOT NULL, "
                + WebtoonContract.CommentEntry.COLUMN_USERNAME + " TEXT NOT NULL, "
                + WebtoonContract.CommentEntry.COLUMN_COMMENT + " TEXT NOT NULL);";

        db.execSQL(SQL_CREATE_COMMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_COMMENTS_TABLE = "DROP TABLE IF EXISTS " + WebtoonContract.CommentEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_COMMENTS_TABLE);
        onCreate(db);
    }
}