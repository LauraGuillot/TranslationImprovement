package org.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tables.Hasmission;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-22T16:43:11")
@StaticMetamodel(Mission.class)
public class Mission_ { 

    public static volatile CollectionAttribute<Mission, Hasmission> hasmissionCollection;
    public static volatile SingularAttribute<Mission, Integer> missionID;
    public static volatile SingularAttribute<Mission, String> missionBody;

}