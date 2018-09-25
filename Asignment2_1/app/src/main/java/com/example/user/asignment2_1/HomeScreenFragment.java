package com.example.user.asignment2_1;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.user.asignment2_1.MainActivity.tempCurrent;
import static com.example.user.asignment2_1.MainActivity.user;

/**
 * Created by USER on 25-Apr-18.
 */

public class HomeScreenFragment  extends Fragment {
    View vHomeScreen;
    TextView tv_temp, tv_city, tv_date, tv_desc,tv_user, tv_good, tv_bad, tv_usage;
    String name="";
    ImageView happy, sad;
    ReportFragment report = new ReportFragment();
    String formatted_date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState){

        vHomeScreen = inflater.inflate(R.layout.fragment_home_screen,container,false);
        tv_temp = (TextView)vHomeScreen.findViewById(R.id.tv_temp);
        tv_date = (TextView)vHomeScreen.findViewById(R.id.tv_date);
        tv_city = (TextView)vHomeScreen.findViewById(R.id.tv_city);
        tv_desc = (TextView)vHomeScreen.findViewById(R.id.tv_desc);
        tv_user = (TextView)vHomeScreen.findViewById(R.id.tv_user_name);
        tv_bad = (TextView)vHomeScreen.findViewById(R.id.msgbad);
        tv_good = (TextView)vHomeScreen.findViewById(R.id.msggood);
        tv_usage = (TextView)vHomeScreen.findViewById(R.id.tv_usage);
        sad = (ImageView)vHomeScreen.findViewById(R.id.bad);
        happy = (ImageView)vHomeScreen.findViewById(R.id.smile);

        tv_bad.setVisibility(View.GONE);
        tv_good.setVisibility(View.GONE);

        AsyncTaskFirstName findFirstName = new AsyncTaskFirstName();
        try {
              name = findFirstName.execute(user).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        String fname = name.substring(11, name.length()-3);
        tv_user.setText(fname);

        currentWeather();
       // checkUsage();
        getUsage();

        //Intent pass_data_intent = new Intent(getActivity().getApplicationContext(), UsageFragment.class);
        //pass_data_intent.putExtra("tempCurrent", tempCur);

        //startActivity(pass_data_intent);


        return vHomeScreen;
    }


    public void getUsage()
    {
        List<Float> daily = report.getHourlyListForChart();
        float result;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        formatted_date = sdf.format(calendar.getTime());

        String hour = formatted_date.substring(11,13);
        boolean checkPeak = false;

        int time = Integer.valueOf(hour);

        int[] peak = {9,10,11,12,13,14,15,16,17,18,19,20,21,22};

        //for report purpose
        //time = 1;

        for(int i=0; i < 14 ; i++)
        {
            if(time == peak[i])
                checkPeak = true;
        }

        result = daily.get(time);

        if(checkPeak)
        {
            if(daily.get(time) > 1.5f)
            {
                sad.setVisibility(View.VISIBLE);
                tv_bad.setVisibility(View.VISIBLE);
            }

            else
                if(daily.get(time) <= 1.5f)
                {
                    happy.setVisibility(View.VISIBLE);
                    tv_good.setVisibility(View.VISIBLE);
                }
        }

        DecimalFormat usageTot = new DecimalFormat("#.##");
        float twoDigitsF = Float.valueOf(usageTot.format(result));
        String totalU= "Usage: " + String.valueOf(twoDigitsF) + " kWh";

        tv_usage.setText(totalU);
    }


    public class AsyncTaskFirstName extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            return RestClient.findFirstNameByUsername(user);
        }

        @Override
        protected void onPostExecute(String result) {
        }
    }

    public double currentWeather() {
        String url = "http://api.openweathermap.org/data/2.5/weather?id=7932638&APPID=d74d65a8edf96d7294bbb5f5809e3232";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject main_object = response.getJSONObject("main");
                    JSONArray array = response.getJSONArray("weather");
                    JSONObject object = array.getJSONObject(0);
                    //Temperature in Kelvin
                    String temperature = String.valueOf(main_object.getDouble("temp"));
                    String description = object.getString("description");
                    String city = response.getString("name");

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    formatted_date = sdf.format(calendar.getTime());

                    //Change temperature to Celcius
                    tempCurrent = Double.parseDouble(temperature);
                    tempCurrent = tempCurrent - 273.15 ;

                    //setting the textview
                    tv_city.setText(city);
                    tv_desc.setText(description);
                    tv_date.setText(formatted_date);
                    //tv_temp.setText(String.valueOf(temperature_cel));
                    tv_temp.setText(new DecimalFormat("##.##").format(tempCurrent));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );

        RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        queue.add(request);

        return tempCurrent;
    }

}





