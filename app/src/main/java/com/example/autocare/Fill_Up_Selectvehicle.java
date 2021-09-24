package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Fill_Up_Selectvehicle extends AppCompatActivity {

    FillUpsDatabaseHelper myDB;
    ArrayList<String> fillUP_id,fillUP_model,fillUP_date,fillUp_quantity, fillUp_price, fillUP_meter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_up_selectvehicle);



        ImageView backIcon = findViewById(R.id.back_btn);
        TextView title = findViewById(R.id.toolbar_app_name);

        title.setText("Select Vehicle ");

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(Fill_Up_Selectvehicle.this,MainActivity.class);
                startActivity(back);
            }
        });

        myDB = new FillUpsDatabaseHelper(Fill_Up_Selectvehicle.this);
        fillUP_id = new ArrayList<>();
        fillUP_model = new ArrayList<>();
        fillUP_date = new ArrayList<>();
        fillUp_quantity = new ArrayList<>();
        fillUp_price = new ArrayList<>();
        fillUP_meter = new ArrayList<>();

        storeDataInArrays();


        Spinner dropdown = findViewById(R.id.spn_fill_up_select_vehicle);
        String[] items = new String[]{"Toyota", "Nissan", "Honda"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, fillUP_model);
        dropdown.setAdapter(adapter);

        Button next = findViewById(R.id.btn_fill_up_select);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Fill_Up_Selectvehicle.this,Fill_Ups.class);
                Intent intent = new Intent(getApplicationContext(), Fill_Ups.class);
                String select = dropdown.getSelectedItem().toString();
                Log.d("selectItem",select);
                intent.putExtra("selectItem", select );
                startActivity(intent);
            }
        });


    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){

        }
        else{
            while(cursor.moveToNext()){
                fillUP_id.add(cursor.getString(0));
                fillUP_model.add(cursor.getString(1));
                fillUP_date.add(cursor.getString(2));
                fillUp_quantity.add(cursor.getString(3));
                fillUp_price.add(cursor.getString(4));
                fillUP_meter.add(cursor.getString(5));

            }
        }
    }
}