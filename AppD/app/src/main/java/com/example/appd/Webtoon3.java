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

public class Webtoon3 extends AppCompatActivity {

    Cursor cursor;
    ListView commentsListView;
    SimpleCursorAdapter commentsAdapter;
    long webtoonId;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtoon3);

        commentsListView = findViewById(R.id.comments_list3);
        webView = findViewById(R.id.webview3);

        Spinner episodeSpinner = findViewById(R.id.episode_spinner3);
        String[] episodeTitles = {"Episode 1", "Episode 2", "Episode 3"};
        String[] episodeUrls = {"https://ndisk.cizgifilmlerizle.com/getvid?evid=28uKijx3UqCZTY2396yzaiKm8Z7VBMxSPW_HR245ifMtG84u9OuS8WXJJ65tg3O9KhCGkhR9U1wwylvGjMFRpIaGfG50PKDHNktogSje5Vv5Zlx4fxyLVzxD8E7776z1YYThOam914DGCw4K3iWElIn-yE9G9PQYe9ZgQyK9OyT0LKmPL9-Uxoq4VHAruXFmmzeuG2CYTqP1jcpERpM0k_7Zo-3K5OA1GGNiwhXtjaz45UZDdIUViv_rbipAUeWcLo0AMnABTd9GukI1ntGS8C7iNjYYaNFyyjfCHEXgBx23sOJrxTFSiBgsCW2DcmOmivGUYy-KwnecbEnnL9r1KWJqxTy0xBu9LoMkTJO4SFmyDs72SRbulRuuVAar6NFWQgRBLD9UuFOQXT7c9mOIuMyx940_u9oVqc8459pNmdYZhr_GNwVX6Ssz_W-C2bXQ_6BF-YchnaJeIZfTOY_Gcy93zzn2AD3Dr5QMHlpdCjjUj1xmSo86zxe9VvElajXoNGuGF3Vzii5M5iSA7t7ilyhvc5NswdcyPYUITo1Edzs",
                "https://ndisk.cizgifilmlerizle.com/getvid?evid=28uKijx3UqCZTY2396yzaiKm8Z7VBMxSPW_HR245ifMtG84u9OuS8WXJJ65tg3O9KhCGkhR9U1wwylvGjMFRpErHcPE3PPBCkFtuKIi48NEfXDkeoubXqfCBHO4RwBh5-qNOLfkd45TCyX05MKFJG8rhHaJGN7ppbMlfdh6KehVRDGWLcKEEA6o5UEoCYwbEPgK31yYX1jrhRV4rC4x7ERpXExaik-pFikUYN9V-5JXvUSwkvLDrsdFFQ9qawhPv6TRGP7O870CbuB0z5IEHHfT9ApijErVjBSBPwFspTxLel0jjX4dfLomRc-AvQqCDMjf0CY-A90kr__TCCbO5zZk3IPNrPS1_mgiCihk2bRKuv4eQnzM3bqD4cBFdLpzMmOBDVKTrgFi4hnWTNUhj8HmbPr-IfJ9KAnM0T5khvWmpB_Y5FoLcdAH6-DbWGDhuZukGkTE8hns_YosKvvrVIHCpUu_FVZDpP23Xho-VGo5bSi-J1XACb1DNwIrsb3YPGWg2FOHmRpHUWH7ppwGHDWUR1dXgGKsGfqvO0pgCH07puP60kH8S1u8jjE7go1-o",
                "https://ndisk.cizgifilmlerizle.com/getvid?evid=28uKijx3UqCZTY2396yzaiKm8Z7VBMxSPW_HR245ifMtG84u9OuS8WXJJ65tg3O9KhCGkhR9U1wwylvGjMFRpHr-rphFY50nBuCJUvGRSI5D8YXKC1UdT-L3ovRKfTQbiqHKTUwlVuWx7TgBi_F6RVje2IDtI28Gd0Mqt8CcZqIkjbzdGZv1EhY9Ub5uxqIzQDTfmVk4qXh2YKxBMBJMjUcnU1djIuZCM34ZNClOCto6YFpfZnVVN2OZXbgVHVqbADXIW5xMJq_HaG_rk5Z6oS1qrPlUnyGYdA5BZFCwtVEfluhdPb3xDQQx0qXXgVF7DrA63Hf06ASYJLLDTx8_VowMy9i5rL43TPS6_jOf8brmV1alwXbt2Y9rTyCZ7DXydHA1RbNpTKASfSpsF8YvTiutEYDVdx-UtoNjuOSqAmJ3N2P7C4dkF1GXUjf4Hn7P8Mjcccrvt9Om346YT7oaZbz4bLVPDZJ8eRqwRHrYQBlhjR2KsBBVyDfD0eIfMS653bWaGLMJoeZkgDxF84UoFV-K68tURVW1ggclYZao2Mk"};
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

        webtoonId = 3; // Set the webtoon ID here
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
        public static final String DATABASE_NAME = "webtoons3.db";
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
