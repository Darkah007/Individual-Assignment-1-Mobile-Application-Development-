package com.example.appd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView aboutText = findViewById(R.id.about_text);
        aboutText.setText("This is the About page of our Webtoon App. " +
                "We are a team of passionate developers who love creating engaging and entertaining content for our users.");
    }
}