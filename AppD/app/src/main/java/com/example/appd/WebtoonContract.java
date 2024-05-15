package com.example.appd;
import android.provider.BaseColumns;


public final class WebtoonContract {
    public WebtoonContract() {}

    public static final class CommentEntry implements BaseColumns {
        public static final String TABLE_NAME = "comments";
        public static final String COLUMN_WEBTOON_ID = "webtoon_id";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_COMMENT = "comment";
        public static final String _ID = BaseColumns._ID;
    }
}