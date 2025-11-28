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

/**
 * Adapter class for the donut recycler.
 *
 * @author Carly Chick
 */
public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutViewHolder> {

    private final Flavor[] donuts;
    private final Context context;
    private final OnDonutClickListener listener;
    private final int[] quantities;

    /**
     * Constructor initializes adapter with context, donut list, and listener for click actions.
     * @param context the current Activity context
     * @param donuts array of available donuts
     * @param listener callback interface for handling click events
     */
    public DonutAdapter(Context context, Flavor[] donuts, OnDonutClickListener listener) {
        this.context = context;
        this.donuts = donuts;
        this.listener = listener;
        this.quantities = new int[donuts.length];
    }

    /**
     * Creates a new ViewHolder when needed by the RecyclerView.
     *
     * @param parent   parent ViewGroup where the item will be added
     * @param viewType view type
     * @return a new DonutViewHolder instance
     */
    @NonNull
    @Override
    public DonutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_donut, parent, false);
        return new DonutViewHolder(view, listener, quantities);
    }

    /**
     * Binds donut data to the ViewHolder at a specific position.
     *
     * @param holder   the ViewHolder to update
     * @param position the position of the donut item in the list
     */
    @Override
    public void onBindViewHolder(@NonNull DonutViewHolder holder, int position) {
        Flavor donut = donuts[position];

        holder.nameText.setText(donut.getName());
        holder.quantityText.setText(String.valueOf(quantities[position]));

        int resId = context.getResources().getIdentifier(
                donut.getImg(),
                "drawable",
                context.getPackageName()
        );

        if (resId != 0) {
            holder.imageView.setImageResource(resId);
        }
    }

    /**
     * Returns the total number of donut options in the RecyclerView.
     *
     * @return total donut count
     */
    @Override
    public int getItemCount() {
        return donuts.length;
    }

    /**
     * Gets the current quantity for a donut at the position.
     *
     * @param position index of the donut in the list
     * @return current quantity of that donut
     */
    public int getQuantity(int position) {
        return quantities[position];
    }

    /**
     * Resets the quantity of a donut to zero and refreshes the UI.
     *
     * @param position index of the donut to reset
     */
    public void resetQuantity(int position) {
        quantities[position] = 0;
        notifyItemChanged(position);
    }

    /**
     * ViewHolder class that holds references to views for each donut item.
     * Handles button clicks for adding/removing quantities and adding donuts.
     */
    static class DonutViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameText;
        TextView quantityText;

        Button addQuantity;
        Button removeQuantity;
        Button addDonut;

        /**
         * Constructor links ui components to code and sets click listeners.
         *
         * @param itemView   the row layout view
         * @param listener   callback interface for handling click actions
         * @param quantities array that stores donut quantities
         */
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
                    if (listener != null) {
                        listener.onIncreaseQuantity(pos);   // 🔥 CALLS ACTIVITY
                    }
                }
            });

            removeQuantity.setOnClickListener(v -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION && quantities[pos] > 0) {
                    quantities[pos]--;
                    quantityText.setText(String.valueOf(quantities[pos]));
                    if (listener != null) {
                        listener.onDecreaseQuantity(pos);   // 🔥 CALLS ACTIVITY
                    }
                }
            });
        }
    }
}
