package com.example.mrlex.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewCalculation extends AppCompatActivity {

    public static final String TEMP = "log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_calculation);

        Intent intent = getIntent();
        String temp = intent.getStringExtra(TEMP);
        TextView display = (TextView) findViewById(R.id.display);
        display.setText(temp);
    }
}
