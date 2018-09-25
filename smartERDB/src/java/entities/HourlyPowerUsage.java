/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package entities;

/**
 *
 * @author USER
 */
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement

public class HourlyPowerUsage 
{
    BigDecimal fridgeUsage;
    BigDecimal washingMachineUsage;
    BigDecimal airConUsage;
    BigDecimal usage;
    BigDecimal mid;
    
    public HourlyPowerUsage(BigDecimal fridgeU, BigDecimal washingMachineU, BigDecimal ac)
    {
        fridgeUsage = fridgeU;
        washingMachineUsage = washingMachineU;
        airConUsage = ac;
        mid = fridgeUsage.add(airConUsage);
        mid = mid.add(washingMachineUsage);
        usage = mid;
    }
    
    public HourlyPowerUsage()
    {
        usage = new BigDecimal("0");
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
