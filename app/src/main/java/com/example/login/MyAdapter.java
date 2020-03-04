package com.example.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import static com.firebase.ui.auth.AuthUI.TAG;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Event> mEvents;
    private DatabaseReference mDatabaseRef;
    private DataSnapshot dataSnapshot;

    private static final String TAG = "MyAdapter";

    public MyAdapter(Context context , List<Event> event) {
        mContext = context;
        mEvents = event;


    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent , int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row , parent , false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder , final int position) {
        final Event uploadCurrent = mEvents.get(position);
        holder.textViewTitle.setText(uploadCurrent.getTitle());
        holder.textViewDescription.setText(uploadCurrent.getDescription());
        holder.textViewDate.setText(uploadCurrent.getDate());
        holder.textViewLocation.setText(uploadCurrent.getLocation());
        Picasso.get().load(uploadCurrent.getImage()).into(holder.imageView);


        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(mContext , TicketSelectionActivity.class);
                intent.putExtra("image View",uploadCurrent.getTitle());

                mContext.startActivity(intent);


            }
        });

        holder.parentlayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {



                showAlertDialog(v);

                return false;
            }
        });


    }


    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;
        public ImageView imageView;
        public TextView textViewDescription;
        public TextView textViewLocation;
        public TextView textViewDate;
        public CardView parentlayout;


        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.rTitleTv);
            imageView = itemView.findViewById(R.id.rImageView);
            textViewDescription = itemView.findViewById(R.id.rDescriptionTv);
            textViewDate = itemView.findViewById(R.id.rDateTv);
            textViewLocation = itemView.findViewById(R.id.rLocationTv);
            parentlayout = itemView.findViewById(R.id.parent_layout);


        }


    }


    public void showAlertDialog(View v) {

        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Delete Event");
        alert.setMessage("Are you sure you want to delete this event?");
        alert.setPositiveButton("Yes" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog , int which) {

               // DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
              //  String key = getIntent().getStringExtra("key");
               // ref.child("EventData").child(key).removeValue();


                Toast.makeText(mContext, "deleted", Toast.LENGTH_LONG).show();
                Log.d(TAG,"Remove Query was called !!!!!!!!!!!!");



            }
        });
        alert.setNegativeButton("No" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog , int which) {
                Toast.makeText(mContext, "ok perfect", Toast.LENGTH_LONG).show();

            }
        });
        alert.create().show();

    }







}