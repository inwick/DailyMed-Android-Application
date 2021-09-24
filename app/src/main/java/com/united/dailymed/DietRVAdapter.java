package com.united.dailymed;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;
import java.util.ArrayList;

public class DietRVAdapter extends RecyclerView.Adapter<DietRVAdapter.ViewHolder> {




    // variable for our array list and context
    private ArrayList<DietModel> DietModelArrayList;
    private Context context;

    // constructor
    public DietRVAdapter(ArrayList<DietModel> courseModalArrayList, Context context) {
        this.DietModelArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.diet_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.

        DietModel modal = DietModelArrayList.get(position);
        holder.id.setText(String.valueOf(modal.getId()));
        holder.date.setText(modal.getDate());
        holder.bfirst.setText(modal.getBfirst());
        holder.lunch.setText(modal.getLunch());
        holder.dinner.setText(modal.getDinner());



        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, EditDietPlan.class);

                // below we are passing all our values.
                i.putExtra("Id", String.valueOf(modal.getId()));
                i.putExtra("Date", modal.getDate());
                i.putExtra("Bfirst", modal.getBfirst());
                i.putExtra("Lunch", modal.getLunch());
                i.putExtra("Dinner", modal.getDinner());



                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return DietModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        // creating variables for our text views.
        private final TextView id,date,bfirst,lunch,dinner;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            id= itemView.findViewById(R.id.diet_display_id);
           date = itemView.findViewById(R.id.diet_display_date);
            bfirst= itemView.findViewById(R.id.diet_display_breakfast);
            lunch = itemView.findViewById(R.id.diet_display_lunch);
            dinner= itemView.findViewById(R.id.diet_display_dinner);

        }
    }


}
