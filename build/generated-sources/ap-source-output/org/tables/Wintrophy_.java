package org.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tables.Player;
import org.tables.Trophy;
import org.tables.WintrophyPK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-22T16:43:11")
@StaticMetamodel(Wintrophy.class)
public class Wintrophy_ { 

    public static volatile SingularAttribute<Wintrophy, String> trophyDate;
    public static volatile SingularAttribute<Wintrophy, Trophy> trophy;
    public static volatile SingularAttribute<Wintrophy, WintrophyPK> wintrophyPK;
    public static volatile SingularAttribute<Wintrophy, Integer> trophyState;
    public static volatile SingularAttribute<Wintrophy, Integer> trophyStateBody;
    public static volatile SingularAttribute<Wintrophy, Player> player;

}