package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Fill_Up_Add extends AppCompatActivity {

    DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_up_add);

        ImageView backIcon = findViewById(R.id.back_btn);
        TextView title = findViewById(R.id.toolbar_app_name);


        EditText date = findViewById(R.id.et_set_fill_date);
        EditText quantity = findViewById(R.id.et_add_quentity);
        EditText price = findViewById(R.id.et_add_price);
        EditText odometer = findViewById(R.id.et_add_meter);
        Button addButton = findViewById(R.id.btn_add_entry);




        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                    Fill_Up_Add.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,month,day);
                    datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                 month = month+1;
                 String Date = dayOfMonth+"/"+month+"/"+year;
                 date.setText(Date);
            }
        };


        Intent intent = getIntent();
        String vehicle_model = intent.getStringExtra("vehicleModel");
        Log.d("vehicleModel",vehicle_model);

        TextView displayModel = findViewById(R.id.tv_fill_up_display);
        displayModel.setText(vehicle_model);



        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Up_Add.this,"You clicked back button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Fill_Ups.class);
                intent.putExtra("selectItem", vehicle_model );
                startActivity(intent);
            }
        });

        title.setText("Add Fill-Up");




        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FillUpsDatabaseHelper mydb = new FillUpsDatabaseHelper(Fill_Up_Add.this);
                mydb.addFillUp (vehicle_model,date.getText().toString().trim(),
                        Integer.valueOf(quantity.getText().toString().trim()),
                        Integer.valueOf(price.getText().toString().trim()),
                        Integer.valueOf(odometer.getText().toString().trim()));
                Intent intent = new Intent(getApplicationContext(), Fill_Ups.class);
                intent.putExtra("selectItem", vehicle_model );
                startActivity(intent);
            }
        });

    }
}