package com.example.autocare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
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

public class Fill_Up_Update extends AppCompatActivity {

    EditText date_input,quantity_input, price_input,meter_input;
    Button update_button,delete_button;

    DatePickerDialog.OnDateSetListener setListener;

    String id,date,qty,price,meter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_up_update);

        ImageView backIcon = findViewById(R.id.back_btn);
        TextView title = findViewById(R.id.toolbar_app_name);

        date_input = findViewById(R.id.et_edit_date);
        quantity_input = findViewById(R.id.et_edit_quentity);
        price_input = findViewById(R.id.et_edit_price);
        meter_input = findViewById(R.id.et_edit_meter);
        update_button = findViewById(R.id.btn_update_entry);
        delete_button = findViewById(R.id.btn_delete_entry);

        //first we call this
        Intent intent = getIntent();
        String vehicle_model = intent.getStringExtra("vehicle");
        Log.d("vehicleModel",vehicle_model);




        getAndSetIntentData();





        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        date_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Fill_Up_Update.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
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
                date_input.setText(Date);
            }
        };









        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FillUpsDatabaseHelper myDB = new FillUpsDatabaseHelper(Fill_Up_Update.this);
                //and only then we call this
                myDB.updateData(id,date_input.getText().toString().trim(),quantity_input.getText().toString().trim(),
                        price_input.getText().toString().trim(),meter_input.getText().toString().trim());
                Intent intent = new Intent(getApplicationContext(), Fill_Ups.class);
                intent.putExtra("selectItem", vehicle_model );
                startActivity(intent);
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });



        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Up_Update.this,"You clicked back button", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Fill_Up_Update.this,Fill_Ups.class);
                Intent intent = new Intent(getApplicationContext(), Fill_Ups.class);
                intent.putExtra("selectItem", vehicle_model );
                startActivity(intent);
            }
        });

        title.setText("Edit Fill-Up ");
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("date") &&
                getIntent().hasExtra("qty") && getIntent().hasExtra("price") && getIntent().hasExtra("meter")){
            //getting data from Intent
            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            qty = getIntent().getStringExtra("qty");
            price = getIntent().getStringExtra("price");
            meter = getIntent().getStringExtra("meter");

            //setting Intent Data
            date_input.setText(date);
            quantity_input.setText(qty);
            price_input.setText(price);
            meter_input.setText(meter);
        }
        else{
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + date + " ?");
        builder.setMessage("Are you sure you want to delete "+ date + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {

                FillUpsDatabaseHelper myDB = new FillUpsDatabaseHelper(Fill_Up_Update.this);
                myDB.deleteOneRow(id);
                finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}