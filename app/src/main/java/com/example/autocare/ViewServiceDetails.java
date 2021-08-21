package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewServiceDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_service_details);

        TextView title = findViewById(R.id.toolbar_app_name);
        ImageView backIcon = findViewById(R.id.back_btn);
        //Button button2 = findViewById(R.id.button4);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ViewServiceDetails.this,"You clicked back button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ViewServiceDetails.this,ServiceInterface.class);
                startActivity(intent);
            }
        });
        title.setText("Service Details");
       /* button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceInterface.this,"You are going to Add New Service Page", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ServiceInterface.this,ViewServiceDetails.class);
                startActivity(intent);
            }
        });*/

    }
}