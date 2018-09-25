/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
/**
 *
 * @author USER
 */
public class ApplianceUsage 
{
    BigDecimal fridge;
    BigDecimal aircon;
    BigDecimal washingmachine;
    
    public ApplianceUsage()
    {
        fridge = new BigDecimal("0");
        aircon = new BigDecimal("0");
        washingmachine = new BigDecimal("0");
    }
    
    public ApplianceUsage(BigDecimal fridgeU, BigDecimal ac, BigDecimal washing)
    {
        fridge = fridgeU;
        aircon = ac;
        washingmachine = washing;
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
    
    
}
