package com.example.software_meth_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.software_meth_5.rudonuts.Flavor;

public class BagActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bag);

        botNavListener();
    }

    private void botNavListener(){
        ImageView menuIcon = findViewById(R.id.menuIcon);
        ImageView bagIcon = findViewById(R.id.bagIcon);
        ImageView orderIcon = findViewById(R.id.orderIcon);

        menuIcon.setOnClickListener(v -> {
            Intent intent = new Intent(BagActivity.this, MainActivity.class);
            startActivity(intent);
        });

        bagIcon.setOnClickListener(v -> {
            Intent intent = new Intent(BagActivity.this, BagActivity.class);
            startActivity(intent);
        });

        orderIcon.setOnClickListener(v -> {
            Intent intent = new Intent(BagActivity.this, OrderActivity.class);
            startActivity(intent);
        });
    }
}
