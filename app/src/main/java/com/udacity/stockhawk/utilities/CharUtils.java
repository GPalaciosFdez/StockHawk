package com.udacity.stockhawk.utilities;


import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.udacity.stockhawk.R;
import com.udacity.stockhawk.ui.DetailActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CharUtils {

    private DetailActivity detailActivity;
    private ArrayList<String> dates = new ArrayList<>();
    private List<Entry> values = new ArrayList<>();


    public CharUtils(DetailActivity detailActivity){
        this.detailActivity = detailActivity;
    }

    public void createChart(){
        prepareData();
        setChartData();
    }

    private void prepareData(){
        DateFormat df = new SimpleDateFormat("dd/MM/yy", Locale.US);
        Entry entry;
        String[] dateAndValue;
        Date d;

        String[] dataArray = detailActivity.historicPrices.split("\\r?\\n");
        float count = 0f;

        for(int i = dataArray.length-1; i>=0; i--){
            dateAndValue = dataArray[i].split(", ");

            Long dat = Long.parseLong(dateAndValue[0]);
            d = new Date(dat);
            String date = df.format(d);
            dates.add(date);

            float value = Float.valueOf(dateAndValue[1]);
            entry = new Entry(count, value);
            values.add(entry);

            count++;

        }
    }

    private void setChartData(){
        LineDataSet lineDataSet = new LineDataSet(values, detailActivity.symbol);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setCircleColor(R.color.material_green_700);

        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);

        LineData lineData = new LineData(dataSets);

        IAxisValueFormatter iAxisValueFormatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return dates.get((int) value);
            }
        };

        detailActivity.lineChart.setData(lineData);

        XAxis xAxis = detailActivity.lineChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(iAxisValueFormatter);

    }




}
