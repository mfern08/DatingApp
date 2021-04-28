package com.example.datingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView name;
    private TextView occupation;
    private TextView info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        name = findViewById(R.id.name);
        //age = findViewByID(R.id.ProfileAge);
        occupation = findViewById(R.id.occupation);
        info = findViewById(R.id.info);



        if (b != null) {
            if (b.containsKey((Constants.KEY_NAME))) {
                name.setText(b.getString(Constants.KEY_NAME));
            }
            if (b.containsKey((Constants.KEY_OCC))) {
                occupation.setText(b.getString(Constants.KEY_OCC));
            }
            if (b.containsKey((Constants.KEY_INFO))) {
                info.setText(b.getString(Constants.KEY_INFO));
            }
           /* if (b.containsKey((Constants.KEY_AGE))) {
                age.setText(b.getString(Constants.KEY_AGE));
            }*/

        }
    }
}