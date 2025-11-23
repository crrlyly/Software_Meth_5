package com.example.software_meth_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.software_meth_5.rudonuts.Flavor;

public class DonutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_donut);

        backBtnHandler();

        RecyclerView recyclerView = findViewById(R.id.donutRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DonutAdapter adapter = new DonutAdapter(this, Flavor.values());
        recyclerView.setAdapter(adapter);

    }

    private void backBtnHandler(){
        ImageButton backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(DonutActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}