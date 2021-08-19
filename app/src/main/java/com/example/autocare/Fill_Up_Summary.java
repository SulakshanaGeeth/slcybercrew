package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Fill_Up_Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_up_summary);

        ImageView backIcon = findViewById(R.id.back_btn);
        TextView title = findViewById(R.id.toolbar_app_name);
        Button button = findViewById(R.id.btn_View_cost_details);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Up_Summary.this,"You clicked back button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Fill_Up_Summary.this,Fill_Ups.class);
                startActivity(intent);
            }
        });

        title.setText("Fills-Up Summary ");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Fill_Up_Summary.this,Cost_Details.class);
                startActivity(intent);
            }
        });

        Spinner dropdown = findViewById(R.id.spn_select_month);
//create a list of items for the spinner.
        String[] items = new String[]{"August", "September", "October"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }

}