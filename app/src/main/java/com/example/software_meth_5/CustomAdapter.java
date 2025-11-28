package com.example.software_meth_5;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.software_meth_5.rudonuts.MenuItem;

import java.util.List;

/**
 * Adapter class for a custom adapter for displaying menu items in a listview.
 *
 * @author Carly Chick
 */
public class CustomAdapter extends ArrayAdapter<MenuItem> {

    private final List<MenuItem> items;
    private int selectedPosition = -1;

    /**
     * Constructs a customadapter with the context and list of MenuItem objects.
     *
     * @param context the current Activity context
     * @param items   list of MenuItem objects to display
     */
    public CustomAdapter(Context context, List<MenuItem> items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.items = items;
    }

    /**
     * Sets which item is currently selected and refreshes the ListView.
     *
     * @param position the selected item position in the list
     */
    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

    /**
     * Returns the view for each row in the ListView and highlights the selected item.
     *
     * @param position     index of the current item
     * @param convertView  recycled view to reuse if available
     * @param parent       parent ViewGroup of this row
     * @return             a styled row view representing the MenuItem
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        if (position == selectedPosition) {
            view.setBackgroundColor(Color.parseColor("#9e9e9e"));
        } else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }
        return view;
    }
}