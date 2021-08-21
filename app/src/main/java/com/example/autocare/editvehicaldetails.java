package com.example.autocare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class editvehicaldetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editvehicaldetails);

        TextView title = findViewById(R.id.toolbar_app_name);
        title.setText("Edit Vehicle Details");

    }
}