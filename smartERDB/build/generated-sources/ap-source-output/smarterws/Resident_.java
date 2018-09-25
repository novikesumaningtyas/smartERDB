package smarterws;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import smarterws.Credential;
import smarterws.Usage;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-28T17:23:16")
@StaticMetamodel(Resident.class)
public class Resident_ { 

    public static volatile SingularAttribute<Resident, String> fname;
    public static volatile SingularAttribute<Resident, String> address;
    public static volatile CollectionAttribute<Resident, Usage> usageCollection;
    public static volatile SingularAttribute<Resident, String> sname;
    public static volatile SingularAttribute<Resident, Date> dob;
    public static volatile CollectionAttribute<Resident, Credential> credentialCollection;
    public static volatile SingularAttribute<Resident, Integer> postcode;
    public static volatile SingularAttribute<Resident, Integer> mobile;
    public static volatile SingularAttribute<Resident, Integer> resid;
    public static volatile SingularAttribute<Resident, String> energyprovider;
    public static volatile SingularAttribute<Resident, String> email;
    public static volatile SingularAttribute<Resident, Integer> numofresidents;

}