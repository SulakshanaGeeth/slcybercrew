package com.example.autocare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateService extends AppCompatActivity {
    EditText type_input2, date_input2, description_input2, present_input2, next_input2, cost_input2;
    Button btn_update, btn_delete;

    String id, type, date, description, present, next, cost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_service);

        TextView title = findViewById(R.id.toolbar_app_name);
        ImageView backIcon = findViewById(R.id.back_btn);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateService.this,"You clicked back button", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateService.this,ServiceInterface.class);
                startActivity(intent);
            }
        });

        type_input2 = findViewById(R.id.input_type2);
        date_input2 = findViewById(R.id.input_date2);
        description_input2 = findViewById(R.id.input_description2);
        present_input2 = findViewById(R.id.input_present2);
        next_input2 = findViewById(R.id.input_next2);
        cost_input2 = findViewById(R.id.input_total2);
        btn_update = findViewById(R.id.btn_updateService);
        btn_delete = findViewById(R.id.btn_deleteService);

        //first we call this
        getAndSetIntentData();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceMyDatabase myDB = new ServiceMyDatabase(UpdateService.this);

                //And only then call this
                myDB.updateData(id, type_input2.getText().toString().trim(), date_input2.getText().toString().trim(), description_input2.getText().toString().trim(), present_input2.getText().toString().trim(), next_input2.getText().toString().trim(), cost_input2.getText().toString().trim());
                Intent intent = new Intent(getApplicationContext(), ServiceInterface.class);
                startActivity(intent);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        title.setText("Service Details");
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("type") && getIntent().hasExtra("date") &&
                getIntent().hasExtra("description") && getIntent().hasExtra("present") &&getIntent().hasExtra("next")
                &&getIntent().hasExtra("cost")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            type = getIntent().getStringExtra("type");
            date = getIntent().getStringExtra("date");
            description = getIntent().getStringExtra("description");
            present = getIntent().getStringExtra("present");
            next = getIntent().getStringExtra("next");
            cost = getIntent().getStringExtra("cost");

            //Setting Intent Data
            type_input2.setText(type);
            date_input2.setText(date);
            description_input2.setText(description);
            present_input2.setText(present);
            next_input2.setText(next);
            cost_input2.setText(cost);

        }else {
            Toast.makeText(this, "No Services to display", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + type + " ?");
        builder.setMessage("Are you sure to delete " + type + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                ServiceMyDatabase myDB = new ServiceMyDatabase(UpdateService.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
    }
}