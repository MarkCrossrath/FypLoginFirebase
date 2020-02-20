package com.example.login;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdminEventActivity extends AppCompatActivity implements View.OnClickListener{

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Event> list;
    MyAdapter adapter;
    Button createButton;
    Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_event);

        // recyclerView =  findViewById(R.id.recyclerView);
        //recyclerView.setLayoutManager( new LinearLayoutManager(this));
        //recyclerView.setHasFixedSize(true);
        // recyclerView.setAdapter(adapter);
        createButton = findViewById(R.id.create_event);
        deleteButton = findViewById(R.id.delete_event);

        findViewById(R.id.create_event).setOnClickListener(this);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(adapter);


        reference = FirebaseDatabase.getInstance().getReference().child("EventData");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Event>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Event p = dataSnapshot1.getValue(Event.class);
                    list.add(p);
                }
                adapter = new MyAdapter(AdminEventActivity.this , list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AdminEventActivity.this , "Opsss.... Something is wrong" , Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.create_event:
              startActivity(new Intent(this,CreateEventActivity.class));

                break;
            //case R.id.the_pulse_button:
            //  startActivity(new Intent(this, PulseActivity.class));
            //    break;
            //  case R.id.resell_button:
            //   startActivity(new Intent(this, ResaleActivity.class));
            //     break;

        }

    }


}