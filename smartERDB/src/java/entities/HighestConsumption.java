/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.math.BigDecimal;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
/**
 *
 * @author USER
 */
public class HighestConsumption 
{
    Date date;
    String datestring;
    int time;
    BigDecimal fridge;
    BigDecimal aircon;
    BigDecimal washingmachine;
    BigDecimal mid;
    BigDecimal usage;
    
    public HighestConsumption()
    {
        datestring = "";
        time = 0;
        usage = new BigDecimal("0");
    }
    
    public HighestConsumption(BigDecimal fridgeU, BigDecimal airconU, BigDecimal washing, Date dateparam, int hour)
    {   
        date = dateparam;
        datestring = date.toString();
        time = hour;
        fridge = fridgeU;
        aircon = airconU;
        washingmachine = washing;
        mid = fridge.add(aircon);
        mid = mid.add(washingmachine);
        usage = mid;
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
