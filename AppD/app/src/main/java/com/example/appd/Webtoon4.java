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

public class Webtoon4 extends AppCompatActivity {

    Cursor cursor;
    ListView commentsListView;
    SimpleCursorAdapter commentsAdapter;
    long webtoonId;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtoon4);

        commentsListView = findViewById(R.id.comments_list4);
        webView = findViewById(R.id.webview4);

        Spinner episodeSpinner = findViewById(R.id.episode_spinner4);
        String[] episodeTitles = {"Episode 1", "Episode 2", "Episode 3"};
        String[] episodeUrls = {"https://ndisk.cizgifilmlerizle.com/getvid?evid=28uKijx3UqCZTY2396yzaiKm8Z7VBMxSPW_HR245ifOMem2Mk9H7q89N9jOZLTHDMfFIs9z7X5nfyCxqZYiGDDBZ3N-pe4t301roWDhgH0ISqYIQiffTtO3O8x3BtPP-l0uDOKnBESog8_zhFhiZkjE4y0vCuh5itlPnJ0GhC5wjY5n0UoQLhhz36JyfCJ_UNtJa6bsJGB-PPPVharVPQWQP9natILZfPg6hWEJZf0O0pdpurHElp2OyQElRmi8SbFGkxd6vVaXzUj_cFmrMRkGlZbF2R34-37r6A7lWP2EU3H3ioEpaRPoaYJJtMKm9Dyr3-92gX0wUE5oTivVJx2IhkvzTNxyA4Tde_B8-jt7rLZN9e2t6ZARt84ZrJNOZAAXcVlNsiJsbgsGMXyR1lbWF5yQ-CdUpgkSQIJdqDt8yjicwL0Sba6zRpJnkqYAQHUfDKrHEzcmuwAfqUmmJuLXMiIPsekDhcLeGUhJVzBgwuv4XmHGOEzKssw3-fiy_B5tGWV75pc7NG8uPDUq-atd7kCs0tg_YT3siE0VNLMw",
                "https://ndisk.cizgifilmlerizle.com/getvid?evid=28uKijx3UqCZTY2396yzaiKm8Z7VBMxSPW_HR245ifOMem2Mk9H7q89N9jOZLTHDMfFIs9z7X5nfyCxqZYiGDEihRf6fzN6M_AQsH8HYsPAaYBuJEodhkpf9D_GhnUhHTWFaKVHGneOARDYyufAbsCEqBN6GO6hZiJU5H8SSKJwO0rjftgLCNVsrsqrRV1PcTym-8ma17c6y2foALKttlwXG4p8Rv6Q8eSx-KpSCzViKmoXtScu4Qw6DsgwWHbwkeZS2Hwi2nN7r9MZSG4qT_ql0ed1ChP6rfuC5jA6quGUq6vJ6PmtwKipsXFHjFIJM6qjDpDZdaUrUnVLtGoLwdUC19YruYZnJ55U_kIklB5wdwch2aAORt0GWdW0kzCO1ScKaI-cZ7Ql0FJm-euaOp9_j6UhGJGps9vvMweRx0YRI_08z953lU2E4f6h-hYRM1aXp5mLUVcsAyBY_36iy1jGv3EaupFUwIktJWV0ZZLhLIDhpsxWKfuKD74CfNi6MZ-yalIJPxhinP8NPc_Ha4Ops0QhWnyLJRAEbMd6EpZk",
                "https://ndisk.cizgifilmlerizle.com/getvid?evid=28uKijx3UqCZTY2396yzaiKm8Z7VBMxSPW_HR245ifOMem2Mk9H7q89N9jOZLTHDMfFIs9z7X5nfyCxqZYiGDMQE8M4RK86VdnBpWrJrYdNHhYxURTxzKY56hoDrhaQjulVDdSBRD6IdPWtz40FuskZfeCRah0o9F-CzolXQVgno7jMkFiiV6X_ozdTkEJXJYn0GSDwrixH8EwipA95BIhId8cp3sufLKrIHv_-HsVngGRBQvD5PVt2z3wCATBN9kM8jdrIAVVi0lnhQaubg_zLV9QnUTAlNyU6Kt3PDnNnPyHIcD70wT7Y2Cj48Xj6QsnEAtRadzogxte7W_dGduoBe5XMwSuU5Gg8eHqkePR7uD6MNtm0NWuHdMmBJMwm79ZTwjq6XXGTCwUpfrnDQ1pXpRgIlNJFdtiHaQ0JS9LUlR5e-2CAjfODhCV165hu3_8hdZeaJJ0CS0GtcjGH6UNBQhcwYN-szWKi5LLCsF84sdgZanHaTcM8A3DjaCiqjzR39E-raTwLcJ0fLItyzmpSWrOGNjdSy758uP5IwrY4"};
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

        webtoonId = 4; // Set the webtoon ID here
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
        public static final String DATABASE_NAME = "webtoons4.db";
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
