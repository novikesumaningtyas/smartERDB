package com.example.user.asignment2_1;

/**
 * Created by USER on 24-Apr-18.
 */

public class usageSimulator {
    private double fridgeUsage;
    private double acUsage;
    private double washingMachineUsage;

    public double generateFrideUsage(){
        double fridgeMin = 0.3;
        double fridgeMax = 0.8;

        double diff = fridgeMax - fridgeMin;
        fridgeUsage = fridgeMin + Math.random() * diff;
        fridgeUsage = Math.floor(fridgeUsage * 100) / 100 ;

        return fridgeUsage;
    }

    public double generateAcUsage(){
        double acMin = 1;
        double acMax = 5;

        double diff = acMax - acMin;
        acUsage = acMin + Math.random() * diff;
        acUsage = Math.floor(acUsage * 100) / 100 ;

        return acUsage;
    }

    public double generateWmUsage(){
        double wmMin = 0.4;
        double wmMax = 1.3;

        double diff = wmMax - wmMin;
        washingMachineUsage = wmMin + Math.random() * diff;
        washingMachineUsage = Math.floor(washingMachineUsage * 100) / 100 ;

        return washingMachineUsage;
    }
}
