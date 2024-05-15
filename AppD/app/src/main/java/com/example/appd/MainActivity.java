package com.example.appd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private EditText searchBar;
    private ImageButton searchButton;
    private TextView homeTextView, aboutTextView, contactTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.grid_view);
        searchBar = findViewById(R.id.search_bar);
        searchButton = findViewById(R.id.search_button);
        homeTextView = findViewById(R.id.home);
        aboutTextView = findViewById(R.id.about);
        contactTextView = findViewById(R.id.contact);

        // Set adapter for grid view
        gridView.setAdapter(new WebtoonAdapter(this));

        // Set on click listener for search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchBar.getText().toString();
                // Implement search logic here
                Toast.makeText(MainActivity.this, "Searching for " + query, Toast.LENGTH_SHORT).show();
            }
        });

        // Set on item click listener for grid view
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Implement logic to navigate to specific webtoon page
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(MainActivity.this, Webtoon1.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, Webtoon2.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this, Webtoon3.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, Webtoon4.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, Webtoon5.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, Webtoon6.class);
                        startActivity(intent);
                        break;
                    // Add more cases for additional webtoons
                }
            }
        });

        // Set onClickListeners for Home, About, and Contact TextViews
        homeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to MainActivity (Home)
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        aboutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to About activity
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }
        });

        contactTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Contact activity
                Intent intent = new Intent(MainActivity.this, Contact.class);
                startActivity(intent);
            }
        });
    }
}
