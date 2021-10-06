package com.example.autocare;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText title_input, date_input, mileage_input;
    Button update_button, delete_button;

    String id, title2, date, mileage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        TextView title = findViewById(R.id.toolbar_app_name);

        title_input = findViewById(R.id.title_input3);
        date_input = findViewById(R.id.date_input3);
        mileage_input = findViewById(R.id.mileage_input3);
        update_button = findViewById(R.id.update_button3);
        delete_button = findViewById(R.id.delete_button4);


        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title2);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                title2 = title_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                mileage = mileage_input.getText().toString().trim();
                myDB.updateData(id, title2, date, mileage);
            }

        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);

                myDB.deleteOneRow(id);
                confirmDialog();


            }


        });
        title.setText("Update and delete");

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("mileage")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title2 = getIntent().getStringExtra("title");
            date = getIntent().getStringExtra("date");
            mileage = getIntent().getStringExtra("mileage");

            //Setting Intent Data
            title_input.setText(title2);
            date_input.setText(date);
            mileage_input.setText(mileage);
            Log.d("stev", title2 + " " + date + " " + mileage);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }

    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title2 + " ?");
        builder.setMessage("Are you sure you want to delete " + title2 + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }

        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}





