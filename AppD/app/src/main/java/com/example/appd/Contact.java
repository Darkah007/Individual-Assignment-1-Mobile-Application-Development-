package com.example.appd;

// Contact.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        TextView contactDetails = findViewById(R.id.contact_details);
        contactDetails.setText("Contact Us:\n" +
                "Email: [example@example.com](mailto:example@example.com)\n" +
                "Phone: +1 123 456 7890\n" +
                "Address: 123 Main St, Anytown, USA 12345");
    }
}