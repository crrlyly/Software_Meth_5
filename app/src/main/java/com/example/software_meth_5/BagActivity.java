package com.example.software_meth_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.software_meth_5.rudonuts.Donut;
import com.example.software_meth_5.rudonuts.Flavor;
import com.example.software_meth_5.rudonuts.MenuItem;
import com.example.software_meth_5.rudonuts.Order;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.List;


public class BagActivity extends AppCompatActivity {

    private int selectedPosition = -1; // stored position
    private CustomAdapter adapter;
    private List<MenuItem> orderList;
    private final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bag);

        botNavListener();

        ListView listView = findViewById(R.id.itemListView);
        Button deleteBtn = findViewById(R.id.deleteItem);
        Button placeBtn = findViewById(R.id.placeBtn);

        orderList = ShareResource.getInstance().getCurrentOrder();

        adapter = new CustomAdapter(
                this,
                orderList
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPosition = position;
            adapter.setSelectedPosition(position);
            adapter.notifyDataSetChanged();
        });

        deleteBtn.setOnClickListener(v -> deleteSelectedItem());

        placeBtn.setOnClickListener(v -> placeOrder());

        displayPrice();
    }

    private void botNavListener(){
        ImageView menuIcon = findViewById(R.id.menuIcon);
        ImageView orderIcon = findViewById(R.id.orderIcon);

        menuIcon.setOnClickListener(v -> {
            Intent intent = new Intent(BagActivity.this, MainActivity.class);
            startActivity(intent);
        });

        orderIcon.setOnClickListener(v -> {
            Intent intent = new Intent(BagActivity.this, OrderActivity.class);
            startActivity(intent);
        });
    }

    private void displayPrice(){
        TextView sales = findViewById(R.id.subTotalText);
        TextView tax = findViewById(R.id.taxText);
        TextView total = findViewById(R.id.totalText);

        double subTotal = 0;
        for(MenuItem item: ShareResource.getInstance().getCurrentOrder()){
            subTotal = subTotal + item.price();
        }
        double salesTax = subTotal * 0.06625;
        double totalAmount = salesTax + subTotal;
        sales.setText("$" + df.format(subTotal));
        tax.setText("$" + df.format(salesTax));
        total.setText("$" + df.format(totalAmount));
    }

    private void deleteSelectedItem() {
        if (selectedPosition != -1) {
            orderList.remove(selectedPosition);
            adapter.notifyDataSetChanged();
            selectedPosition = -1;
            new AlertDialog.Builder(BagActivity.this)
                    .setTitle("Item Status")
                    .setMessage("Item has been successfully deleted.")
                    .show();
            displayPrice();
        } else {
            new AlertDialog.Builder(BagActivity.this)
                    .setTitle("Item Status")
                    .setMessage("No selected item. Please select one and try again.")
                    .show();
        }
    }

    private void placeOrder(){
        if(orderList.isEmpty()){
            new AlertDialog.Builder(BagActivity.this)
                    .setTitle("List Status")
                    .setMessage("No selected items in bag. Please add some items and try again.")
                    .show();
            return;
        }

        ShareResource.getInstance().setOrderNumber(
                ShareResource.getInstance().getOrderNumber() + 1
        );
        Order order = new Order(ShareResource.getInstance().getOrderNumber(), orderList);
        ShareResource.getInstance().getAllOrders().add(order);
        ShareResource.getInstance().getCurrentOrder().clear();
        Toast.makeText(getApplicationContext(),
                "Successfully placed your order.",
                Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
        displayPrice();
    }
}
