package com.united.dailymed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Waterdashboard extends AppCompatActivity {


    Button btnAddNewDrink;
    Button btnChangeWaterSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20198336_waterdashboard);



        btnAddNewDrink = findViewById(R.id.btnAddDrink);
        btnChangeWaterSettings=findViewById(R.id.btnchangeSettings);

        btnAddNewDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Waterdashboard.this,Selectwateramount.class);
                Toast.makeText(getApplicationContext(), "Please select a New Drink", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        btnChangeWaterSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Waterdashboard.this,Changewatersettings.class);
                startActivity(i);
            }
        });
    }

    }
