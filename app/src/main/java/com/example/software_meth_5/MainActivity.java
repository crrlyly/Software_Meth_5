package com.example.software_meth_5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Activity class for the main page.
 *
 * @author Carly Chick
 */
public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        ImageButton donutBtn = findViewById(R.id.donutBtn);
        ImageButton coffeeBtn = findViewById(R.id.coffeeBtn);
        ImageButton sandwichBtn = findViewById(R.id.sandwichBtn);

        donutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DonutActivity.class);
            startActivity(intent);
        });
        coffeeBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
            startActivity(intent);
        });
        sandwichBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SandwichActivity.class);
            startActivity(intent);
        });
        botNavListener();
    }

    /**
     * Allows user to switch pages using bottom nav.
     */
    private void botNavListener(){
        ImageView bagIcon = findViewById(R.id.bagIcon);
        ImageView orderIcon = findViewById(R.id.orderIcon);

        bagIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BagActivity.class);
            startActivity(intent);
        });
        orderIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OrderActivity.class);
            startActivity(intent);
        });
    }
}