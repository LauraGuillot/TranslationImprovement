package org.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tables.HasmissionPK;
import org.tables.Mission;
import org.tables.Player;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-22T16:43:11")
@StaticMetamodel(Hasmission.class)
public class Hasmission_ { 

    public static volatile SingularAttribute<Hasmission, String> missionDate;
    public static volatile SingularAttribute<Hasmission, Mission> mission;
    public static volatile SingularAttribute<Hasmission, String> missionStateBody;
    public static volatile SingularAttribute<Hasmission, String> missionInfo;
    public static volatile SingularAttribute<Hasmission, HasmissionPK> hasmissionPK;
    public static volatile SingularAttribute<Hasmission, Integer> missionState;
    public static volatile SingularAttribute<Hasmission, Player> player;

}