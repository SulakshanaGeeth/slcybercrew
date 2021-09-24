package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Fill_Up_Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_up_summary);

        ImageView backIcon = findViewById(R.id.back_btn);
        TextView title = findViewById(R.id.toolbar_app_name);




        title.setText("Fills-Up Summary ");


//        Spinner dropdown = findViewById(R.id.spn_select_month);
////create a list of items for the spinner.
//        String[] items = new String[]{"August", "September", "October"};
////create an adapter to describe how the items are displayed, adapters are used in several places in android.
////There are multiple variations of this, but this is the basic variant.
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
////set the spinners adapter to the previously created one.
//        dropdown.setAdapter(adapter);




        Intent intent = getIntent();
        String total_price = intent.getStringExtra("totalprice");
        String total_quantity = intent.getStringExtra("totalquantity");
        String total_distance = intent.getStringExtra("totaldistance");
        String vehicle_model = intent.getStringExtra("vehicle_model");

        TextView displayModel = findViewById(R.id.tv_fill_up_display_model);
        displayModel.setText(vehicle_model);

        Double fualeff =  Double.parseDouble(total_distance)/  Double.parseDouble(total_quantity);


        DecimalFormat df=new DecimalFormat("#.##");

        Log.d("price",total_price);
        Log.d("price2",total_quantity);
        Log.d("price3",total_distance);
        Log.d("price4","efficiency "+fualeff);

        TextView TotalPrice = findViewById(R.id.tv_total_cost_ans);
        TotalPrice.setText(total_price);

        TextView TotalQuantity = findViewById(R.id.tv_fuel_qty_ans);
        TotalQuantity.setText(total_quantity);

        TextView TotalDistance = findViewById(R.id.tv_travel_distance_ans);
        TotalDistance.setText(total_distance);

        TextView efficiency = findViewById(R.id.tv_fuel_efficiency_ans);
        efficiency.setText(String.valueOf(df.format(fualeff)));


        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Up_Summary.this,"You clicked back button", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Fill_Up_Summary.this,Fill_Ups.class);
                Intent intent = new Intent(getApplicationContext(), Fill_Ups.class);
                intent.putExtra("selectItem", vehicle_model );
                startActivity(intent);
            }
        });

    }

}