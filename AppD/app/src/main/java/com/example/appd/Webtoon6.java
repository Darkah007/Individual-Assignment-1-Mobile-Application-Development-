package com.example.appd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

public class Webtoon6 extends AppCompatActivity {

    Cursor cursor;
    ListView commentsListView;
    SimpleCursorAdapter commentsAdapter;
    long webtoonId;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtoon6);

        commentsListView = findViewById(R.id.comments_list6);
        webView = findViewById(R.id.webview6);

        Spinner episodeSpinner = findViewById(R.id.episode_spinner6);
        String[] episodeTitles = {"Episode 1", "Episode 2", "Episode 3"};
        String[] episodeUrls = {"https://cdn.cizgifilmlerizle.com/getvid?evid=avyp8tEE81uGAP6NbFuQOjLrb1SPQwbrQuw-CJcMPbii4RGizN16eItVNpvUeLVjQ6adjHxZ5kpeNBjBc0hDoNp54-DTtWvFkg8dPrG5f6YLRY6IjefnLxnJS2M811m4wUX_MTmYQe7H5eBavIoIcGvW9BmSOvo7XBmeAxcp6Uqs9m5uEJatv7Iuyikn4iT49A8j09JCT-RbHojsQI1tUqFhjhGmJJJXIf_nMyQ3H5StVMbX2w9UM_Zfc9FGlINPfpmRaLX9SQepA1fsnS0zzpe1XK94v8WT5N1A0MU6DH6zPhXsT2-qFWJqmSqKeDJAVbxHUDBHtcNyQLJdNU1qMrwLnIDG6ipvGZIOyl1sgmVwkjTXpAMwxPxzj89aWhsCal9LjhmBv3eXEO-0itKEAaOApdRIJ-SUxjGO8YJRby8xjhu4I_QPp0SCGzukQcBKL65fwFiiTC82qLCLRzNZiBZsCflyx9DMT4BytuTME_BMXoB-VrlpVTcsxWRYUTwxs1ZC_P05t6vrz60ghoiv3AK6AX7cdsmjsbKzhPHW4ug",
                "https://cdn.cizgifilmlerizle.com/getvid?evid=avyp8tEE81uGAP6NbFuQOjLrb1SPQwbrQuw-CJcMPbii4RGizN16eItVNpvUeLVjGYHwJa5f0CtGY74ZHnO7DKY3evP0XdR4hudVxWze-e734Hbeniea7wzJuDFLdclDhlyMiAeZ5Dh1EZguhXvvk7QICavDXc6z-WvXqF-ZFncW7rWtN5S71ftKCdtiygtxdMkfRB3614k-hFqEk_6Ia_mfq0jPTzmMIEepXbwgejBgCZ2Y1WjYEaEaV-_e6_-DbgR4G0txcly6EXDhKxryueHf0QwuALFsThyH1-_UhbCx42tIAtWmBGsSVnrPMcjziRShqQlpOQZ01zYptt8lb7-0uWfXCN7nk9-GSoodtrgSJJE_z9ZMKRwv6Sj9e1hcOi2qSxwGNDJbpZ66_8GbIun8T3jB07WH3le_QjuLFONqpNBCL4WzRwfUlAYMahhuXRTE5VvnHUxiwv9bcr0OZsrn5XXrNcG5eSHReKobDIJwj61Jskw3hT0Ze5AXU6UsPgvQSNo9KO5DysttCvw-oQ",
                "https://cdn.cizgifilmlerizle.com/getvid?evid=avyp8tEE81uGAP6NbFuQOlbQkZvpToISEP17TaThotahmkDSSuu_tCiHVxbVX7JVeqIqnzmLltjEkB9oxzZc5EgqjRagbfEGDZSCsE9JmgVt1kIbxeIDBDX5tNSV2TlBtpB6DaZdqe1P_BQa6NamMjwqFxsxM2VETCnSFzDhenKnHPl3Ezk0VLVuh5h_N7B4731TZ8foE_xNGjR0XeWMCYifkJw5cR637G-BRb3pl47ZHuKYM6UnL5Ciz42kv57NF8-njIAfM-ZUEgaElOJiWbJ2IeNNdlzB-RkWf6AxZi78Q6QOiWfoXeSYNeaOo87za2zZadDoHqYDAZOUjqwd1xdS6aagepCG8XClGSw_WMLXvEjjHFNIAqd1tgd4k5cAGVzITINXc5gJfwIbZfU8hAmxe7ltiP8jwmJEoVpxkeRL3mnBF3umnMgYyFWrb6m9XgZ4idNncsqJBxdRqO_N8g"};
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

        Button submitCommentButton = findViewById(R.id.submit_comment_button);
        submitCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveComment();
            }
        });

        WebtoonDatabaseHelper dbHelper = new WebtoonDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        webtoonId = 6; // Set the webtoon ID here
        cursor = db.query(
                WebtoonContract.CommentEntry.TABLE_NAME,
                null,
                WebtoonContract.CommentEntry.COLUMN_WEBTOON_ID + " = ?",
                new String[]{String.valueOf(webtoonId)},
                null,
                null,
                null
        );

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String[] from = {WebtoonContract.CommentEntry.COLUMN_USERNAME, WebtoonContract.CommentEntry.COLUMN_COMMENT};
        int[] to = {android.R.id.text1, android.R.id.text2};
        commentsAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to, 0);
        commentsListView.setAdapter(commentsAdapter);
    }

    private void saveComment() {
        String username = ((EditText) findViewById(R.id.comment_username)).getText().toString();
        String comment = ((EditText) findViewById(R.id.comment_text)).getText().toString();

        if (username.isEmpty() || comment.isEmpty()) {
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

    public static class WebtoonDatabaseHelper extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "webtoons6.db";
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
