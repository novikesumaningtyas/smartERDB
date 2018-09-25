package com.example.user.asignment2_1;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import static android.support.constraint.Constraints.TAG;
import static com.example.user.asignment2_1.MainActivity.acUsage;
import static com.example.user.asignment2_1.MainActivity.fridgeUsage;
import static com.example.user.asignment2_1.MainActivity.wmUsage;

/**
 * Created by USER on 26-Apr-18.
 */

public class UsageFragment extends Fragment {
    View vUsage;
    private static boolean findFridge =false;
    private static boolean findAc =false;
    private static boolean findWm =false;
    usageSimulator usageGen = new usageSimulator();
    TextView tv_ac, tv_fridge, tv_wm, tv_count, tv_wm_data, tv_ac_data;
    Button but_usage;
    double fridge;
    double ac;
    double wm;
    int counter = 0;
    int wmData = 0;
    int acData = 0;
    //double temperature;
    //ArrayList<Double> fridgeUsage = new ArrayList<>();
    //ArrayList<Double> wmUsage = new ArrayList<>();
    //ArrayList<Double> acUsage = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState)
    {
        vUsage = inflater.inflate(R.layout.fragment_usage, container, false);
        tv_ac = (TextView)vUsage.findViewById(R.id.tv_acUsage);
        tv_fridge = (TextView)vUsage.findViewById(R.id.tv_fridgeUsage);
        tv_wm = (TextView)vUsage.findViewById(R.id.tv_wmUsage);
        tv_count = (TextView)vUsage.findViewById(R.id.tv_count);
        tv_ac_data = (TextView)vUsage.findViewById(R.id.tv_ac_data);
        tv_wm_data = (TextView)vUsage.findViewById(R.id.tv_wm_data);
        but_usage = (Button)vUsage.findViewById(R.id.button2);

        but_usage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGenerator();
            }
        });

        return vUsage;
    }

    public void checkGenerator(){
        Thread t=new Thread(){


            @Override
            public void run(){

                //while(!isInterrupted()){
                while(counter <= 22){
                    try {
                        Thread.sleep(1000);  //1000ms = 1 sec //3600000 1 hour

                        getActivity().runOnUiThread(new Runnable() {

                                                        @Override
                                                        public void run(){


                                                            if(!findFridge){
                                                                fridge = usageGen.generateFrideUsage();
                                                                addFridgeUsage(fridge);
                                                                findFridge = true;
                                                            }

                                                            if(!findAc){
                                                                ac = usageGen.generateAcUsage();
                                                                addAcUsage(ac);
                                                                acData = acData + 1;
                                                                if(acData == 10)
                                                                    findAc = true;
                                                            }

                                                            if(!findWm){
                                                                wm = usageGen.generateWmUsage();
                                                                addWmUsage(wm);
                                                                wmData = wmData + 1;

                                                                if(wmData == 3)
                                                                    findWm = true;
                                                            }


                                                            tv_ac.setText(new DecimalFormat("##.##").format(ac));
                                                            tv_fridge.setText(new DecimalFormat("##.##").format(fridge));
                                                            tv_wm.setText(new DecimalFormat("##.##").format(wm));


                                                            counter = counter + 1;


                                                            tv_count.setText(String.valueOf(counter));
                                                            tv_wm_data.setText(String.valueOf(wmData));
                                                            tv_ac_data.setText(String.valueOf(acData));
                                                        }
                                                    }

                        );

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        t.start();

    }

    public ArrayList<Double> addFridgeUsage(double data){
        fridgeUsage.add(data);
        return fridgeUsage;
    }

    public ArrayList<Double> addAcUsage(double data){
        acUsage.add(data);
        return acUsage;
    }

    public ArrayList<Double> addWmUsage(double data){
        wmUsage.add(data);
        return wmUsage;
    }



}
