package com.udacity.stockhawk.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.utilities.CharUtils;

public class DetailActivity extends AppCompatActivity {

    public String historicPrices;
    public String symbol;

    public LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        lineChart = (LineChart) findViewById(R.id.chart);

        Intent intentThatStartedThisActivity = getIntent();

        if(intentThatStartedThisActivity != null){
            if(intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT) && intentThatStartedThisActivity.hasExtra(Intent.EXTRA_SHORTCUT_NAME)){
                historicPrices = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                symbol = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_SHORTCUT_NAME);
                CharUtils charUtils = new CharUtils(this);
                charUtils.createChart();
                lineChart.invalidate();
            }
        }

    }
}
