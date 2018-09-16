package com.dot.outpool;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Random;


public class FragmentOne extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {

        final View view =inflater.inflate(R.layout.fragment_one,container,false);

        LineChart chart = view.findViewById(R.id.chart);

        XAxis xAxis=chart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(12);
        xAxis.setDrawLabels(true);


        YAxis left=chart.getAxisLeft();
        left.setDrawGridLines(false);
        YAxis right=chart.getAxisRight();
        right.setDrawGridLines(false);
        right.setDrawLabels(false);

        Random rand=new Random();
        ArrayList<Entry> lineEntries=new ArrayList<>();
        for(int i=1;i<25;i++)
        {
            lineEntries.add(new Entry(i,rand.nextInt(10)));
        }

        LineDataSet lineDataSet=new LineDataSet(lineEntries,"Hello");
        lineDataSet.setLineWidth(5);
        lineDataSet.setColor(R.color.black);
        lineDataSet.setCircleColor(R.color.black);
        lineDataSet.setValueTextSize(9);

        LineData lineData=new LineData(lineDataSet);
        chart.setData(lineData);

        chart.setVisibleXRangeMaximum(12);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setEnabled(false);

        return view;
    }


}