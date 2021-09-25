package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Adddetails extends AppCompatActivity {

    EditText title_input, date_input, mileage_input;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddetails);
        TextView title = findViewById(R.id.toolbar_app_name);
        title_input = findViewById(R.id.title_input);
        date_input = findViewById(R.id.date_input);
        mileage_input = findViewById(R.id.mileage_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Adddetails.this);
                myDB.addbook(title_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        Integer.valueOf(mileage_input.getText().toString().trim()));
            }
        });



        ImageView backIcon = findViewById(R.id.back_btn);


        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Adddetails.this,"You clicked back button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Adddetails.this,Reminder.class);
                startActivity(intent);
            }
        });
        title.setText("Add details");

    }
}