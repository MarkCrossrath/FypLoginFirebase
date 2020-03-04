package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TicketSelectionActivity extends AppCompatActivity {


    ImageView eventImage;
    Button button1;
    Button button2;
    Button button3;
    TextView selectionTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_selection);

        Intent intent = getIntent();
        intent.getStringExtra("key");

        selectionTitle = findViewById(R.id.selection_title);
        eventImage = findViewById(R.id.event_Image);
        button1 = findViewById(R.id.ticket_one);
        button2 = findViewById(R.id.ticket_two);
        button3 = findViewById(R.id.ticket_three);



        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TicketSelectionActivity.this,TicketOneActivity.class);
                startActivity(intent);
            }
        });



    }
}
