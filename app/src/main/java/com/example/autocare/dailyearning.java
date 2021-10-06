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

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

public class dailyearning extends AppCompatActivity {


    private EditText num1;
    private EditText num2;
    private EditText num3;
    private EditText num4;
    private EditText num5;
    private EditText num6;
    private EditText num7;
    private Button add;
    private TextView result;
    private Button average;
    private TextView displayaverage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyearning);


        ImageView homeIcon = findViewById(R.id.home_btn);
        TextView title = findViewById(R.id.toolbar_app_name);
        title.setText("Daily Earning");




        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(dailyearning.this,"You clicked home", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(dailyearning.this,MainActivity.class);
                startActivity(intent);
            }
        });



        num1 =(EditText) findViewById(R.id.etNum1);
        num2 =(EditText) findViewById(R.id.etNum2);
        num3 =(EditText) findViewById(R.id.etNum3);
        num4 =(EditText) findViewById(R.id.etNum4);
        num5 =(EditText) findViewById(R.id.etNum5);
        num6 =(EditText) findViewById(R.id.etNum6);
        num7 =(EditText) findViewById(R.id.etNum7);
        add = (Button) findViewById(R.id.btnAdd);
        result =(TextView) findViewById(R.id.tvAnswer);
        average = (Button) findViewById(R.id.btnAverage);
        displayaverage =(TextView) findViewById(R.id.tvAverage);


        AwesomeValidation awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.etNum1,
                RegexTemplate.NOT_EMPTY,R.string.Earning_input_empty);
        awesomeValidation.addValidation(this,R.id.etNum2,
                RegexTemplate.NOT_EMPTY,R.string.Earning_input_empty);
        awesomeValidation.addValidation(this,R.id.etNum3,
                RegexTemplate.NOT_EMPTY,R.string.Earning_input_empty);
        awesomeValidation.addValidation(this,R.id.etNum4,
                RegexTemplate.NOT_EMPTY,R.string.Earning_input_empty);
        awesomeValidation.addValidation(this,R.id.etNum5,
                RegexTemplate.NOT_EMPTY,R.string.Earning_input_empty);
        awesomeValidation.addValidation(this,R.id.etNum6,
                RegexTemplate.NOT_EMPTY,R.string.Earning_input_empty);
        awesomeValidation.addValidation(this,R.id.etNum7,
                RegexTemplate.NOT_EMPTY,R.string.Earning_input_empty);





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    int number1 = Integer.parseInt(num1.getText().toString());
                    int number2 = Integer.parseInt(num2.getText().toString());
                    int number3 = Integer.parseInt(num3.getText().toString());
                    int number4 = Integer.parseInt(num4.getText().toString());
                    int number5 = Integer.parseInt(num5.getText().toString());
                    int number6 = Integer.parseInt(num6.getText().toString());
                    int number7 = Integer.parseInt(num7.getText().toString());

                    int sum = number1 + number2 + number3 + number4 + number5 + number6 + number7;
                    result.setText("Weekly Earning Is : " + String.valueOf(sum));

                    int avg = (number1 + number2 + number3 + number4 + number5 + number6 + number7) / 7;
                    displayaverage.setText("Daily Earning Average : " + String.valueOf(avg));
                } else {
                    Toast.makeText(getApplicationContext(), "Validation Faild",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}