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
public class totalPowerUsage 
{
    BigDecimal fridge;
    BigDecimal aircon;
    BigDecimal washingmachine;
    BigDecimal mid;
    int resid;
    String address;
    int postcode;
    String totalusage;
    
    public totalPowerUsage()
    {
        resid = 0;
        postcode = 0;
        address = "";
        totalusage = "";
    }
    
    public totalPowerUsage(BigDecimal fridgeU, BigDecimal airconU, BigDecimal washing, int res, String add, int pc)
    {
        fridge = fridgeU;
        aircon = airconU;
        washingmachine = washing;
        resid = res;
        address = add;
        postcode = pc;
        mid = fridge.add(aircon);
        mid = mid.add(washingmachine);
        totalusage = mid.toString();    
    }

    public int getPostCode()       
    {
        return postcode;
    }
    
    public void setPostCode(int pc)
    {
        this.postcode = pc;
    }
    
    public String getTotalUsage()
    {
        return totalusage;
    }
    
    public void setTotalUsage(String total)
    {
        totalusage = "0";
    }
   
    public int getResId()
    {
        return resid;
    }
    
    public void setResId(int resid)
    {
        this.resid = resid;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String add)
    {
        this.address = add;
    } 
}
