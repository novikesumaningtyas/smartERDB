package com.example.user.asignment2_1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by USER on 27-Apr-18.
 */

public class Resident {
    private Integer resid;
    private String fname;
    private String sname;
    private String address;
    private Integer postcode;
    private String email;
    private Integer mobile;
    private Integer numofresidents;
    private String energyprovider;
    private Date dob;

    public Resident() {
        resid = 1122;
        fname = "Kristiagsgn";
        sname = "Wijasgsya";
        address = "967 Dagsgng Road";
        postcode = 3045;
        email = "fx.wijgsgsaya@yahoo.om";
        mobile = 4816246;
        numofresidents = 1;
        energyprovider = "AGL";

        String dateStr = "1988-04-29";
        SimpleDateFormat dateParam = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try {
            date1 = dateParam.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        dob = date1;

    }

    public Resident(Integer resid) {
        this.resid = resid;
    }

    public Integer getResid() {
        return resid;
    }

    public void setResid(Integer resid) {
        this.resid = resid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public Integer getNumofresidents() {
        return numofresidents;
    }

    public void setNumofresidents(Integer numofresidents) {
        this.numofresidents = numofresidents;
    }

    public String getEnergyprovider() {
        return energyprovider;
    }

    public void setEnergyprovider(String energyprovider) {
        this.energyprovider = energyprovider;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}