package com.example.software_meth_5;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order);

        botNavListener();
    }

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
}
