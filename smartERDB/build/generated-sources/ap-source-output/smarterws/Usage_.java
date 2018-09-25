package smarterws;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import smarterws.Resident;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-28T17:23:16")
@StaticMetamodel(Usage.class)
public class Usage_ { 

    public static volatile SingularAttribute<Usage, Date> date;
    public static volatile SingularAttribute<Usage, BigDecimal> fridgeusage;
    public static volatile SingularAttribute<Usage, Integer> usageid;
    public static volatile SingularAttribute<Usage, BigDecimal> temperature;
    public static volatile SingularAttribute<Usage, Integer> hourusage;
    public static volatile SingularAttribute<Usage, BigDecimal> washmachineusage;
    public static volatile SingularAttribute<Usage, BigDecimal> airconusage;
    public static volatile SingularAttribute<Usage, Resident> resid;

}