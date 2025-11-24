package com.example.software_meth_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.software_meth_5.rudonuts.Flavor;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutViewHolder> {

    private final Flavor[] donuts;
    private final Context context;

    private final OnDonutClickListener listener;

    private final int[] quantities;

    public DonutAdapter(Context context, Flavor[] donuts, OnDonutClickListener listener) {
        this.context = context;
        this.donuts = donuts;
        this.listener = listener;
        this.quantities = new int[donuts.length];
    }

    @NonNull
    @Override
    public DonutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donut, parent, false);
        return new DonutViewHolder(view, listener, quantities);
    }

    @Override
    public void onBindViewHolder(@NonNull DonutViewHolder holder, int position) {
        Flavor donut = donuts[position];

        holder.nameText.setText(donut.getName());

        int resId = context.getResources().getIdentifier(
                donut.getImg(),
                "drawable",
                context.getPackageName()
        );

        if (resId != 0) {
            holder.imageView.setImageResource(resId);
        }
    }

    @Override
    public int getItemCount() {
        return donuts.length;
    }

    public int getQuantity(int position) {
        return quantities[position];
    }

    public void resetQuantity(int position) {
        quantities[position] = 0;
        notifyItemChanged(position);
    }

    static class DonutViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameText;
        TextView quantityText;

        Button addQuantity;

        Button removeQuantity;

        Button addDonut;
        DonutViewHolder(@NonNull View itemView, OnDonutClickListener listener, int[] quantities) {
            super(itemView);
            imageView = itemView.findViewById(R.id.donutImage);
            nameText = itemView.findViewById(R.id.donutName);
            quantityText = itemView.findViewById(R.id.quantitySandwichText);
            addQuantity = itemView.findViewById(R.id.addQuantity);
            removeQuantity = itemView.findViewById(R.id.removeQuantity);
            addDonut = itemView.findViewById(R.id.addDonut);

            addDonut.setOnClickListener(v -> {
                if (listener != null && getBindingAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onAddClick(getBindingAdapterPosition());
                }
            });

            addQuantity.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    quantities[pos]++;
                    quantityText.setText(String.valueOf(quantities[pos]));
                }
            });

            removeQuantity.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && quantities[pos] > 0) {
                    quantities[pos]--;
                    quantityText.setText(String.valueOf(quantities[pos]));
                }
            });
        }
    }
}
