package com.example.autocare;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Toast;

import java.util.ArrayList;

public class Fill_Ups extends AppCompatActivity {

    FillUpsDatabaseHelper myDB;
    ArrayList<String> fillUP_id,fillUP_date,fillUp_quantity, fillUp_price, fillUP_meter;
    Fill_Up_CustomAdapter fill_up_customAdapter;
    ImageView emptyIcon;
    TextView tvnodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_ups);

        ImageView homeIcon = findViewById(R.id.home_btn);
        ImageView settingIcon = findViewById(R.id.setting_btn);
        TextView title = findViewById(R.id.toolbar_app_name);
        Button btn_add_fillUp = findViewById(R.id.btn_addFill_up);
        Button btn_summary = findViewById(R.id.btn_summery);

        emptyIcon = findViewById(R.id.img_fillup_empty);
        tvnodata = findViewById(R.id.tv_fillup_empty);


        RecyclerView recyclerView = findViewById(R.id.FillUp_recyclerView);


        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Ups.this,"You clicked home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Fill_Ups.this,MainActivity.class);
                startActivity(intent);
            }
        });
        settingIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Ups.this,"You clicked setting", Toast.LENGTH_SHORT).show();
            }
        });




        Intent intent = getIntent();
        String vehicle_model = intent.getStringExtra("selectItem");


        Button btn_Vehicle_Model = findViewById(R.id.btn_fillUp_display_model);
        btn_Vehicle_Model.setText(vehicle_model);

        btn_Vehicle_Model.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(Fill_Ups.this,Fill_Up_Selectvehicle.class);
                startActivity(picture_intent );
            }
        });





        btn_add_fillUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Ups.this,"You are going to add fill-up page", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Fill_Ups.this, Fill_Up_Add.class);
                Intent intent = new Intent(getApplicationContext(), Fill_Up_Add.class);
                intent.putExtra("vehicleModel", vehicle_model );

                startActivity(intent);
            }
        });

        title.setText("Fill-Ups");




        myDB = new FillUpsDatabaseHelper(Fill_Ups.this);
        fillUP_id = new ArrayList<>();
        fillUP_date = new ArrayList<>();
        fillUp_quantity = new ArrayList<>();
        fillUp_price = new ArrayList<>();
        fillUP_meter = new ArrayList<>();

        storeDataInArrays(vehicle_model);

        fill_up_customAdapter = new Fill_Up_CustomAdapter(Fill_Ups.this,this,fillUP_id,fillUP_date,fillUp_quantity,fillUp_price,fillUP_meter,vehicle_model);
        recyclerView.setAdapter(fill_up_customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Fill_Ups.this));




        int totalPrice = 0;
        int totalQuantity =0;
        int startMeter = 0;
        int endMeter = 0;


        for (int i = 0; i < fillUp_price.size(); i++){
            totalPrice = totalPrice + Integer.parseInt(fillUp_price.get(i));
        }
        for (int i = 0; i < fillUp_quantity.size(); i++){
            totalQuantity = totalQuantity + Integer.parseInt(fillUp_quantity.get(i));
        }
        for (int i = 0; i < fillUP_meter.size(); i++){
            if(i==0)
                startMeter = Integer.parseInt(fillUP_meter.get(i));
            else if (i==fillUP_meter.size()-1){
                endMeter = Integer.parseInt(fillUP_meter.get(i));
            }
        }


        String total_price = String.valueOf(totalPrice);
        String total_quantity = String.valueOf(totalQuantity);
        String total_distance = String.valueOf(endMeter-startMeter);

        Log.d("price4","start "+startMeter);
        Log.d("price4","end "+endMeter);


        btn_summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Ups.this,"You are going to summary page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Fill_Up_Summary.class);
                intent.putExtra("totalprice", total_price );
                intent.putExtra("totalquantity", total_quantity );
                intent.putExtra("totaldistance", total_distance );
                intent.putExtra("vehicle_model", vehicle_model );
                startActivity(intent);
            }
        });



    }
    //this method is for refresh the page
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            recreate();
        }
    }


    void storeDataInArrays(String model){
        Cursor cursor = myDB.readOneData(model);
        if(cursor.getCount() == 0){
            emptyIcon.setVisibility(View.VISIBLE);
            tvnodata.setVisibility(View.VISIBLE);
        }
        else{
            while(cursor.moveToNext()){
                fillUP_id.add(cursor.getString(0));
                fillUP_date.add(cursor.getString(2));
                fillUp_quantity.add(cursor.getString(3));
                fillUp_price.add(cursor.getString(4));
                fillUP_meter.add(cursor.getString(5));

            }
            emptyIcon.setVisibility(View.GONE);
            tvnodata.setVisibility(View.GONE);
        }
    }

}