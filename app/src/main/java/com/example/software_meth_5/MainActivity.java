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


public class MainActivity extends AppCompatActivity {

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

    private void botNavListener(){
        ImageView menuIcon = findViewById(R.id.menuIcon);
        ImageView bagIcon = findViewById(R.id.bagIcon);
        ImageView orderIcon = findViewById(R.id.orderIcon);

        menuIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        });

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