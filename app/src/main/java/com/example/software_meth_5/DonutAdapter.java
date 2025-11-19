package com.example.software_meth_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.software_meth_5.rudonuts.Flavor;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutViewHolder> {

    private final Flavor[] donuts;
    private final Context context;

    public DonutAdapter(Context context, Flavor[] donuts) {
        this.context = context;
        this.donuts = donuts;
    }

    @NonNull
    @Override
    public DonutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donut, parent, false);
        return new DonutViewHolder(view);
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

    static class DonutViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameText;

        DonutViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.donutImage);
            nameText = itemView.findViewById(R.id.donutName);
        }
    }
}
