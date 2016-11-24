package org.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tables.Hastopic;
import org.tables.Islinkto;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-22T16:43:11")
@StaticMetamodel(Topic.class)
public class Topic_ { 

    public static volatile CollectionAttribute<Topic, Islinkto> islinktoCollection;
    public static volatile SingularAttribute<Topic, Integer> topicID;
    public static volatile CollectionAttribute<Topic, Hastopic> hastopicCollection;
    public static volatile SingularAttribute<Topic, String> topicName;

}