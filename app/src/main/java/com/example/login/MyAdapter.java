package com.example.login;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Event> mEvents;

    public MyAdapter(Context context, List<Event> event) {
        mContext = context;
        mEvents = event;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        Event uploadCurrent = mEvents.get(position);
        holder.textViewTitle.setText(uploadCurrent.getTitle());
        holder.textViewDescription.setText(uploadCurrent.getDescription());
        holder.textViewDate.setText(uploadCurrent.getDate());
        holder.textViewLocation.setText(uploadCurrent.getLocation());
        Picasso.get().load(uploadCurrent.getImage()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


        public ImageViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.rTitleTv);
            imageView = itemView.findViewById(R.id.rImageView);
            textViewDescription = itemView.findViewById(R.id.rDescriptionTv);
            textViewDate = itemView.findViewById(R.id.rDateTv);
            textViewLocation = itemView.findViewById(R.id.rLocationTv);

        }
    }
}