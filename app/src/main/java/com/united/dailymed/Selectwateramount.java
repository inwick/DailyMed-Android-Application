package com.united.dailymed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.united.dailymed.Heart.Heart;


public class Selectwateramount extends AppCompatActivity {

    ImageView imageCoffee,imageJuice,imageTea,imageWine,imageWater,imageCola,imageEnergyDrink,imagecustom;
    Button btnChange,btnCancel;
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
        imagecustom = findViewById(R.id.cupCustom);





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Heart Selected
        bottomNavigationView.setSelectedItemId(R.id.Water);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.Heart:
                        startActivity(new Intent(getApplicationContext(), Heart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Fitness:
                        startActivity(new Intent(getApplicationContext(),Fitness.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Pill:
                        startActivity(new Intent(getApplicationContext(),Pill.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Water:
                        startActivity(new Intent(getApplicationContext(),Water.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }

        });


        //setting on click methods for image clicks

        imageCoffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this, amtCoffee.class);
                Toast.makeText(getApplicationContext(), "You have selected Coffee", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageJuice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this, amtJuice.class);
                Toast.makeText(getApplicationContext(), "You have selected Juice", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this, amtWater.class);
                Toast.makeText(getApplicationContext(), "You have selected Water", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        imageTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this, amtTea.class);
                Toast.makeText(getApplicationContext(), "You have selected Tea", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageCola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this, amtCola.class);
                Toast.makeText(getApplicationContext(), "You have selected Cola", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageWine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this, amtWine.class);
                Toast.makeText(getApplicationContext(), "You have selected Wine", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imageEnergyDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this, amtEnergyDrink.class);
                Toast.makeText(getApplicationContext(), "You have selected Energy drink", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        imagecustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Selectwateramount.this, amtEnergyDrink.class);
                Toast.makeText(getApplicationContext(), "You have selected Custom", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }
        public void change(View view) {
             btnChange = findViewById(R.id.btnChange);
            btnChange.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                    Intent i =  new Intent(Selectwateramount.this,waterDetails.class);
                    startActivity(i);
                }
            });
        }
        public void cancel(View view) {
             btnCancel = findViewById(R.id.btnCancel);
            btnCancel.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                    Intent i = new Intent(Selectwateramount.this,Waterdashboard.class);
                    startActivity(i);
                }
            });
        }




    }
