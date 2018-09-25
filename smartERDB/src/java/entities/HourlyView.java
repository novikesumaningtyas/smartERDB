/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
/**
 *
 * @author USER
 */
public class HourlyView 
{
    int resid;
    BigDecimal fridge;
    BigDecimal aircon;
    BigDecimal washingmachine;
    BigDecimal mid;
    BigDecimal usage;
    BigDecimal temperature;
    Date date;
    SimpleDateFormat dateparam;
    int time;
    String datestring;
    
    public HourlyView()
    {
        resid = 0;
        usage = new BigDecimal("0");
        temperature = new BigDecimal("0");
        date = new Date();
        time = 0;
        datestring = "";
        
    }
    
    public HourlyView(int res, BigDecimal fridgeU, BigDecimal airconU, BigDecimal washing, BigDecimal temp, Date dateparam, int hour)
    {
        resid = res;
        fridge = fridgeU;
        aircon = airconU;
        washingmachine = washing;
        mid = fridge.add(aircon);
        mid = mid.add(washingmachine);
        usage = mid;
        temperature = temp;
        date = dateparam;
        datestring = date.toString();
        time = hour;
    }
    
    public BigDecimal getFridge()
    {
        return fridge;
    }
    
    public void setFridge(BigDecimal fridge)
    {
        this.fridge = fridge;
    }
    
    public BigDecimal getAirCon()
    {
        return aircon;
    }
    
    public void setAirCon(BigDecimal aircon)
    {
        this.aircon = aircon;
    }
    
    public BigDecimal getWashingMachine()
    {
        return washingmachine;
    }
    
    public void setWashingMachine(BigDecimal washing)
    {
        this.washingmachine = washing;
    }
    
    public String getDatestring()
    {
        return datestring; 
    }
    
    public void setDatestring(String num)
    {
        datestring = num;
    }
    
    public int getResId()
    {
        return resid;
    }
    
    public void setResId(int resid)
    {
        this.resid = resid;
    }
    
     public BigDecimal getTemperature()
    {
        return temperature;
    }
    
    public void setTemperature(BigDecimal temp)
    {
        temperature = temp;
    }
    
    public int getTime()
    {
        return time;
    }
    
    public void setTime(int hour)
    {
        time = hour;
    }
    
    public Date getDate()
    {
        return date;
    }
    
    public void setDate(Date number)
    {
        date = number;
    }
    
    public BigDecimal getUsage()
    {
        return usage;
    }
    
    public void setUsage(BigDecimal consume)
    {
        usage = consume;
    }  
}
