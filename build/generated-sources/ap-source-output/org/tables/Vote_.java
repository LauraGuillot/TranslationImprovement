package org.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tables.Player;
import org.tables.Sentence;
import org.tables.VotePK;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-22T16:43:11")
@StaticMetamodel(Vote.class)
public class Vote_ { 

    public static volatile SingularAttribute<Vote, Sentence> sentence;
    public static volatile SingularAttribute<Vote, Integer> voteFor;
    public static volatile SingularAttribute<Vote, String> voteDate;
    public static volatile SingularAttribute<Vote, VotePK> votePK;
    public static volatile SingularAttribute<Vote, Player> player;

}