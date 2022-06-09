package com.ottego.drugstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ottego.drugstore.model.MovieModel;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;

    ArrayList<MovieModel> arrayContact;

    public MovieAdapter(Context context, ArrayList<MovieModel> arrayContact) {
        this.context = context;
        this.arrayContact = arrayContact;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.contactImage.setImageResource(arrayContact.get(position).img);
        holder.contactName.setText(arrayContact.get(position).name);
        holder.contactNumber.setText(arrayContact.get(position).category);
        Glide.with(context)
//                .load("https://cdn0.iconfinder.com/data/icons/computer-process-outline/64/start_new_beginning_go-512.png")
                .load(arrayContact.get(position).imageUrl)
                .into(holder.contactImage);

    }

    @Override
    public int getItemCount() {
        return arrayContact.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView contactName, contactNumber;
        ImageView contactImage;


        public ViewHolder(View itemView) {

            super(itemView);

            contactName = itemView.findViewById(R.id.contactName);
            contactNumber = itemView.findViewById(R.id.contactNumber);
            contactImage = itemView.findViewById(R.id.contactImage);

        }

    }
}
