package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Adddetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);

        TextView title = findViewById(R.id.toolbar_app_name);


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