package com.example.datingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

import static android.app.DatePickerDialog.OnDateSetListener;

public class MainActivity extends AppCompatActivity implements OnDateSetListener {
    private Button button;
    private TextView textView;
    private EditText name;
    private EditText info;
    private EditText occupation;
    private EditText email;
    private DatePickerDialog.OnDateSetListener dsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openSecondActivity();
            }
        });

        textView = (TextView) findViewById(R.id.date);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }



    public void submit(View v){
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        info = findViewById(R.id.info);
        occupation = findViewById(R.id.occupation);



        if(name.getText().toString().matches("")){
            name.setError("Enter your name");
            return;
        }

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        Bundle bundle = new Bundle();

        bundle.putString(Constants.KEY_NAME, name.getText().toString());
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String dateString = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());

        textView = (TextView) findViewById(R.id.date);
        textView.setText(dateString);
    }





    public void openSecondActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }

}