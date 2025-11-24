package com.example.software_meth_5;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.software_meth_5.rudonuts.MenuItem;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<MenuItem> {

    private final List<MenuItem> items;
    private int selectedPosition = -1;

    public CustomAdapter(Context context, List<MenuItem> items) {
        super(context, android.R.layout.simple_list_item_1, items);
        this.items = items;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }

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