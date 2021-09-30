package com.united.dailymed.Heart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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

public class HeartAddEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_add_entry);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        createNotificationChannel();

        final Calendar myCalendar = Calendar.getInstance();

        EditText edittext = (EditText) findViewById(R.id.heart_add_date);
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
                new DatePickerDialog(HeartAddEntry.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        Intent intent = new Intent(this, HeartReport.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "daily_med_Heart")
                .setSmallIcon(R.drawable.dailymed_ic_notify)
                .setContentTitle("Daily Med")
                .setContentText("Heart-Rate saved. Click here to view the summary.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);;


        EditText HeartRateEntry = findViewById(R.id.heart_add_heartrate);
        Button addHeartEntry = findViewById(R.id.btn_add_heart_entry);
        HeartDBHandler dbHandler = new HeartDBHandler(HeartAddEntry.this);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        addHeartEntry.setOnClickListener(v -> {

            notificationManager.notify(100,builder.build());

            // below line is to get data from all edit text fields.
            String hRate = HeartRateEntry.getText().toString();
            String entryDate = edittext.getText().toString();


            // validating if the text fields are empty or not.
            if (hRate.isEmpty() || entryDate.isEmpty()) {
                Toast.makeText(HeartAddEntry.this, "Please enter all the data.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (Integer.parseInt(hRate) > 300){
                Toast.makeText(HeartAddEntry.this, "The maximum Heart-rate cannot be more than 300bpm.", Toast.LENGTH_SHORT).show();
                return;
            }

            // on below line we are calling a method to add new
            // heart rate to sqlite data and pass all our values to it.
            dbHandler.addNewHeartEntry(hRate, entryDate);

            // after adding the data we are displaying a toast message.
            Toast.makeText(HeartAddEntry.this, "Heart-Rate Saved.", Toast.LENGTH_SHORT).show();
            HeartRateEntry.setText("");
            edittext.setText("");
            Intent i = new Intent(HeartAddEntry.this, HeartHistory.class);
            startActivity(i);
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
            }

        });


    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "heart_rate_notify";
            String description = "heart service notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("daily_med_Heart", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}

