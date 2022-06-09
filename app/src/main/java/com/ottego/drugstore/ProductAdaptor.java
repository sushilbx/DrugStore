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
import com.ottego.drugstore.model.ProductModel;
import com.ottego.drugstore.model.MovieModel;

import java.util.ArrayList;
import java.util.List;


public class ProductAdaptor extends RecyclerView.Adapter<ProductAdaptor.ViewHolder> {

    Context context;

    List<ProductModel> arrayProduct;

    public ProductAdaptor(Context context, List<ProductModel> arrayProduct) {
        this.context = context;
        this.arrayProduct = arrayProduct;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(arrayProduct.get(position).image)
                .into(holder.productImage);

        holder.productDiscription.setText(arrayProduct.get(position).description);
        holder.productName.setText(arrayProduct.get(position).name);
        holder.productPrice.setText(arrayProduct.get(position).price);

    }

    @Override
    public int getItemCount() {
        return arrayProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView productName, productDiscription,productPrice;
        ImageView productImage;


        public ViewHolder(View itemView) {

            super(itemView);

            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productDiscription = itemView.findViewById(R.id.productDiscription);
            productPrice = itemView.findViewById(R.id.productPrice);

        }

    }
}

