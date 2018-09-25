/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "test")
@XmlAccessorType(XmlAccessType.FIELD)
/**
 *
 * @author USER
 */
public class test {
    @XmlElement(name = "test")
    String Fname;
    String Sname;
    String nick;
    
    public test()
    {
        Fname = "";
        Sname = "";
        nick = "";
    }
    
    public test(String Fname, String Sname)
    {
        this.Fname = Fname;
        this.Sname = Sname;
        nick = "Hey halo guys";
    }
    
    public String getNick()
    {
        return nick;
    }
    
    public void setNick()
    {
        nick = "Hey Halo";
    }
    
    public String getFName()
    {
            return Fname;
    }
    
    public void setFName(String fname)
    {
        this.Fname = fname;
        
    }
    
    public String getSname()
    {
        return Sname;
    }
    
    public void setSname(String sname)
    {
        this.Sname = sname;
    }
    
    
    
    
    
    
}
