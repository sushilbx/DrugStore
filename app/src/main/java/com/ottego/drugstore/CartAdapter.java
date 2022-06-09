package com.ottego.drugstore;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.ottego.drugstore.model.CartModel;
import com.ottego.drugstore.model.ProductModel;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    Context context;
    List<ProductModel> arrayCart;

    public CartAdapter(Context context, List<ProductModel> arrayCart) {
        this.context = context;
        this.arrayCart = arrayCart;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cart_row, parent, false);

        ViewHolder viewHolder = new CartAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductModel model = arrayCart.get(position);

        holder.tvCartProductName.setText(model.name);
        holder.tvCartProductId.setText(model.id);
        holder.tvCartProductPrice.setText(model.price);
        holder.tvCartProductQuantity.setText(String.valueOf(model.quantity));
        holder.mbCartRowRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 RemoveCartFragment.newInstance(model.cart_item_id, "").show(
                        ((AppCompatActivity)context).getSupportFragmentManager(), "remove_cart_fragment");
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayCart.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvCartProductName, tvCartProductId, tvCartProductPrice, tvCartProductQuantity;
        MaterialButton mbCartRowRemove;


        public ViewHolder(View itemView) {

            super(itemView);
            tvCartProductName = itemView.findViewById(R.id.tvCartProductName);
            tvCartProductId = itemView.findViewById(R.id.tvCartProductId);
            tvCartProductPrice = itemView.findViewById(R.id.tvCartProductPrice);
            tvCartProductQuantity = itemView.findViewById(R.id.tvCartProductQuantity);
            mbCartRowRemove = itemView.findViewById(R.id.mbCartRowRemove);
        }

    }
}
