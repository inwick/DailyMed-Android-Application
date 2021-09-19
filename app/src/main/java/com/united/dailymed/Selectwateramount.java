package com.united.dailymed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;



public class Selectwateramount extends AppCompatActivity {

    ImageView imageCoffee,imageJuice,imageTea,imageWine,imageWater,imageCola,imageEnergyDrink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20198336_selectwater);


        //getting the id's of image views
        imageCoffee = findViewById(R.id.CupCoffee);
        imageWater = findViewById(R.id.CupWater);
        imageJuice = findViewById(R.id.CupJuice);
        imageTea = findViewById(R.id.CupTea);
        imageWine = findViewById(R.id.CupWine);
        imageCola = findViewById(R.id.CupCola);
        imageEnergyDrink = findViewById(R.id.CupEnergyDrink);



        //setting on click methods for image clicks

        imageCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this,amtCoffee.class);
                Toast.makeText(getApplicationContext(), "You have Selected Coffee", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageJuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this,amtJuice.class);
                Toast.makeText(getApplicationContext(),"You Selected Water",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this,amtWater.class);
                Toast.makeText(getApplicationContext(),"You Selected Water",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        imageTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this,amtTea.class);
                Toast.makeText(getApplicationContext(),"You Selected Water",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageCola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this,amtCola.class);
                Toast.makeText(getApplicationContext(),"You Selected Water",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageWine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this,amtWine.class);
                Toast.makeText(getApplicationContext(),"You Selected Water",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageEnergyDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this,amtEnergyDrink.class);
                Toast.makeText(getApplicationContext(),"You Selected Water",Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });








    }
}