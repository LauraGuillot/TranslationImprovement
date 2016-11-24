package org.tables;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.tables.Translation;
import org.tables.Vote;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-08-22T16:43:11")
@StaticMetamodel(Sentence.class)
public class Sentence_ { 

    public static volatile SingularAttribute<Sentence, Integer> translationApproved;
    public static volatile CollectionAttribute<Sentence, Vote> voteCollection;
    public static volatile CollectionAttribute<Sentence, Translation> translationCollection;
    public static volatile SingularAttribute<Sentence, Boolean> sentenceValidation;
    public static volatile SingularAttribute<Sentence, Integer> sentenceID;
    public static volatile SingularAttribute<Sentence, String> sentenceLanguage;
    public static volatile SingularAttribute<Sentence, String> translationLanguage;
    public static volatile SingularAttribute<Sentence, String> sentenceDate;
    public static volatile SingularAttribute<Sentence, String> sentenceBody;

}