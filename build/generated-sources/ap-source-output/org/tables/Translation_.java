package org.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tables.Sentence;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-22T16:43:11")
@StaticMetamodel(Translation.class)
public class Translation_ { 

    public static volatile SingularAttribute<Translation, Integer> translationID;
    public static volatile SingularAttribute<Translation, Sentence> sentenceID;
    public static volatile SingularAttribute<Translation, String> translationBody;
    public static volatile SingularAttribute<Translation, Integer> player;

}