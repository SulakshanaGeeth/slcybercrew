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

public class Fill_Ups extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_ups);

        ImageView homeIcon = findViewById(R.id.home_btn);
        ImageView settingIcon = findViewById(R.id.setting_btn);
        TextView title = findViewById(R.id.toolbar_app_name);
        Button button = findViewById(R.id.btn_addFill_up);
        Button button1 = findViewById(R.id.btn_summery);
        Button button2 = findViewById(R.id.btn_editEntry5);

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Ups.this,"You are going to add fill-up page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Fill_Ups.this,Add_Fill_Up.class);
                startActivity(intent);
            }
        });

        title.setText("Fill-Ups");

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Ups.this,"You are going to summary page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Fill_Ups.this,Fill_Up_Summary.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Fill_Ups.this,"You are going to edit entry page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Fill_Ups.this,Edit_Fill_Up.class);
                startActivity(intent);
            }
        });

        Spinner dropdown = findViewById(R.id.spn_select_vehicle);
//create a list of items for the spinner.
        String[] items = new String[]{"Vehicle01", "Vehicle02", "Vehicle03"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);

    }
}