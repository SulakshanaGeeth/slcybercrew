package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class viewvehicle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewvehicle);


        Button editbutton = findViewById(R.id.button45);
        TextView title = findViewById(R.id.toolbar_app_name);



        editbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewvehicle.this,"You are going to add fill-up page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(viewvehicle.this,editvehicaldetails.class);
                startActivity(intent);
            }
        });


        title.setText("View Vehicle Details");



    }
}