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
import com.ottego.drugstore.model.HospitalModel;
import com.ottego.drugstore.model.ProductModel;

import java.util.List;

public class HospitalAdaptor extends RecyclerView.Adapter<HospitalAdaptor.ViewHolder> {

    Context context;
    List<HospitalModel> arrayHospital;

    public HospitalAdaptor(Context context, List<HospitalModel> arrayHospital) {
        this.context = context;
        this.arrayHospital = arrayHospital;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.hospital_row, parent, false);
        ViewHolder viewHolder = new HospitalAdaptor.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .load(arrayHospital.get(position).image)
                .into(holder.ivHospitalImage);

        holder.tvHospitalName.setText(arrayHospital.get(position).name);
        holder.tvHospitalMobile.setText(arrayHospital.get(position).mobile);
        holder.tvHospitalEmail.setText(arrayHospital.get(position).email);
        holder.tvHospitalAddress.setText(arrayHospital.get(position).address);

    }

    @Override
    public int getItemCount() {
        return arrayHospital.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvHospitalName, tvHospitalMobile,tvHospitalEmail,tvHospitalAddress;
        ImageView ivHospitalImage;


        public ViewHolder(View itemView) {

            super(itemView);

            ivHospitalImage = itemView.findViewById(R.id.ivHospitalImage);
            tvHospitalName = itemView.findViewById(R.id.tvHospitalName);
            tvHospitalMobile = itemView.findViewById(R.id.tvHospitalMobile);
            tvHospitalAddress = itemView.findViewById(R.id.tvHospitalAddress);
            tvHospitalEmail = itemView.findViewById(R.id.tvHospitalEmail);

        }
    }
}
