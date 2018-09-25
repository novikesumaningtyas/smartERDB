package com.example.user.asignment2_1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegActivity extends AppCompatActivity {

    private Spinner nor, energyprovider;
    private Button reg, cancel;
    Context context;
    String[] totalRes = {"1","2","3","4","5"};
    String[] provider = {"AGL", "Origin Energy", "Schneider"};
    Calendar calendar;
    EditText dob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        nor = (Spinner)findViewById(R.id.residentsin);
        energyprovider = (Spinner)findViewById(R.id.energgyin);
        reg = (Button)findViewById(R.id.register);
        cancel = (Button)findViewById(R.id.cancel);
        dob = (EditText)findViewById(R.id.dobin);
        context = RegActivity.this;

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, totalRes);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nor.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item, provider);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        energyprovider.setAdapter(spinnerArrayAdapter2);

        getDate();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


    }

    public void getDate(){
        calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                String formatDate = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
                dob.setText(sdf.format(calendar.getTime()));

            }
        };

        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
}
