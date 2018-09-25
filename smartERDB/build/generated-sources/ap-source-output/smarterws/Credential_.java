package smarterws;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import smarterws.Resident;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-28T17:23:16")
@StaticMetamodel(Credential.class)
public class Credential_ { 

    public static volatile SingularAttribute<Credential, String> password;
    public static volatile SingularAttribute<Credential, Date> registrationdate;
    public static volatile SingularAttribute<Credential, Resident> resid;
    public static volatile SingularAttribute<Credential, String> username;

}