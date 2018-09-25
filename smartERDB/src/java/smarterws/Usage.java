/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarterws;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "USAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usage.findAll", query = "SELECT u FROM Usage u")
    , @NamedQuery(name = "Usage.findByUsageid", query = "SELECT u FROM Usage u WHERE u.usageid = :usageid")
    , @NamedQuery(name = "Usage.findByDate", query = "SELECT u FROM Usage u WHERE u.date = :date")
    , @NamedQuery(name = "Usage.findByAirconusage", query = "SELECT u FROM Usage u WHERE u.airconusage = :airconusage")
    , @NamedQuery(name = "Usage.findByTemperature", query = "SELECT u FROM Usage u WHERE u.temperature = :temperature")
    , @NamedQuery(name = "Usage.findByHourusage", query = "SELECT u FROM Usage u WHERE u.hourusage = :hourusage")
    , @NamedQuery(name = "Usage.findByFridgeusage", query = "SELECT u FROM Usage u WHERE u.fridgeusage = :fridgeusage")
    , @NamedQuery(name = "Usage.findByWashmachineusage", query = "SELECT u FROM Usage u WHERE u.washmachineusage = :washmachineusage")
        // Task 3.4
    , @NamedQuery(name = "Usage.findByEmailANDAirconusage", query = "SELECT u FROM Usage u WHERE LOWER(u.resid.email) = LOWER(:email) AND u.airconusage = :airconusage")

})
public class Usage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USAGEID")
    private Integer usageid;
    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "AIRCONUSAGE")
    private BigDecimal airconusage;
    @Column(name = "TEMPERATURE")
    private BigDecimal temperature;
    @Column(name = "HOURUSAGE")
    private Integer hourusage;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FRIDGEUSAGE")
    private BigDecimal fridgeusage;
    @Column(name = "WASHMACHINEUSAGE")
    private BigDecimal washmachineusage;
    @JoinColumn(name = "RESID", referencedColumnName = "RESID")
    @ManyToOne
    private Resident resid;

    public Usage() {
    }

    public Usage(Integer usageid) {
        this.usageid = usageid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usageid != null ? usageid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usage)) {
            return false;
        }
        Usage other = (Usage) object;
        if ((this.usageid == null && other.usageid != null) || (this.usageid != null && !this.usageid.equals(other.usageid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "smarterws.Usage[ usageid=" + usageid + " ]";
    }
    
}
