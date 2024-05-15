package com.example.appd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;

public class Webtoon1 extends AppCompatActivity {

    // Add this code inside the Webtoon1 class
    Cursor cursor;
    ListView commentsListView;
    SimpleCursorAdapter commentsAdapter;
    long webtoonId;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtoon1);

        // Initialize your views
        commentsListView = findViewById(R.id.comments_list);
        webView = findViewById(R.id.webview);

        // Set up the spinner
        Spinner episodeSpinner = findViewById(R.id.episode_spinner);
        String[] episodeTitles = {"Episode 1", "Episode 2", "Episode 3"};
        String[] episodeUrls = {"https://ndisk.cizgifilmlerizle.com/getvid?evid=28uKijx3UqCZTY2396yzaiKm8Z7VBMxSPW_HR245ifOFdVRk_-lSZ3wdMKXQz5uMt2lqNnjwTB7u19Y2KHYjq50hcyunIx6BeB7yXpUg2NQ2LJuRp4wjqy1J_MNlEszFhVJt-HucoOSSZnlLkd-6c6Z86aQx1b0rI5TLaNA4B8STTKFwih_1X08rEGCI9Uem8V5Kc-wtYtsdzQiMnOTH7l6cnBSccj4Ef8afcX_l_Zft4pTwqpFtBo2Y6tfgrkPbozU5wE7h1lSRayKC0efOWMIj5-Gs--9NwcAu9KU9gMHrviW3-0Np4AdTTUV7oRZJ5cwZtWh0CwJDzQ2p522LjlvCpIQx-qjihPJaFD1oBNQFUqdrZ-x41NtMoVTazOhsaxXqHXR2WsPcPcLDUK9cC5Laf5MDaEZgfUUbbvJKuTCTBb4KcbG28ucvaKSKbjknoZY6ba755DoSKvVDlbVkwN7VZedT5xChNfqs9cCBq47JxBoMKlFfI-4o29FIV9geyLUA6N2yZDNbOeUXXXBm-fzNdd-wig4WnIPg1hgwhvQdmc33iUzeM5OfgYOmxrPw",
                "https://ndisk.cizgifilmlerizle.com/getvid?evid=28uKijx3UqCZTY2396yzaiKm8Z7VBMxSPW_HR245ifOFdVRk_-lSZ3wdMKXQz5uMt2lqNnjwTB7u19Y2KHYjqzTqGpFHJOFMHO_Z-RsBqQ6CgDWJbzcLbUYi06LVwc5xUNS6ZZD2nRDUKtOvKV5KrNPHBx2F7y2_4YzTbsQq71_WxIhKNvw9l9FRWMp4eRQCaQRt8d6PBHub_c1IjFNCXwMOzljPKQWqpyyQQkbnzyDaIZMN0i3Lbepup2vc97oDTxFnWnf_HqyuBYySToWGknk5SOtfz1okG6E4j9ubaqEn0pUyXHxIceayXAW5P1xBfjROst0fxzueO8qKU6qqov6XwrpgAObH170lX5jw_RNPAAD9YDtGVkWkYRan7KxCj0KybyTxbCnGAlvsGRU1ev62CKb3D2wvNY-vybwsXhm_uXq5jyv43IeJSPjT6YLLJqUyStM2uFlcnpifEWtJQVkxsR9jf_PpzG_7eXAqUj9kFIxFiNaIkcgfwXsX-fQBgjcWBZyvB8-Bio0FHHFiksn4ui9Q_0yBw89cR9wDnjYuqLWVZlA09LID3lxKJCHp",
                "https://ndisk.cizgifilmlerizle.com/getvid?evid=28uKijx3UqCZTY2396yzaiKm8Z7VBMxSPW_HR245ifOFdVRk_-lSZ3wdMKXQz5uMt2lqNnjwTB7u19Y2KHYjq1yS786hH0eZayy2hLdEla1vMonHns6SFAxok4tgvy_lymgwpNNCHwtuIbSBpHDvo8URkwkuWdldb3HUvTEsymgJtfpBI8POKdEPp-txCwlmpj7eC06i2UDNQIqU1FmPaX3ybTQSLTGowHHzciXd7OIWYwz46nZR52vUxskdgwtqFP_HrzOgUwutuQghFqqbqzRnm1nYmLX2iobSCRh4bWnSwq4UGTYOtR7cED0LrOVMIEc6_-W1ZUv0q_tGl8xHB2RF6Eb3oMXOH9HZOcCAPbml9-XlR9bd9fdH6HIyH1mr-ut3A6Upzel8gTyi3bLMtbiDZ0BmaRXvTHiEBNdCfapIIkFN64u_oZJxQfbXNFtuEXM6Rbq1700UlsLzrU_SKqdShwuDeEluu3KWpiZJPPxHo2qt3db72y-PMOnuuJX9OZem0NLi_5CjMIDMuoHX5qRkezaYzgDN8km8SaDrQl0zIOJBxrdqiL4ZvdOZUxp_"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, episodeTitles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        episodeSpinner.setAdapter(adapter);

        episodeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                webView.loadUrl(episodeUrls[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do something when nothing is selected
            }
        });

        // Set up the submit button
        Button submitCommentButton = findViewById(R.id.submit_comment_button);
        submitCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveComment();
            }
        });

        // Initialize and set up the database
        WebtoonDatabaseHelper dbHelper = new WebtoonDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Query the comments for this webtoon
        webtoonId = 1; // Set the webtoon ID here
        cursor = db.query(
                WebtoonContract.CommentEntry.TABLE_NAME,
                null,
                WebtoonContract.CommentEntry.COLUMN_WEBTOON_ID + " = ?",
                new String[]{String.valueOf(webtoonId)},
                null,
                null,
                null
        );

        // Set up the WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set up the comments list view
        String[] from = {WebtoonContract.CommentEntry.COLUMN_USERNAME, WebtoonContract.CommentEntry.COLUMN_COMMENT};
        int[] to = {android.R.id.text1, android.R.id.text2};
        commentsAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to, 0);
        commentsListView.setAdapter(commentsAdapter);
    }

    private void saveComment() {
        String username = ((EditText) findViewById(R.id.comment_username)).getText().toString();
        String comment = ((EditText) findViewById(R.id.comment_text)).getText().toString();

        if (username.isEmpty()|| comment.isEmpty()) {
            Toast.makeText(this, "Please enter a username and comment", Toast.LENGTH_SHORT).show();
            return;
        }

        WebtoonDatabaseHelper dbHelper = new WebtoonDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WebtoonContract.CommentEntry.COLUMN_WEBTOON_ID, webtoonId);
        values.put(WebtoonContract.CommentEntry.COLUMN_USERNAME, username);
        values.put(WebtoonContract.CommentEntry.COLUMN_COMMENT, comment);

        long newRowId = db.insert(WebtoonContract.CommentEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Toast.makeText(this, "Error saving comment", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Comment saved", Toast.LENGTH_SHORT).show();
            // Update the list view
            commentsAdapter.changeCursor(db.query(
                    WebtoonContract.CommentEntry.TABLE_NAME,
                    null,
                    WebtoonContract.CommentEntry.COLUMN_WEBTOON_ID + " = ?",
                    new String[]{String.valueOf(webtoonId)},
                    null,
                    null,
                    null
            ));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cursor != null) {
            cursor.close();
        }
    }

    // Add the WebtoonDatabaseHelper class inside the Webtoon1 class
    public static class WebtoonDatabaseHelper extends SQLiteOpenHelper {
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
}