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

import com.ottego.drugstore.model.FilmModel;

import java.util.ArrayList;


public class FilmAdaptor extends RecyclerView.Adapter<FilmAdaptor.ViewHolder> {

    Context context;

    ArrayList<FilmModel>arrayContact;

    public FilmAdaptor(Context context, ArrayList<FilmModel> arrayContact) {
        this.context = context;
        this.arrayContact = arrayContact;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.film_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.contactImage.setImageResource(arrayContact.get(position).img);
        holder.filmCategory.setText(arrayContact.get(position).category);
        holder.filmName.setText(arrayContact.get(position).name);
//        holder.filmDesc.setText(arrayContact.get(position).desc);
        Glide.with(context)
//                .load("https://cdn0.iconfinder.com/data/icons/computer-process-outline/64/start_new_beginning_go-512.png")
                .load(arrayContact.get(position).imageUrl)
                .into(holder.filmImage);

    }

    @Override
    public int getItemCount() { return arrayContact.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView filmCategory, filmName,filmDesc;
        ImageView filmImage;


        public ViewHolder(View itemView) {

            super(itemView);

            filmName = itemView.findViewById(R.id.filmName);
            filmCategory = itemView.findViewById(R.id.filmCategory);
            filmImage = itemView.findViewById(R.id.filmImage);
            filmDesc = itemView.findViewById(R.id.filmDesc);

        }

    }
}

