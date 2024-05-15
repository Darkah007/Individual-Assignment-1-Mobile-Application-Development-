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

public class Webtoon2 extends AppCompatActivity {

    // Add this code inside the Webtoon2 class
    Cursor cursor;
    ListView commentsListView;
    SimpleCursorAdapter commentsAdapter;
    long webtoonId;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtoon2);

        // Initialize your views
        commentsListView = findViewById(R.id.comments_list2);
        webView = findViewById(R.id.webview2);

        // Set up the spinner
        Spinner episodeSpinner = findViewById(R.id.episode_spinner2);
        String[] episodeTitles = {"Episode 1", "Episode 2", "Episode 3"};
        String[] episodeUrls = {"https://ndisk.cizgifilmlerizle.com/getvid?evid=VW7yFllHvotlsJFQMT5L81ByDmUtqk0fgSaWeumZr0YOFYPGoK2QNi-Wqo3DEnzmpA2W1rrIGJJXh0WBUHlX6IYlWtNEzLYEYJBAoAYrF9alkUVy-P-pe8gsWNRW9TA5yD4kOOvDCFPSv1dtZ85yn4LEZ8yW21sVZdN_qAvGhCDdF8CIy7QIOUqDq_bKz2uAsEfAG8iQ62oQS0dA6ruXmuYiS9iT7FX-IbUB5P42fTnBgKU6HeWzsP83X-51Le9k4f0-dT_zagDA7zh_5D5T6Uv8q37TzfymabK2mxHsFmkJNXSV4KKj-LSvWDrh4ZBsOwk_GJAn2Sugb_T71NTvNzkZyKsMRH_QlqMkrL0Zs_zDSNPEROkDVzav_lLF71dKCjyH1Yn4akwNvAhj9fRDAZB1lEHIKceikoOkEaFnxHT9ee9SgCS80tdAqv82bHCs6xHsFQqtqQrjCtiE4FG0IM2fvbqdPbhrkZFuIBegC-taGTvmud6EE0YxSXRSxmKl_SHrsMx_uxoYo6ArVno8pcdb9AVIWoc3kBiCjwzhZWY",
                "https://ndisk.cizgifilmlerizle.com/getvid?evid=VW7yFllHvotlsJFQMT5L81ByDmUtqk0fgSaWeumZr0YOFYPGoK2QNi-Wqo3DEnzmpA2W1rrIGJJXh0WBUHlX6HsfDeQsXNRagNY9WgPN2F5DBA93iqryH9Xj-qlaG_BWhxpB2XTEoc_uA2Zaf1b8h00jlh0kOLZHJH7J7u4zMHZsDNUOLjs2Y-iBF-f6LhbZV63RTU7Jspyuxe4xZmKRAuHHCOdF9G4Y6p9ZiVsMgZrOX1hkEA0Z8RpzK8cJX81QVH4F0_TO7zfX2Wzx6cZAgux0nGXg3Lpa4wOOpF0PhwIXKQ6txcchPos68OUrtRlbCB-IZREZBe0la4iF2xvFfqo8lSnIo4Cfg84CadPvZ0JYUFm-q3gxGfE3lL60z6JryM6DhYFUQ5idCxA3trFRBF33bWyGGxkcqBXbaiQbtMGvZhFlWjmGXL1MrJ_2eEcBjVZbFqgxDMhnvZ5OrDV16wN6P5Zkbg4YW2fB2giuAGX_0H2KZRaCGR8CT66GvW7PvJGj6re58lgbf_ElERON5I9VRhFzj1HpZSCVuzjngHg",
                "https://ndisk.cizgifilmlerizle.com/getvid?evid=VW7yFllHvotlsJFQMT5L81ByDmUtqk0fgSaWeumZr0YOFYPGoK2QNi-Wqo3DEnzmpA2W1rrIGJJXh0WBUHlX6C74pKccxZPlZyld-B9YOhctrbVm1dGdZ9hE_7x34kskKq9EmWB-eGyz_VwJ2_6iikWr6z2JheUKEFRJbprIMNbsMnssVGoAft9xQo9_HF_diIstCDbTwWOTXMjU3W2RzDcbkAqqaMMzlGYlujk5mdqvc7_JgIZl4V08YyjNB5G92B0LXUpH9Sca00rgOaizKQ7ZJTgdw8ST7cbgbIFOwGg1wtSZSj40VBXjF6Jfm8vviC-zwFnqIelhd-PqGTzA9eIROpvm2nd_Nhnx0NPltCg-bd5C6M3bPqr0FyfBUMip2QP6zf0oU9yZJIOlnouEiSxjbgeiKY4wB5VzvbtsaPn3ZEIbrjvMxwqK3ciZqz5maqjOV0kkEov8UFGv2BC98A5SBFVQLXbzjdHiOFuQ4plWtTp7yXAI8GpB485O8Bs-WtEu84o50DlunvEVAAtA1Q"};
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
        webtoonId = 2; // Set the webtoon ID here
        cursor = db.query(
                WebtoonContract.CommentEntry.TABLE_NAME,
                null,
                WebtoonContract.CommentEntry.COLUMN_WEBTOON_ID + " =?",
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
            ((EditText) findViewById(R.id.comment_username)).setText("");
            ((EditText) findViewById(R.id.comment_text)).setText("");
            cursor.requery();
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
        public static final String DATABASE_NAME = "webtoons2.db";
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