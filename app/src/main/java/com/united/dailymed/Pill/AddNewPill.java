package com.united.dailymed.Pill;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.united.dailymed.Model.PillModel;
import com.united.dailymed.R;
import com.united.dailymed.Utils.DatabaseHandler;

public class AddNewPill extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";

    private EditText newPillText;
    private EditText newPillTime;
    private Button newPillSaveButton;
    private DatabaseHandler db;


    public static AddNewPill newInstance(){
        return new AddNewPill();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.DialogStyle);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_adding_pills, container,false);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        newPillText = getView().findViewById(R.id.newPillText);
        newPillTime = getView().findViewById(R.id.newPillTime);
        newPillSaveButton = getView().findViewById(R.id.newPillButton);

        db = new DatabaseHandler(getActivity());
        db.openDatabase();

        boolean isUpdate = false;
        final Bundle bundle = getArguments();
        if (bundle !=null){
            isUpdate = true;
            String pill = bundle.getString("pill");
            String pilltime = bundle.getString("pilltime");
            newPillText.setText(pill);
            newPillTime.setText(pilltime);
            if (pill.length()>0)
                newPillSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.btnColor));
        }
        newPillText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    newPillSaveButton.setEnabled(false);
                    newPillSaveButton.setTextColor(Color.GRAY);
                }
                else{
                    newPillSaveButton.setEnabled(true);
                    newPillSaveButton.setTextColor(ContextCompat.getColor(getContext(),R.color.btnColor));

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        final boolean finalIsUpdate = isUpdate;
        newPillSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = newPillText.getText().toString();
                String text1 = newPillTime.getText().toString();

                if (finalIsUpdate){
                    db.updatePill(bundle.getInt("id"), text);
                    db.updatePillTime(bundle.getInt("id"), text1);
                }
                else{
                    PillModel pill = new PillModel();
                    pill.setPill(text);
                    pill.setPillTime(text1);
                    pill.setStatus(0);
                    db.insertPill(pill);

                }
                dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog){
        Activity activity = getActivity();
        if(activity instanceof DialogCloseListener)
            ((DialogCloseListener)activity).handleDialogClose(dialog);

    }


}
