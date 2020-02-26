package com.example.login;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminEventActivity extends AppCompatActivity  {
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    Button createButton;
    Button deleteButton;



    private DatabaseReference mDatabaseRef;
    private List<Event> mEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_event);

        createButton = findViewById(R.id.create_event);
        deleteButton = findViewById(R.id.delete_event);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminEventActivity.this, CreateEventActivity.class);

                startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);


        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mEvent = new ArrayList<>();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("EventData");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Event event = postSnapshot.getValue(Event.class);
                    mEvent.add(event);
                }

                mAdapter = new MyAdapter(AdminEventActivity.this, mEvent);

                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(AdminEventActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}