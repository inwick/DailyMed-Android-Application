package com.united.dailymed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Pill extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill);

        //Initialize And Assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.Pill);

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
                        startActivity(new Intent(getApplicationContext(),Fitness.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.Pill:
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