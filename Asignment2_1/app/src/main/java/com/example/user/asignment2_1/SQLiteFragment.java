package com.example.user.asignment2_1;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.user.asignment2_1.MainActivity.acUsage;
import static com.example.user.asignment2_1.MainActivity.fridgeUsage;
import static com.example.user.asignment2_1.MainActivity.tempCurrent;
import static com.example.user.asignment2_1.MainActivity.wmUsage;
import static android.support.constraint.Constraints.TAG;

/**
 * Created by USER on 26-Apr-18.
 */

public class SQLiteFragment extends Fragment {
    View vSQLite;
    protected DBManager dbManager;
    private TextView tv_sql, tv_wu, tv_fu, tv_au;
    private UsageFragment genUsage = new UsageFragment();
    private HomeScreenFragment temp = new HomeScreenFragment();
   private ArrayList<Double> fridgeUsageAr;
    private ArrayList<Double> acUsageAr;
    private ArrayList<Double> wmUsageAr;
    private double temperature;
    private Button bt_send;
    int usageId = 8800;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {
        vSQLite = inflater.inflate(R.layout.fragment_sqlite, container, false);
        tv_sql = (TextView)vSQLite.findViewById(R.id.tv_sql);
        tv_fu = (TextView)vSQLite.findViewById(R.id.tv_fu);
        tv_au = (TextView)vSQLite.findViewById(R.id.tv_au);
        tv_wu = (TextView)vSQLite.findViewById(R.id.tv_wu);
        bt_send = (Button)vSQLite.findViewById(R.id.bt_rest);


        fridgeUsageAr = fridgeUsage;
        wmUsageAr = wmUsage;
        acUsageAr = acUsage;
        temperature = tempCurrent;
        tv_au.setText(String.valueOf(acUsage.size()));
        tv_fu.setText(String.valueOf(fridgeUsageAr.size()));
        tv_wu.setText(String.valueOf(wmUsageAr.size()));
       // tv_sql.setText(new DecimalFormat("##.##").format(temperature));

        dbManager = new DBManager(getActivity().getApplicationContext());
        deleteAllData();
        insertData();

        //deleteData(8801);
       // tv_sql.setText(String.valueOf(getUsageById(8801).getFridgeusage()));

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncTask<Integer, Void, String>(){
                    @Override
                    protected String doInBackground(Integer... params) {
                        Usage usage = new Usage(Integer.valueOf(params[0]));
                        //Resident resident = new Resident();
                        //RestClient.createResident(resident);
                        RestClient.createUsage(usage);
                        return "Usage was added";
                    }

                    @Override
                    protected void onPostExecute(String response) {
                        tv_sql.setText(response);
                    }
                    }.execute(usageId);
            }
        });
     /* bt_send.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                  //create an anonymous AsyncTask
                  new AsyncTask<Void, Void, String>() {
                      @Override
                      protected String doInBackground(Void... params) {
                          return RestClient.findHourlyUsageData("hourly");
                      }

                      @Override
                      protected void onPostExecute(String resident) {
                          tv_sql.setText(resident);
                          tv_au.setText(String.valueOf(resident.length()));
                      }
                  }.execute();
          }
      });*/

        return vSQLite;
    }

    //Insert Data
    public void insertData(){
        try {
            dbManager.open();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        int usageId = 8800;


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String formatted_date = sdf.format(calendar.getTime());
        temperature = tempCurrent;
        //temperature = 1.1; //using for while
        int wmindex = 0;
        int acindex = 0;

        for(int i = 0; i < 24; i++) {
            //Washing Machine 3 data from 6 - 21
            //Fridge 24 data all day
            //AC 10 data 9 - 23

            //Insert Data from 0 - 5
            //Wm && Fridge not working
           if( i<=5 )
                dbManager.insertUsage(usageId,formatted_date,i,1104,0,0,fridgeUsageAr.get(0),temperature);

            //Insert Data from 6 - 8
            //Insert washing machine from 6 - 8 \\3 data
            else
                if(6 <= i && i <=8)
            {
                dbManager.insertUsage(usageId,formatted_date,i,1104,wmUsageAr.get(wmindex),0,fridgeUsageAr.get(0),temperature);
                wmindex = wmindex + 1;
            }
            //Insert data from 9 - 18
            // Insert AC 9 - 18 \\10 data
            else
                if(9 <=i && i<=18) {
                    dbManager.insertUsage(usageId,formatted_date,i,1104,0,acUsageAr.get(acindex),fridgeUsageAr.get(0),temperature);
                    acindex = acindex + 1;
                }
            else
               if(19<= i && i<24)
                dbManager.insertUsage(usageId,formatted_date,i,1104,0,0,fridgeUsageAr.get(0),temperature);

            usageId = usageId + 1;
        }
        //tv_sql.setText(readData());
        dbManager.close();
    }

    //Read Data
    public String readData(){
        Cursor c = dbManager.getAllUsers();
        String s="";
        if (c.moveToFirst()) {
            do {
                s+="Usageid: " + c.getString(0) + "\t" + "Date: " + c.getString(1)
                        + "\t" + "Hour Usage: " + c.getString(2)+ "\t" + "Res ID:" + c.getString(3)
                        + "\t" + "Washing Machine Usage:" + c.getString(4)
                        + "\t" + "AC Usage:" + c.getString(5)
                        + "\t" + "Fridge Usage:" + c.getString(6)
                        + "\t" + "Temperature:" + c.getString(7)
                        + "\n";
            } while (c.moveToNext());
        }
        return s;
    }

    //DeleteAllData
    public void deleteAllData(){
        try {
            dbManager.open();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        dbManager.deleteAll();
       // tv_sql.setText(readData());
        dbManager.close();
    }

    //Delete Data
    public void deleteData(int id){
        try {
            dbManager.open();
        }catch(SQLException e) {
            e.printStackTrace();
        }
        dbManager.deleteUsage(id);
       // tv_sql.setText(readData());
        dbManager.close();
    }


    public Usage getUsageById(int usageId) {
        String selectQuery = dbManager.getLineByUsageId(usageId);

        Log.d(TAG, selectQuery);
        Cursor c = dbManager.getOneRecord(selectQuery);

        if(c != null)
            c.moveToFirst();

        Usage usage = new Usage();
        usage.setUsageid(c.getInt(c.getColumnIndex(DBStructure.tableEntry.COLUMN_ID)));
        double ac = c.getDouble(c.getColumnIndex(DBStructure.tableEntry.COLUMN_AC_USAGE));
        double fridge = c.getDouble(c.getColumnIndex(DBStructure.tableEntry.COLUMN_FRIDGE_USAGE));
        double wm = c.getDouble(c.getColumnIndex(DBStructure.tableEntry.COLUMN_WM_USAGE));
        double temp = c.getDouble(c.getColumnIndex(DBStructure.tableEntry.COLUMN_TEMPERATURE));

        BigDecimal acu = BigDecimal.valueOf(ac);
        BigDecimal fridgeu = BigDecimal.valueOf(fridge);
        BigDecimal wmu = BigDecimal.valueOf(wm);
        BigDecimal tempu = BigDecimal.valueOf(temp);

        usage.setAirconusage(acu);
        usage.setFridgeusage(fridgeu);
        usage.setWashmachineusage(wmu);
        usage.setHourusage(c.getInt(c.getColumnIndex(DBStructure.tableEntry.COLUMN_HOURUSAGE)));
        usage.setTemperature(tempu);


        String dateString = c.getString(c.getColumnIndex(DBStructure.tableEntry.COLUMN_DATE));
        Date date = null;
        try {
            date = new SimpleDateFormat("YYYY-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        usage.setDate(date);

        return usage;

    }




}
