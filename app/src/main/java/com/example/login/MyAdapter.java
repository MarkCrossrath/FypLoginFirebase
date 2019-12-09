package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Event> event;

    public MyAdapter(Context c , ArrayList<Event> e)
    {
        context = c;
       event = e;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.row,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(event.get(position).getTitle());
        holder.description.setText(event.get(position).getDescription());
        holder.location.setText(event.get(position).getLocation());
        holder.date.setText(event.get(position).getDate());



        Picasso.get().load(event.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return event.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView title,description,location,date;
        ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            title =  itemView.findViewById(R.id.rTitleTv);
            description =  itemView.findViewById(R.id.rDescriptionTv);
            image =  itemView.findViewById(R.id.rImageView);
            location = itemView.findViewById(R.id.rLocationTv);
            date= itemView.findViewById(R.id.rDateTv);
        }

    }
}