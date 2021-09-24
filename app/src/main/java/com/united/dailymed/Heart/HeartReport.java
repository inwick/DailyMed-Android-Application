package com.united.dailymed.Heart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.united.dailymed.Fitness;
import com.united.dailymed.Home;
import com.united.dailymed.Pill;
import com.united.dailymed.R;
import com.united.dailymed.Utils.HeartDBHandler;
import com.united.dailymed.Water;

public class HeartReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_report);

        Button btn_tips = findViewById(R.id.heart_btn_report_tips);
        TextView textview = findViewById(R.id.heart_report_average_result);

        HeartDBHandler heartdb = new HeartDBHandler(HeartReport.this);
        int HeartTotal = heartdb.sumHeartRates();
        int noOfEntries = heartdb.noOfEntries();

        float Average = (float) HeartTotal / (float) noOfEntries;

        textview.setText(String.valueOf((double) Math.round(Average * 10d / 10d)));

        btn_tips.setOnClickListener(v -> {
            Intent i = new Intent(HeartReport.this, HeartTips.class);
            startActivity(i);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Heart Selected
        bottomNavigationView.setSelectedItemId(R.id.Heart);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.Heart:
                        startActivity(new Intent(getApplicationContext(), Heart.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Fitness:
                        startActivity(new Intent(getApplicationContext(), Fitness.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Pill:
                        startActivity(new Intent(getApplicationContext(), Pill.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.Water:
                        startActivity(new Intent(getApplicationContext(), Water.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }

        });
    }
}