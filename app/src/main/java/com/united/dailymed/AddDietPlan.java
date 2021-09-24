package com.united.dailymed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.united.dailymed.Heart.Heart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddDietPlan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diet_plan);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);



        final Calendar myCalendar = Calendar.getInstance();

        EditText edittext = (EditText) findViewById(R.id.diet_add_date);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {



            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };


        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddDietPlan.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        EditText Breakfast = findViewById(R.id.diet_add_breakfast);
        EditText Lunch = findViewById(R.id.diet_add_lunch);
        EditText Dinner = findViewById(R.id.diet_add_dinner);
        Button addHeartEntry = findViewById(R.id.diet_btn_add_diet_plan);
        DietDBHandler dbHandler = new DietDBHandler(AddDietPlan.this);

        addHeartEntry.setOnClickListener(v -> {


            // below line is to get data from all edit text fields.
            String entryDate = edittext.getText().toString();
            String breakfast = Breakfast.getText().toString();
            String lunch = Lunch.getText().toString();
            String dinner = Dinner.getText().toString();


            // validating if the text fields are empty or not.
            if (entryDate.isEmpty() || breakfast.isEmpty() || lunch.isEmpty() || dinner.isEmpty()) {
                Toast.makeText(AddDietPlan.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                return;
            }

            // on below line we are calling a method to add new
            // course to sqlite data and pass all our values to it.
            dbHandler.addDietPlan(entryDate, breakfast,lunch,dinner);

            // after adding the data we are displaying a toast message.
            Toast.makeText(AddDietPlan.this, "Heart-Rate Saved.", Toast.LENGTH_SHORT).show();
            Breakfast.setText("");
            Lunch.setText("");
            Dinner.setText("");
            edittext.setText("");
            Intent i = new Intent(AddDietPlan.this, AddDietPlan.class);
            startActivity(i);
        });


        //Set Heart Selected
        bottomNavigationView.setSelectedItemId(R.id.Heart);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
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