package com.united.dailymed;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class activity_heart_add_entry extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_add_entry);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        final Calendar myCalendar = Calendar.getInstance();

        EditText edittext= (EditText) findViewById(R.id.heart_add_date);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
            private void updateLabel(){
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };



        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(activity_heart_add_entry.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });







         EditText HeartRateEntry= findViewById(R.id.heart_add_heartrate);
       Button addHeartEntry = findViewById(R.id.btn_add_heart_entry);
        HeartDBHandler dbHandler= new HeartDBHandler(activity_heart_add_entry.this);


        addHeartEntry.setOnClickListener(v -> {

            // below line is to get data from all edit text fields.
            String hRate = HeartRateEntry.getText().toString();
            String entryDate = edittext.getText().toString();


            // validating if the text fields are empty or not.
            if (hRate.isEmpty() || entryDate.isEmpty()) {
                Toast.makeText(activity_heart_add_entry.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                return;
            }

            // on below line we are calling a method to add new
            // course to sqlite data and pass all our values to it.
            dbHandler.addNewHeartEntry(hRate, entryDate);

            // after adding the data we are displaying a toast message.
            Toast.makeText(activity_heart_add_entry.this, "Heart-Rate Saved..", Toast.LENGTH_SHORT).show();
            HeartRateEntry.setText("");
            edittext.setText("");
        });


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

