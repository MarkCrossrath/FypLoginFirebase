package com.example.login;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateEventActivity extends AppCompatActivity {

    EditText eventTitle;
    EditText eventDescription;
    EditText eventLocation;
    EditText eventDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event);

        eventTitle = findViewById(R.id.event_title);
        eventDescription = findViewById(R.id.event_description);
        eventLocation = findViewById(R.id.event_date);
        eventDate = findViewById(R.id.event_date);





    }
}
