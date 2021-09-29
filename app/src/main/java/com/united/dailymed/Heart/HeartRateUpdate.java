package com.united.dailymed.Heart;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.united.dailymed.Diet.Diet;
import com.united.dailymed.Home.Home;
import com.united.dailymed.Pill.Pill;
import com.united.dailymed.R;
import com.united.dailymed.Utils.HeartDBHandler;
import com.united.dailymed.Water.Water;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class HeartRateUpdate extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_update);

        // variables for our edit text, button, strings and dbhandler class.
        EditText heartRateEdt;
        TextView idEdt;
        EditText dateInput;
        Button updateEntryBtn;
        Button deleteEntryBtn;
        HeartDBHandler dbHandler;
        String rateFetched, dateFetched, id;

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Heart Selected
        bottomNavigationView.setSelectedItemId(R.id.Heart);

        //Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
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
                    startActivity(new Intent(getApplicationContext(), Diet.class));
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
        });

        // initializing all our variables.
        idEdt = findViewById(R.id.idEdtid);
        heartRateEdt = findViewById(R.id.idEdtCourseName);
        dateInput = findViewById(R.id.idEdtCourseDuration);
        updateEntryBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteEntryBtn = findViewById(R.id.idBtnDelete);

        // on below line we are initialing our dbhandler class.
        dbHandler = new HeartDBHandler(HeartRateUpdate.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        id = getIntent().getStringExtra("id");
        rateFetched = getIntent().getStringExtra("rate");
        dateFetched = getIntent().getStringExtra("date");

        // setting data to edit text
        // of our update activity.
        idEdt.setText(id);
        heartRateEdt.setText(rateFetched);
        dateInput.setText(dateFetched);

        final Calendar myCalendar = Calendar.getInstance();

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

                dateInput.setText(sdf.format(myCalendar.getTime()));
            }

        };

        dateInput.setOnClickListener(v -> new DatePickerDialog(HeartRateUpdate.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show());

        // adding on click listener to our update heart rate button.
        updateEntryBtn.setOnClickListener(v -> {

            if (heartRateEdt.getText().toString().isEmpty() || dateInput.getText().toString().isEmpty()) {
                Toast.makeText(HeartRateUpdate.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                return;
            }

            // inside this method we are calling an update course
            // method and passing all our edit text values.
            dbHandler.updateHeartRate(idEdt.getText().toString(), heartRateEdt.getText().toString(), dateInput.getText().toString());

            // displaying a toast message that our course has been updated.
            Toast.makeText(HeartRateUpdate.this, "Record Updated Successfully.", Toast.LENGTH_SHORT).show();

            // launching our main activity.
            Intent i = new Intent(HeartRateUpdate.this, HeartHistory.class);
            startActivity(i);
        });

        //calling a method to delete our course.
        deleteEntryBtn.setOnClickListener(this::showAlertDialog);
    }

    public void showAlertDialog(View v) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("DELETE CONFIRMATION");
        alert.setMessage("Are you sure to delete record?");
        alert.setPositiveButton("Yes", (dialog, which) -> {
            HeartDBHandler dbHandler = new HeartDBHandler(HeartRateUpdate.this);
            String id;
            id = getIntent().getStringExtra("id");
            dbHandler.deleteHeartEntry(id);
            Toast.makeText(HeartRateUpdate.this, "Record Deleted Successfully..", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(HeartRateUpdate.this, HeartHistory.class);
            startActivity(i);
        });
        alert.setNegativeButton("No", (dialog, which) -> {
            Intent i = new Intent(HeartRateUpdate.this, HeartHistory.class);
        });
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
}
