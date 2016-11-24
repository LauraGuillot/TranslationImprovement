package org.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tables.Connect;
import org.tables.Hasmission;
import org.tables.Vote;
import org.tables.Wintrophy;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-22T16:43:11")
@StaticMetamodel(Player.class)
public class Player_ { 

    public static volatile CollectionAttribute<Player, Hasmission> hasmissionCollection;
    public static volatile CollectionAttribute<Player, Vote> voteCollection;
    public static volatile SingularAttribute<Player, String> playerName;
    public static volatile CollectionAttribute<Player, Wintrophy> wintrophyCollection;
    public static volatile CollectionAttribute<Player, Connect> connectCollection;
    public static volatile SingularAttribute<Player, Integer> playerPoints;
    public static volatile SingularAttribute<Player, Double> playerConfidenceScore;
    public static volatile SingularAttribute<Player, Integer> playerID;

}