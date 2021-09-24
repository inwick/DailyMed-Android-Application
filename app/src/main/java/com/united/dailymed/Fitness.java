package com.united.dailymed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fitness extends AppCompatActivity {

    Button btnaddDetails;
    Button  btnviewDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness);



            btnaddDetails = findViewById(R.id.adddietbutton);

            btnaddDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Fitness.this,AddDietPlan.class);
                    Toast.makeText(getApplicationContext(), "Changes Applied", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            });


        btnviewDetails = findViewById(R.id.viewdietplan);

        btnviewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Fitness.this,DietPlan.class);
                Toast.makeText(getApplicationContext(), "Changes Applied", Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });



        //Initialize And Assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.Fitness);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.Heart:
                        startActivity(new Intent(getApplicationContext(),Heart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Fitness:
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
    }
}


