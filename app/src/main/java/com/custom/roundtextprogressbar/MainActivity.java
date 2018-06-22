package com.custom.roundtextprogressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import custom.RoundProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RoundProgressBar roundProgressBar = findViewById(R.id.pb_driver_score);
        roundProgressBar.setCurrentProgress(80,"current progress");
    }
}
