package com.example.software_meth_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.software_meth_5.rudonuts.MenuItem;
import com.example.software_meth_5.rudonuts.Order;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Activity class for the order page.
 *
 * @author Carly Chick
 */
public class OrderActivity extends AppCompatActivity {

    private ArrayAdapter<MenuItem> adapter;
    private List<MenuItem> orderList;
    private final DecimalFormat df = new DecimalFormat("0.00");
    private int SELECTED_ORDER_INDEX = -1;

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
        setContentView(R.layout.activity_order);

        botNavListener();

        ListView listView = findViewById(R.id.orderListView);
        Button deleteBtn = findViewById(R.id.deleteOrder);

        orderList = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                orderList
        );

        listView.setAdapter(adapter);

        initSpinner();

        deleteBtn.setOnClickListener(v -> deleteOrder());
    }

    /**
     * Allows user to switch pages using bottom nav.
     */
    private void botNavListener(){
        ImageView menuIcon = findViewById(R.id.menuIcon);
        ImageView bagIcon = findViewById(R.id.bagIcon);

        menuIcon.setOnClickListener(v -> {
            Intent intent = new Intent(OrderActivity.this, MainActivity.class);
            startActivity(intent);
        });

        bagIcon.setOnClickListener(v -> {
            Intent intent = new Intent(OrderActivity.this, BagActivity.class);
            startActivity(intent);
        });

    }

    /**
     * Initializes spinner for items.
     */
    private void initSpinner(){
        Spinner spinner = findViewById(R.id.orderNumSpinner);
        ArrayList<Integer> orderNumbers = new ArrayList<>();

        for(int i = 0; i < ShareResource.getInstance().getAllOrders().size(); i++){
            orderNumbers.add(ShareResource.getInstance().getAllOrders().get(i).getOrderNumber());
        }
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                orderNumbers
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SELECTED_ORDER_INDEX = position;
                orderList.clear();
                orderList.addAll(
                        ShareResource.getInstance()
                                .getAllOrders()
                                .get(SELECTED_ORDER_INDEX)
                                .getListOfItems()
                );
                adapter.notifyDataSetChanged();
                displayPrice();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Displays the price.
     */
    private void displayPrice(){
        TextView price = findViewById(R.id.orderPrice);
        double subTotal = 0;
        for(MenuItem item: ShareResource.getInstance()
                .getAllOrders().get(SELECTED_ORDER_INDEX).getListOfItems()){
            subTotal = subTotal + item.price();
        }
        double salesTax = subTotal * 0.06625;
        double total = salesTax + subTotal;
        price.setText(getString(R.string.money, df.format(total)));
    }

    /**
     * Deletes the order.
     */
    private void deleteOrder(){
        Spinner spinner = findViewById(R.id.orderNumSpinner);
        Integer selected = (Integer) spinner.getSelectedItem();

        if (selected == null) {
            Toast.makeText(this, "Please select an order first.", Toast.LENGTH_SHORT).show();
            return;
        }
        List<Order> allOrders = ShareResource.getInstance().getAllOrders();
        Iterator<Order> iterator = allOrders.iterator();
        while (iterator.hasNext()) {
            Order o = iterator.next();
            if (o.getOrderNumber() == selected) {
                iterator.remove();
                break;
            }
        }
        orderList.clear();
        adapter.notifyDataSetChanged();
        spinner.setSelection(-1);
        new AlertDialog.Builder(this)
                .setTitle("Order Deleted")
                .setMessage("Order #" + selected + " was removed successfully.")
                .setPositiveButton("OK", null)
                .show();
        TextView price = findViewById(R.id.orderPrice);
        price.setText(getString(R.string.base_price));
        refreshSpinner();
    }

    /**
     * Refreshes spinner after deleted order.
     */
    private void refreshSpinner() {
        Spinner spinner = findViewById(R.id.orderNumSpinner);

        ArrayList<Integer> orderNumbers = new ArrayList<>();
        for (Order o : ShareResource.getInstance().getAllOrders()) {
            orderNumbers.add(o.getOrderNumber());
        }
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                orderNumbers
        );
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(-1);
    }
}
