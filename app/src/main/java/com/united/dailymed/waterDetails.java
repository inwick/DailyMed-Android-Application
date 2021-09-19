package com.united.dailymed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class waterDetails extends AppCompatActivity {

Button  btnwaterDetails;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_it20198336_water_details);










                btnwaterDetails = findViewById(R.id.btnStart);

        btnwaterDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(waterDetails.this,Waterdashboard.class);
                        Toast.makeText(getApplicationContext(), "Changes Applied", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                });




        }
    }
