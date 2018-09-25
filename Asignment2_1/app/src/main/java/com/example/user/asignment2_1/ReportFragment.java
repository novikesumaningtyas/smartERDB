package com.example.user.asignment2_1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by USER on 25-Apr-18.
 */

public class ReportFragment extends Fragment implements
        AdapterView.OnItemSelectedListener{
    View vReport;
    Spinner spinner;
    String display[] = {"Hourly","Daily"};
    LineChart chart, chart2;
    Button bt_pie, bt_chart, bt_bar;
    TextView testtv, tv_date;
    BarChart bar, bar2;
    PieChart pie;
    String option;
    EditText pickDate;
    Context context;
    Calendar calendar;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle
            savedInstanceState)
    {
        vReport = inflater.inflate(R.layout.fragment_report, container, false);
        bt_pie = (Button)vReport.findViewById(R.id.bt_pie);
        bt_bar = (Button)vReport.findViewById(R.id.bt_bar);
        bt_chart = (Button)vReport.findViewById(R.id.bt_chart);
        pickDate = (EditText)vReport.findViewById(R.id.datein);
        tv_date =(TextView)vReport.findViewById(R.id.tv_date);
        context = getActivity().getApplicationContext();

        //testtv = (TextView)vReport.findViewById(R.id.testtv);

        //calendar = (DatePicker)vReport.findViewById(R.id.datePicker);

        spinner = (Spinner)vReport.findViewById(R.id.spinner_dis);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext()
                ,android.R.layout.simple_spinner_item, display);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(this);

        //gettingspinnervalue
        //Spinner mySpinner=(Spinner) findViewById(R.id.your_spinner);
        //option = spinner.getSelectedItem().toString();
        //listener to button


        chart = (LineChart)vReport.findViewById(R.id.linechart);
        bar = (BarChart)vReport.findViewById(R.id.barChart);
        pie = (PieChart)vReport.findViewById(R.id.pieChart);

        chart2 = (LineChart)vReport.findViewById(R.id.linechart2);
        bar2 = (BarChart)vReport.findViewById(R.id.barChart2);


        List<Float> inputRest = getHourlyListForChart();
        List<Float> inputRestTemp = getHourlyTempListForChart();
        lineChart(inputRest, inputRestTemp);
        chart.setVisibility(View.GONE);

            barChart(inputRest);
            bar.setVisibility(View.GONE);

            List<Float> inputDailyApp = getDailyAppliance();
            pieChart(inputDailyApp);
            pie.setVisibility(View.GONE);


            barChartDaily();
            bar2.setVisibility(View.GONE);
            lineChartDaily();
            chart2.setVisibility(View.GONE);



        bt_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option = spinner.getSelectedItem().toString();
                if(option.equalsIgnoreCase("hourly")) {
                    chart.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                    pie.setVisibility(View.GONE);
                    bar2.setVisibility(View.GONE);
                    chart2.setVisibility(View.GONE);
                }
                else{
                    chart2.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                    pie.setVisibility(View.GONE);
                    bar2.setVisibility(View.GONE);
                    chart.setVisibility(View.GONE);
                }
            }
        });

        bt_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                option = spinner.getSelectedItem().toString();
                if(option.equalsIgnoreCase("hourly"))
                {
                    bar.setVisibility(View.VISIBLE);
                    pie.setVisibility(View.GONE);
                    chart.setVisibility(View.GONE);
                    bar2.setVisibility(View.GONE);
                    chart2.setVisibility(View.GONE);
                }
                else{
                    bar2.setVisibility(View.VISIBLE);
                    pie.setVisibility(View.GONE);
                    chart.setVisibility(View.GONE);
                    chart2.setVisibility(View.GONE);
                    bar.setVisibility(View.GONE);
                    }
            }
        });

        bt_pie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                option = spinner.getSelectedItem().toString();
                if(option.equalsIgnoreCase("daily")) {
                    pie.setVisibility(View.VISIBLE);
                    bar.setVisibility(View.GONE);
                    chart.setVisibility(View.GONE);
                    chart2.setVisibility(View.GONE);
                    bar2.setVisibility(View.GONE);
                }
            }
        });

        //getDate();

        return vReport;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapter, View view, int position, long l) {
        //On selecting a spinner item
        String display =  adapter.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(getActivity().getApplicationContext(),
                "Selected Display : " + display, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
            // TODO Auto-generated method stub
    }

    ///Find data for All Chart

    //Change the data we getting from REST to float list for hourly usage
    public static List<Float> getHourlyList(String list){
        List<Float> resultList = new ArrayList<>();
        String[] splitResult = list.split(",");

            for(int n=0; n < splitResult.length; n++)
            {
                if(splitResult[n].contains("usage")){
                    resultList.add(
                            Float.parseFloat
                                    (splitResult[n].substring
                                            (splitResult[n].indexOf(":")+1,splitResult[n].indexOf(":")+4)));
                }
            }
        return resultList;
    }



    public void getDate(){
        calendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                String formatDate = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
                pickDate.setText(sdf.format(calendar.getTime()));

            }
        };

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity().getApplicationContext(), date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    public static List<Float> getHourlyTemp(String list){
        List<Float> resultList = new ArrayList<>();

            String[] splitResult = list.split(",");

            for(int n=0; n < splitResult.length; n++)
            {
                if(splitResult[n].contains("temperature"))
                {
                    resultList.add(
                            Float.parseFloat
                                    (splitResult[n].substring
                                            (splitResult[n].indexOf(":")+1,splitResult[n].indexOf(":")+5)));
                }
            }
        return resultList;
    }

    public static List<Float> getDailyUsageForEachAppliance(String list){
        List<Float> resultList = new ArrayList<>();

        String[] splitResult = list.split(",");
        for(int n=0; n < splitResult.length; n++)
        {
            if(splitResult[n].contains("airCon"))
            {
                resultList.add(Float.parseFloat(splitResult[n].substring(splitResult[n].indexOf(":")+1,splitResult[n].indexOf(":")+5)));
            }

            else
                if(splitResult[n].contains("fridge"))
                {
                    resultList.add(Float.parseFloat(splitResult[n].substring(splitResult[n].indexOf(":")+1,splitResult[n].indexOf(":")+5)));
                }

            else
                if(splitResult[n].contains("washingMachine"))
                {
                    resultList.add(Float.parseFloat(splitResult[n].substring(splitResult[n].indexOf(":")+1,splitResult[n].indexOf(":")+4)));
                }
        }

        return resultList;
    }



    //AsyncTask for grabbing data hourly usage
    public class AsyncTaskChart extends AsyncTask<String, Void, List<Float>> {

        @Override
        protected List<Float> doInBackground(String... params) {
            List<Float> usageList;
            usageList = getHourlyList(RestClient.findHourlyUsageData(params[0]));

            return usageList;
        }

        @Override
        protected void onPostExecute(List<Float> result) {
        }
    }


    //AsyncTask for grabbing data hourly temp
    public class AsyncTaskChartTemp extends AsyncTask<String, Void, List<Float>> {

        @Override
        protected List<Float> doInBackground(String... params) {
            List<Float> usageList;
            usageList = getHourlyTemp(RestClient.findHourlyUsageData(params[0]));

            return usageList;
        }

        @Override
        protected void onPostExecute(List<Float> result) {
        }
    }

    //AsyncTask for grabbing data daily usage for each appliance
    public class AsyncTaskChartDailyApp extends AsyncTask<Void, Void, List<Float>> {

        @Override
        protected List<Float> doInBackground(Void... params) {
            List<Float> usageList;
            usageList = getDailyUsageForEachAppliance(RestClient.findDailyUsageForEachAppliance());

            return usageList;
        }

        @Override
        protected void onPostExecute(List<Float> result) {
        }

    }

    //gettingHourlyListForChart
    public List<Float> getHourlyListForChart(){
        AsyncTaskChart grabData = new AsyncTaskChart();

        List<Float> hourlyList = new ArrayList<>();
        //using hourly
        try {
            hourlyList = grabData.execute("hourly").get();
            int size = hourlyList.size();
           // testtv.setText(String.valueOf(size));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return hourlyList;
    }

    //gettingHourlyTempListForChart
    public List<Float> getHourlyTempListForChart(){
        AsyncTaskChartTemp grabData = new AsyncTaskChartTemp();

        List<Float> hourlyTempList = new ArrayList<>();
        //using hourly
        try {
            hourlyTempList = grabData.execute("hourly").get();
            int size = hourlyTempList.size();
            //testtv.setText(String.valueOf(size));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return hourlyTempList;
    }


    //getting daily usage for each appliance
    public List<Float> getDailyAppliance(){
        AsyncTaskChartDailyApp grabData = new AsyncTaskChartDailyApp();

        List<Float> dailyApp = new ArrayList<>();
        //using hourly
        try {
            dailyApp = grabData.execute().get();
            int size = dailyApp.size();
           // testtv.setText(String.valueOf(dailyApp.get(2)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return dailyApp;
    }


    public void lineChart(List<Float> list, List<Float> temp){
        List<Entry> entries = new ArrayList<>();
        List<Entry> entries2 = new ArrayList<>();

        float[] xAxis =  {0f,1f,2f,3f,4f};

        //getting start from hour 8
        for (int i = 0; i <xAxis.length; i++){
            entries.add(new Entry(xAxis[i], list.get(i+8)));
        }

        for (int i = 0; i <xAxis.length; i++){
            entries2.add(new Entry(xAxis[i], temp.get(i+8)));
        }

        final String[] hours = new String[] { "8", "9", "10", "11","12" };
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return hours[(int)value];
            }
        };

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet dataSet = new LineDataSet(entries, "Usage");
        LineDataSet dataSet2 = new LineDataSet(entries2, "Temperature");
        chart.getDescription().setText("Hourly Usage at 4 March,2018 with Temperature");

        dataSet2.setColor(Color.GREEN);
        dataSet2.setCircleColor(Color.GREEN);
        dataSet2.setAxisDependency(YAxis.AxisDependency.RIGHT);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        lineDataSets.add(dataSet);
        lineDataSets.add(dataSet2);


        chart.setData(new LineData(lineDataSets));
        XAxis xAxisFromChart = chart.getXAxis();
        xAxisFromChart.setDrawAxisLine(true);
        xAxisFromChart.setValueFormatter(formatter);
        xAxisFromChart.setGranularity(1f);
        xAxisFromChart.setPosition(XAxis.XAxisPosition.BOTTOM);

    }


    public void lineChartDaily(){
        List<Float> list = new ArrayList<>();
        list.add(45.6f);
        list.add(38.8f);
        list.add(50.5f);

        List<Float> temp = new ArrayList<>();
        temp.add(27.7f);
        temp.add(35.2f);
        temp.add(23.8f);

        List<Entry> entries = new ArrayList<>();
        List<Entry> entries2 = new ArrayList<>();

        float[] xAxis =  {0f,1f,2f};

        for (int i = 0; i <xAxis.length; i++){
            entries.add(new Entry(xAxis[i], list.get(i)));
        }

        for (int i = 0; i <xAxis.length; i++){
            entries2.add(new Entry(xAxis[i], temp.get(i)));
        }

        final String[] days = new String[] { "March 4", "March 14", "March 15" };
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return days[(int)value];
            }
        };

        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();

        LineDataSet dataSet = new LineDataSet(entries, "Usage");
        LineDataSet dataSet2 = new LineDataSet(entries2, "Temperature");
        chart2.getDescription().setText("Daily Usage at March, 2018 with Temperature");

        dataSet2.setColor(Color.MAGENTA);
        dataSet2.setCircleColor(Color.MAGENTA);
        dataSet2.setAxisDependency(YAxis.AxisDependency.RIGHT);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        lineDataSets.add(dataSet);
        lineDataSets.add(dataSet2);

        chart2.setData(new LineData(lineDataSets));
        XAxis xAxisFromChart = chart2.getXAxis();
        xAxisFromChart.setDrawAxisLine(true);
        xAxisFromChart.setValueFormatter(formatter);
        // minimum axis-step (interval) is 1,if no, the same value will be displayed multiple times
        xAxisFromChart.setGranularity(1f);
        xAxisFromChart.setPosition(XAxis.XAxisPosition.BOTTOM);

    }

    public void barChart(List<Float> list){
        ArrayList<BarEntry> entries = new ArrayList<>();
        float[] xAxis = {0f,1f,2f,3f,4f};

        for (int i = 0; i <xAxis.length; i++){
            entries.add(new BarEntry(xAxis[i], list.get(i+8)));
        }

        final String[] hours = new String[] { "8", "9", "10", "11","12" };
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return hours[(int)value];
            }
        };

        BarDataSet dataSet = new BarDataSet(entries, "Electrical Usage");
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        BarData barData = new BarData(dataSet);
        bar.setData(barData);
        XAxis xAxisFromChart = bar.getXAxis();
        xAxisFromChart.setDrawAxisLine(true);
        xAxisFromChart.setValueFormatter(formatter);
        xAxisFromChart.setGranularity(1f);
        xAxisFromChart.setPosition(XAxis.XAxisPosition.BOTTOM);
        bar.getDescription().setText("Hourly Usage at 4 March,2018");

    }

    public void barChartDaily(){
        List<Float> list = new ArrayList<>();
        list.add(76.5f);
        list.add(60.8f);
        list.add(88.5f);

        ArrayList<BarEntry> entries = new ArrayList<>();
        float[] xAxis = {0f,1f,2f};

        for (int i = 0; i <xAxis.length; i++){
            entries.add(new BarEntry(xAxis[i], list.get(i)));
        }

        final String[] months = new String[] {"January", "February", "March"};
        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int)value];
            }
        };

        BarDataSet dataSet = new BarDataSet(entries, "Electrical Usage");
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        BarData barData = new BarData(dataSet);
        bar2.setData(barData);
        XAxis xAxisFromChart = bar2.getXAxis();
        xAxisFromChart.setDrawAxisLine(true);
        xAxisFromChart.setValueFormatter(formatter);
        xAxisFromChart.setGranularity(1f);
        xAxisFromChart.setPosition(XAxis.XAxisPosition.BOTTOM);
        bar2.getDescription().setText("Total usage per day for Jan - Mar");
    }


    public void pieChart(List<Float> list){
        ArrayList<PieEntry> entries = new ArrayList<>();
        ArrayList<String> xEntries = new ArrayList<>();

        String[] xData = {"AC", "Fridge", "WM"};

        for (int i = 0; i <list.size(); i++) {
            entries.add(new PieEntry(list.get(i), xData[i]));
        }
        for (int i = 0; i <list.size(); i++) {
            xEntries.add(xData[i]);
        }

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.GRAY);

        pie.getDescription().setText("Daily usage for each appliance at 4 March,2018");

        //add legend
        Legend legend = pie.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieDataSet dataSet = new PieDataSet(entries, "Usage");
        dataSet.setColors(colors);
        dataSet.setSliceSpace(2);
        PieData pieData = new PieData(dataSet);
        pieData.setValueTextSize(12f);
        pieData.setValueFormatter(new PercentFormatter());
        pie.setData(pieData);
        pie.setHoleRadius(25f);
        pie.setTransparentCircleAlpha(0);
        pie.setRotationEnabled(true);
        pie.setUsePercentValues(true);

    }








}

