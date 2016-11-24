package topicLabelling.labelling.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import topicLabelling.labelling.tables.Category;
import topicLabelling.labelling.tables.RelationPK;
import topicLabelling.labelling.tables.Word;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-22T16:43:11")
@StaticMetamodel(Relation.class)
public class Relation_ { 

    public static volatile SingularAttribute<Relation, Integer> occurence;
    public static volatile SingularAttribute<Relation, RelationPK> relationPK;
    public static volatile SingularAttribute<Relation, Category> category;
    public static volatile SingularAttribute<Relation, Word> word;

}