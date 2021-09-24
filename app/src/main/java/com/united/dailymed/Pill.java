package com.united.dailymed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.united.dailymed.Adapter.PillAdapter;
import com.united.dailymed.Model.PillModel;
import com.united.dailymed.Utils.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Pill extends AppCompatActivity implements DialogCloseListener{

    //define RecyclerView
    private RecyclerView pillsRecyclerView;
    private PillAdapter pAdapter;
    private FloatingActionButton fab;

    private List<PillModel> pillList;
    private DatabaseHandler db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_pills);


        //Initialize And Assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //Set Pill Selected
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


        //data retrieving

        db = new DatabaseHandler(this);
        db.openDatabase();

        pillList = new ArrayList<>();

        pillsRecyclerView = findViewById(R.id.pillsRecyclerView);
        pillsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pAdapter = new PillAdapter(db,this);
        pillsRecyclerView.setAdapter(pAdapter);

        fab = findViewById(R.id.fab);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(pAdapter));
        itemTouchHelper.attachToRecyclerView(pillsRecyclerView);

        pillList = db.getAllPills();
        //add new pill to top
        Collections.reverse(pillList);
        pAdapter.setPills(pillList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewPill.newInstance().show(getSupportFragmentManager(), AddNewPill.TAG);

            }
        });

    }

    @Override
    public void handleDialogClose(DialogInterface dialog){
        pillList = db.getAllPills();
        //add new pill details to top
        Collections.reverse(pillList);
        pAdapter.setPills(pillList);
        pAdapter.notifyDataSetChanged();
    }

}