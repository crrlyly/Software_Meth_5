package com.example.software_meth_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.software_meth_5.rudonuts.MenuItem;
import com.example.software_meth_5.rudonuts.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity class for the bag page.
 *
 * @author Carly Chick
 */
public class BagActivity extends AppCompatActivity {

    private CustomAdapter adapter;
    private List<MenuItem> itemList;
    private final DecimalFormat df = new DecimalFormat("0.00");
    private int SELECTED_POSITION = -1;

    /**
     * Initialization function.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bag);

        botNavListener();

        ListView listView = findViewById(R.id.itemListView);
        Button deleteBtn = findViewById(R.id.deleteItem);
        Button placeBtn = findViewById(R.id.placeBtn);

        itemList = ShareResource.getInstance().getCurrentOrder();

        adapter = new CustomAdapter(
                this,
                itemList
        );
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            SELECTED_POSITION = position;
            adapter.setSelectedPosition(position);
            adapter.notifyDataSetChanged();
        });

        deleteBtn.setOnClickListener(v -> deleteSelectedItem());

        placeBtn.setOnClickListener(v -> placeOrder());

        displayPrice();
    }

    /**
     * Allows user to switch pages using bottom nav.
     */
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

    /**
     * Displays the price.
     */
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
        sales.setText(getString(R.string.money, df.format(subTotal)));
        tax.setText(getString(R.string.money, df.format(salesTax)));
        total.setText(getString(R.string.money, df.format(totalAmount)));
    }

    /**
     * Deletes the selected item in the current order.
     */
    private void deleteSelectedItem() {
        if (SELECTED_POSITION != -1) {
            itemList.remove(SELECTED_POSITION);
            adapter.notifyDataSetChanged();
            SELECTED_POSITION = -1;
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

    /**
     * Places the current order.
     */
    private void placeOrder() {
        if (itemList.isEmpty()) {
            new AlertDialog.Builder(BagActivity.this)
                    .setTitle("List Status")
                    .setMessage("No items in bag. Please add some items and try again.")
                    .show();
            return;
        }

        ShareResource.getInstance().setOrderNumber(
                ShareResource.getInstance().getOrderNumber() + 1
        );

        Order order = new Order(
                ShareResource.getInstance().getOrderNumber(),
                new ArrayList<>(ShareResource.getInstance().getCurrentOrder())
        );

        ShareResource.getInstance().getAllOrders().add(order);
        ShareResource.getInstance().getCurrentOrder().clear();

        Toast.makeText(getApplicationContext(),
                "Successfully placed your order.",
                Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
        displayPrice();
    }
}
