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

import com.example.software_meth_5.rudonuts.AddIns;
import com.example.software_meth_5.rudonuts.Coffee;
import com.example.software_meth_5.rudonuts.CupSize;
import com.example.software_meth_5.rudonuts.MenuItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Activity class for the coffee page.
 *
 * @author Carly Chick
 */
public class CoffeeActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_coffee);
        backBtnHandler();
        initSpinner();
        addOrder();
        addToQuantity();
        removeFromQuantity();
        checkBoxChecker();
        displayPrice();
    }

    /**
     * Handler for the back button.
     */
    private void backBtnHandler(){
        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(CoffeeActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Initializes the cup size spinner.
     */
    private void initSpinner(){
        Spinner spinner = findViewById(R.id.spinnerCoffee);

        CupSize[] sizes = CupSize.values();

        ArrayAdapter<CupSize> adapter = new ArrayAdapter<>(
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
     * Adds to quantity for coffee.
     *
     */
    private void addToQuantity(){
        Button addBtn = findViewById(R.id.addQuantity);
        addBtn.setOnClickListener(view -> {
            TextView quantityText = findViewById(R.id.quantityCoffee);
            int quantity = Integer.parseInt(quantityText.getText().toString().trim());
            int newQuantity = quantity + 1;
            quantityText.setText(String.valueOf(newQuantity));
            displayPrice();
        });
    }

    /**
     * Decreases quantity for coffee.
     *
     */
    private void removeFromQuantity(){
        Button removeBtn = findViewById(R.id.removeQuantity);
        removeBtn.setOnClickListener(view -> {
            TextView quantityText = findViewById(R.id.quantityCoffee);
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
     * Adds coffee item to order.
     */
    private void addOrder() {
        Button addToOrderBtn = findViewById(R.id.addToOrderBtn);
        CheckBox[] checkGrp = {
                findViewById(R.id.creamBtn),
                findViewById(R.id.vanillaBtn),
                findViewById(R.id.milkBtn),
                findViewById(R.id.mochaBtn),
                findViewById(R.id.caramelBtn)
        };
        AddIns[] addInsValues = {
                AddIns.WHIPPEDCREAM,
                AddIns.VANILLA,
                AddIns.MILK,
                AddIns.MOCHA,
                AddIns.CARAMEL
        };
        addToOrderBtn.setOnClickListener(view -> {
            Spinner spinner = findViewById(R.id.spinnerCoffee);
            TextView quantityText = findViewById(R.id.quantityCoffee);
            CupSize selectedSize = (CupSize) spinner.getSelectedItem();
            int quantity = Integer.parseInt(quantityText.getText().toString().trim());
            ArrayList<AddIns> selectedAddIns = new ArrayList<>();
            for (int i = 0; i < checkGrp.length; i++) {
                if (checkGrp[i].isChecked()) {
                    selectedAddIns.add(addInsValues[i]);
                }
            }
            Coffee coffee = new Coffee(quantity, selectedSize, selectedAddIns);
            String message = "Add " + quantity + " x " + selectedSize + " to order?";
            for (int i = 0; i < ShareResource.getInstance().getCurrentOrder().size(); i++) {
                MenuItem item = ShareResource.getInstance().getCurrentOrder().get(i);
                if (item.equals(coffee)) {
                    showConfirmation(message, coffee, 0, item);
                    return;
                }
            }
            showConfirmation(message, coffee, 1, null);
        });
    }

    /**
     * Shows confirmation before adding coffee.
     * @param message confirmation message
     * @param coffee coffee to be added
     * @param state tells if need to add to quantity of existing item or make a new item in list
     * @param item the menu item to add to depending on state
     */
    private void showConfirmation(String message, Coffee coffee, int state, MenuItem item){
        new AlertDialog.Builder(CoffeeActivity.this)
                .setTitle("Confirm Order")
                .setMessage(message)
                .setPositiveButton("Yes", (dialog, which) -> {
                    if(state == 0){
                        item.setQuantity(item.getQuantity() + coffee.getQuantity());
                    }
                    if(state == 1){
                        ShareResource.getInstance().getCurrentOrder().add(coffee);
                    }
                    Toast.makeText(getApplicationContext(),
                            "Added: " + coffee.getQuantity() + " x " + coffee.getSize(),
                            Toast.LENGTH_SHORT).show();
                    clearInputs();
                })
                .setNegativeButton("No", null)
                .show();
    }

    /**
     * Displays price
     */
    private void displayPrice(){
        TextView priceText = findViewById(R.id.priceText);
        Spinner spinner = findViewById(R.id.spinnerCoffee);
        TextView quantityText = findViewById(R.id.quantityCoffee);
        int quantity = Integer.parseInt(quantityText.getText().toString().trim());

        CheckBox[] checkGrp = {
                findViewById(R.id.creamBtn),
                findViewById(R.id.vanillaBtn),
                findViewById(R.id.milkBtn),
                findViewById(R.id.mochaBtn),
                findViewById(R.id.caramelBtn)
        };

        AddIns[] addInsValues = {
                AddIns.WHIPPEDCREAM,
                AddIns.VANILLA,
                AddIns.MILK,
                AddIns.MOCHA,
                AddIns.CARAMEL
        };

        ArrayList<AddIns> selectedAddIns = new ArrayList<>();
        for (int i = 0; i < checkGrp.length; i++) {
            if (checkGrp[i].isChecked()) {
                selectedAddIns.add(addInsValues[i]);
            }
        }

        MenuItem coffee = new Coffee(quantity, (CupSize) spinner.getSelectedItem(), selectedAddIns);
        priceText.setText(getString(R.string.money, df.format(coffee.price())));
    }

    /**
     * Checks what items are selected in the addins checkboxes.
     */
    private void checkBoxChecker(){
        CheckBox[] checkGrp = {
                findViewById(R.id.creamBtn),
                findViewById(R.id.vanillaBtn),
                findViewById(R.id.milkBtn),
                findViewById(R.id.mochaBtn),
                findViewById(R.id.caramelBtn)
        };

        for (CheckBox box : checkGrp) {
            box.setOnCheckedChangeListener((buttonView, isChecked) -> displayPrice());
        }
    }

    /**
     * Reset coffee page to default.
     */
    private void clearInputs(){
        Spinner spinner = findViewById(R.id.spinnerCoffee);
        TextView quantityText = findViewById(R.id.quantityCoffee);

        CheckBox[] checkGrp = {
                findViewById(R.id.creamBtn),
                findViewById(R.id.vanillaBtn),
                findViewById(R.id.milkBtn),
                findViewById(R.id.mochaBtn),
                findViewById(R.id.caramelBtn)
        };

        for (CheckBox checkBox : checkGrp) {
            checkBox.setChecked(false);
        }
        spinner.setSelection(0);
        quantityText.setText("1");
        displayPrice();
    }
}


