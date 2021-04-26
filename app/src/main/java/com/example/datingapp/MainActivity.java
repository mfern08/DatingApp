package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

import static android.app.DatePickerDialog.OnDateSetListener;

public class MainActivity extends AppCompatActivity implements OnDateSetListener {
    private Button button;
    private TextView textView;
    private DatePickerDialog.OnDateSetListener dsListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openSecondActivity();
            }
        });

        textView = (TextView) findViewById(R.id.Date);
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String dateString = DateFormat.getDateInstance(DateFormat.SHORT).format(cal.getTime());

        textView = (TextView) findViewById(R.id.Date);
        textView.setText(dateString);
    }

    public void openSecondActivity(){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
}