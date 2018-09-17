package com.dot.outpool;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class FragmentOne extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
    {

        final View view =inflater.inflate(R.layout.fragment_one,container,false);

        PieChart chart = view.findViewById(R.id.chart);

        Random rand=new Random();
        ArrayList<PieEntry> pieEntries=new ArrayList<>();

        String Time[]={"1 AM","2 AM","3 AM","4 AM","5 AM","6 AM"};
        int[] allColors = view.getResources().getIntArray(R.array.colors);

        for(int i=1;i<7;i++)
        {
            pieEntries.add(new PieEntry(rand.nextInt(10),Time[i-1]));
        }

        PieDataSet pieDataSet=new PieDataSet(pieEntries,"");
        pieDataSet.setColors(allColors);

        PieData PieData=new PieData(pieDataSet);
        chart.setData(PieData);

        chart.getDescription().setText("Last 6 Hours");
        chart.getDescription().setEnabled(true);
        chart.getLegend().setEnabled(true);

        ////////////////////////////////////////////////////

        LineChart chart1=view.findViewById(R.id.chart1);

        XAxis xAxis=chart1.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setLabelCount(12);
        xAxis.setDrawLabels(true);
        xAxis.setGranularity(1f);


        YAxis left=chart1.getAxisLeft();
        left.setDrawGridLines(false);
        YAxis right=chart1.getAxisRight();
        right.setDrawGridLines(false);
        right.setDrawLabels(false);

        Random rand1=new Random();
        ArrayList<Entry> lineEntries=new ArrayList<>();
        for(int i=1;i<7;i++)
        {
            lineEntries.add(new Entry(i,rand1.nextInt(10)));
        }

        LineDataSet lineDataSet=new LineDataSet(lineEntries,"");
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        lineDataSet.setLineWidth(0);
        lineDataSet.setDrawValues(false);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(ContextCompat.getColor(getContext(),R.color.green_500));
        lineDataSet.setFillAlpha(255);
        lineDataSet.setColor(R.color.black);
        lineDataSet.setCircleColor(R.color.black);
        lineDataSet.setValueTextSize(9);

        LineData lineData=new LineData(lineDataSet);
        chart1.setData(lineData);

        //chart1.setVisibleXRangeMaximum(12);
        chart1.getDescription().setEnabled(true);
        chart1.getLegend().setEnabled(false);
        chart1.getDescription().setText("Future 6 Hours");

        return view;
    }


}