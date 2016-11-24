package topicLabelling.labelling.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import topicLabelling.labelling.tables.Relation;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-22T16:43:11")
@StaticMetamodel(Category.class)
public class Category_ { 

    public static volatile CollectionAttribute<Category, Relation> relationCollection;
    public static volatile SingularAttribute<Category, String> cat;
    public static volatile SingularAttribute<Category, Integer> categoryID;

}