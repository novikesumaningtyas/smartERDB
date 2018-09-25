package com.example.user.asignment2_1;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by USER on 27-Apr-18.
 */

public class Usage {
    private Integer usageid ;
    private Date date;
    private BigDecimal airconusage;
    private BigDecimal temperature;
    private Integer hourusage;
    private BigDecimal fridgeusage;
    private BigDecimal washmachineusage;
    private Resident resid;
    private Resident resident;

    public Usage() {
    }

    public Usage(Integer usageid) {
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();

        this.usageid = usageid;
        this.hourusage = 0;
        this.resid = new Resident();
        this.washmachineusage = new BigDecimal("0");
        this.airconusage = new BigDecimal("0");
        this.temperature = new BigDecimal("0");
        this.fridgeusage = new BigDecimal("0");
    }

    public Integer getUsageid() {
        return usageid;
    }

    public void setUsageid(Integer usageid) {
        this.usageid = usageid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getAirconusage() {
        return airconusage;
    }

    public void setAirconusage(BigDecimal airconusage) {
        this.airconusage = airconusage;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public Integer getHourusage() {
        return hourusage;
    }

    public void setHourusage(Integer hourusage) {
        this.hourusage = hourusage;
    }

    public BigDecimal getFridgeusage() {
        return fridgeusage;
    }

    public void setFridgeusage(BigDecimal fridgeusage) {
        this.fridgeusage = fridgeusage;
    }

    public BigDecimal getWashmachineusage() {
        return washmachineusage;
    }

    public void setWashmachineusage(BigDecimal washmachineusage) {
        this.washmachineusage = washmachineusage;
    }

    public Resident getResid() {
        return resid;
    }

    public void setResid(Resident resid) {
        this.resid = resid;
    }
}
