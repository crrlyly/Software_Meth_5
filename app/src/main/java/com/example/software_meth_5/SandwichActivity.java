package com.example.software_meth_5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.software_meth_5.rudonuts.AddOns;
import com.example.software_meth_5.rudonuts.Bread;
import com.example.software_meth_5.rudonuts.MenuItem;
import com.example.software_meth_5.rudonuts.Protein;
import com.example.software_meth_5.rudonuts.Sandwich;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Activity class for the sandwich page.
 *
 * @author Carly Chick
 */
public class SandwichActivity extends AppCompatActivity {

    private final DecimalFormat df = new DecimalFormat("0.00");

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
        setContentView(R.layout.activity_sandwich);
        backBtnHandler();
        initSpinnerMeat();
        initSpinnerBread();
        addToQuantity();
        removeFromQuantity();
        displayPrice();
        checkBoxChecker();
        addOrder();
    }

    /**
     * Handler for the back button.
     */
    private void backBtnHandler(){
        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SandwichActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Initializes the spinner for the protein.
     */
    private void initSpinnerMeat(){
        Spinner spinner = findViewById(R.id.spinnerMeat);
        Protein[] protein = Protein.values();
        ArrayAdapter<Protein> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                protein
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayPrice();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Initializes the spinner for the bread.
     */
    private void initSpinnerBread(){
        Spinner spinner = findViewById(R.id.spinnerBread);
        Bread[] sizes = Bread.values();
        ArrayAdapter<Bread> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                sizes
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                displayPrice();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    /**
     * Creates functionality for the add quantity button
     */
    private void addToQuantity(){
        Button addBtn = findViewById(R.id.addBtnSand);
        addBtn.setOnClickListener(view -> {
            TextView quantityText = findViewById(R.id.quantitySandwichText);
            int quantity = Integer.parseInt(quantityText.getText().toString().trim());
            int newQuantity = quantity + 1;
            quantityText.setText(String.valueOf(newQuantity));
            displayPrice();
        });
    }

    /**
     * Creates functionality for the remove quantity button
     */
    private void removeFromQuantity(){
        Button removeBtn = findViewById(R.id.removeBtnSand);
        removeBtn.setOnClickListener(view -> {
            TextView quantityText = findViewById(R.id.quantitySandwichText);
            int quantity = Integer.parseInt(quantityText.getText().toString().trim());
            if(quantity <= 1){
                return;
            }
            int newQuantity = quantity - 1;
            quantityText.setText(String.valueOf(newQuantity));
            displayPrice();
        });
    }

    /**
     * Adds the item to the order.
     */
    private void addOrder() {
        Button addToOrderBtn = findViewById(R.id.addToOrderBtn);
        CheckBox[] checkGrp = {
                findViewById(R.id.tomatoBtn),
                findViewById(R.id.lettuceBtn),
                findViewById(R.id.cheeseBtn),
                findViewById(R.id.onionBtn),
        };
        AddOns[] addOnsValues = {
                AddOns.TOMATO,
                AddOns.LETTUCE,
                AddOns.CHEESE,
                AddOns.ONION
        };
        addToOrderBtn.setOnClickListener(view -> {
            Spinner spinnerMeat = findViewById(R.id.spinnerMeat);
            Spinner spinnerBread = findViewById(R.id.spinnerBread);
            Protein selectedProtein = (Protein) spinnerMeat.getSelectedItem();
            Bread selectedBread = (Bread) spinnerBread.getSelectedItem();
            TextView quantityText = findViewById(R.id.quantitySandwichText);
            int quantity = Integer.parseInt(quantityText.getText().toString().trim());
            ArrayList<AddOns> selectedAddOns = new ArrayList<>();
            for (int i = 0; i < checkGrp.length; i++) {
                if (checkGrp[i].isChecked()) {
                    selectedAddOns.add(addOnsValues[i]);
                }
            }
            Sandwich sandwich = new Sandwich(quantity, selectedBread, selectedProtein, selectedAddOns);
            String message = "Add " + quantity + " x " + selectedProtein + " to order?";
            for (int i = 0; i < ShareResource.getInstance().getCurrentOrder().size(); i++) {
                MenuItem item = ShareResource.getInstance().getCurrentOrder().get(i);
                if (item.equals(sandwich)) {
                    showConfirmation(message, sandwich, 0, item);
                    return;
                }
            }
            showConfirmation(message, sandwich, 1, null);
        });
    }

    /**
     * Shows confirmation message
     * @param message confirmation message
     * @param sandwich sandwich adding to order
     * @param state tells if need to add to quantity of existing item or make a new item in list
     * @param item the menu item to add to depending on state
     */
    private void showConfirmation(String message, Sandwich sandwich, int state, MenuItem item){
        new AlertDialog.Builder(SandwichActivity.this)
                .setTitle("Confirm Order")
                .setMessage(message)
                .setPositiveButton("Yes", (dialog, which) -> {
                    if(state == 0){
                        item.setQuantity(item.getQuantity() + sandwich.getQuantity());
                    }
                    if(state == 1){
                        ShareResource.getInstance().getCurrentOrder().add(sandwich);
                    }
                    Toast.makeText(getApplicationContext(),
                            "Added: " + sandwich.getQuantity() + " x " + sandwich.getProtein(),
                            Toast.LENGTH_SHORT).show();
                    clearInputs();
                })
                .setNegativeButton("No", null)
                .show();
    }

    /**
     * Displays the price of sandwich.
     */
    private void displayPrice(){
        TextView priceText = findViewById(R.id.priceSandText);
        Spinner spinnerMeat = findViewById(R.id.spinnerMeat);
        Spinner spinnerBread = findViewById(R.id.spinnerBread);
        TextView quantityText = findViewById(R.id.quantitySandwichText);
        int quantity = Integer.parseInt(quantityText.getText().toString().trim());
        CheckBox[] checkGrp = {
                findViewById(R.id.tomatoBtn),
                findViewById(R.id.lettuceBtn),
                findViewById(R.id.cheeseBtn),
                findViewById(R.id.onionBtn),
        };
        AddOns[] addOnsValues = {
                AddOns.TOMATO,
                AddOns.LETTUCE,
                AddOns.CHEESE,
                AddOns.ONION
        };
        ArrayList<AddOns> selectedAddOns = new ArrayList<>();
        for (int i = 0; i < checkGrp.length; i++) {
            if (checkGrp[i].isChecked()) {
                selectedAddOns.add(addOnsValues[i]);
            }
        }
        MenuItem sandwich = new Sandwich(quantity, (Bread) spinnerBread.getSelectedItem(), (Protein) spinnerMeat.getSelectedItem(), selectedAddOns);
        priceText.setText("$" + df.format(sandwich.price()));
    }

    /**
     * Checks what items are selected in the addons checkboxes.
     */
    private void checkBoxChecker(){
        CheckBox[] checkGrp = {
                findViewById(R.id.tomatoBtn),
                findViewById(R.id.lettuceBtn),
                findViewById(R.id.cheeseBtn),
                findViewById(R.id.onionBtn),
        };
        for (CheckBox box : checkGrp) {
            box.setOnCheckedChangeListener((buttonView, isChecked) -> {
                displayPrice();
            });
        }
    }

    /**
     * Reset sandwich page to default.
     */
    private void clearInputs(){
        Spinner spinnerMeat = findViewById(R.id.spinnerMeat);
        Spinner spinnerBread = findViewById(R.id.spinnerBread);
        TextView quantityText = findViewById(R.id.quantitySandwichText);
        CheckBox[] checkGrp = {
                findViewById(R.id.tomatoBtn),
                findViewById(R.id.lettuceBtn),
                findViewById(R.id.cheeseBtn),
                findViewById(R.id.onionBtn),
        };
        for (CheckBox checkBox : checkGrp) {
            checkBox.setChecked(false);
        }
        spinnerMeat.setSelection(0);
        spinnerBread.setSelection(0);
        quantityText.setText("1");
        displayPrice();
    }
}
