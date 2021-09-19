package com.united.dailymed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Heart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_heart);

        final Button button_add_heart = (Button) findViewById(R.id.btn_heart_add);
        final Button button_add_history = (Button) findViewById(R.id.btn_heart_history);
        final Button button_add_tips = (Button) findViewById(R.id.btn_heart_tips);
        final Button button_add_summary = (Button) findViewById(R.id.btn_heart_summary);

        button_add_heart.setOnClickListener(view -> {

            Intent i = new Intent(getApplicationContext(), HeartAddEntry.class);
            startActivity(i);

        });
        button_add_history.setOnClickListener(view -> {

            Intent i = new Intent(getApplicationContext(), HeartHistory.class);
            startActivity(i);

        });
        button_add_tips.setOnClickListener(view -> {

            Intent i = new Intent(getApplicationContext(), HeartTips.class);
            startActivity(i);

        });
        button_add_summary.setOnClickListener(view -> {

            Intent i = new Intent(getApplicationContext(), HeartReport.class);
            startActivity(i);

        });

        //Initialize And Assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.Heart);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.Heart:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
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
    }
}