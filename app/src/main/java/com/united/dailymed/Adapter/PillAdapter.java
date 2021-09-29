package com.united.dailymed.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.united.dailymed.AddNewPill;
import com.united.dailymed.Pill;
import com.united.dailymed.Model.PillModel;
import com.united.dailymed.R;
import com.united.dailymed.Utils.DatabaseHandler;

import java.util.List;

public class PillAdapter extends RecyclerView.Adapter<PillAdapter.ViewHolder> {

    private List<PillModel> pillList;
    private Pill activity;
    private DatabaseHandler db;

    public PillAdapter(DatabaseHandler db, Pill activity){
        this.db = db;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_pill_added, parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder (ViewHolder holder, int position){
        db.openDatabase();

        PillModel item = pillList.get(position);
        holder.pill.setText(item.getPill());
        holder.TimeView.setText(item.getPillTime());
        holder.pill.setChecked(toBoolean(item.getStatus()));
        holder.pill.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    db.updateStatus(item.getId(),1);
                    holder.pill.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);

                }
                else{
                    db.updateStatus(item.getId(),0);
                    holder.pill.setPaintFlags(0);
                }
            }
        });
    }

    //helper class
    private boolean toBoolean(int n){
        return n != 0;
    }

    @Override
    public int getItemCount(){
        return pillList.size();
    }

    public Context getContext(){
        return activity;
    }

    public void setPills(List<PillModel>pillList){
        this.pillList = pillList;
        notifyDataSetChanged();
    }

    public void deleteItem(int position){
        PillModel item = pillList.get(position);
        db.deletePill(item.getId());
        pillList.remove(position);
        notifyItemRemoved(position);

    }

    public void editItem(int position){
        PillModel item = pillList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("pill", item.getPill());
        bundle.putString("pilltime",item.getPillTime());
        AddNewPill fragment = new AddNewPill();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewPill.TAG);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox pill;
        TextView TimeView; //v5.0

        ViewHolder(View view){
            super(view);
            pill = view.findViewById(R.id.pillCheckBox);
            TimeView = view.findViewById(R.id.TimeView); //v5.0

        }
    }
}
