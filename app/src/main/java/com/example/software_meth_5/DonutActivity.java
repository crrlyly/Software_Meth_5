package com.example.software_meth_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.software_meth_5.rudonuts.Coffee;
import com.example.software_meth_5.rudonuts.Donut;
import com.example.software_meth_5.rudonuts.Flavor;
import com.example.software_meth_5.rudonuts.MenuItem;

import java.text.DecimalFormat;

public class DonutActivity extends AppCompatActivity implements OnDonutClickListener{

    private DonutAdapter adapter;
    private Flavor[] donuts;

    private final DecimalFormat df = new DecimalFormat("0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donut);

        backBtnHandler();

        donuts = Flavor.values();

        RecyclerView recyclerView = findViewById(R.id.donutRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new DonutAdapter(this, donuts, this);
        recyclerView.setAdapter(adapter);
    }

    private void backBtnHandler(){
        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DonutActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onAddClick(int position) {
        Flavor flavor = donuts[position];            // get which donut
        int qty = adapter.getQuantity(position);    // get quantity from adapter

        if(qty == 0){
            //toast that says to please add a quantity
        }

        Donut donutItem = new Donut(qty, flavor.getType(), flavor);
        String message = "Add " + qty + " x " + flavor + " to order?";

        for (int i = 0; i < ShareResource.getInstance().getCurrentOrder().size(); i++) {
            MenuItem item = ShareResource.getInstance().getCurrentOrder().get(i);
            if (item.equals(donutItem)) {
                showConfirmation(message, donutItem, 0, item);
                adapter.resetQuantity(position);
                displayPrice();
                return;
            }
        }
        showConfirmation(message, donutItem, 1, null);
        adapter.resetQuantity(position);
        displayPrice();
    }

    private void showConfirmation(String message, Donut donut, int state, MenuItem item){
        new AlertDialog.Builder(DonutActivity.this)
                .setTitle("Confirm Order")
                .setMessage(message)
                .setPositiveButton("Yes", (dialog, which) -> {
                    if(state == 0){
                        item.setQuantity(item.getQuantity() + donut.getQuantity());
                    }
                    if(state == 1){
                        ShareResource.getInstance().getCurrentOrder().add(donut);
                    }
                    Toast.makeText(getApplicationContext(),
                            "Added: " + donut.getQuantity() + " x " + donut.getFlavor(),
                            Toast.LENGTH_SHORT).show();
//                    clearInputs();
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onIncreaseQuantity(int position) {
        displayPrice();
    }

    @Override
    public void onDecreaseQuantity(int position) {
        displayPrice();
    }

    private void displayPrice(){
        double price = 0;
        for (int i = 0; i < adapter.getItemCount(); i++) {
            int qty = adapter.getQuantity(i);
            if (qty == 0) continue;
            Flavor flavor = donuts[i];
            Donut donutItem = new Donut(qty, flavor.getType(), flavor);
            price = price + donutItem.price();
        }
        TextView donutPrice = findViewById(R.id.donutPrice);
        donutPrice.setText("$" + df.format(price));
    }
}