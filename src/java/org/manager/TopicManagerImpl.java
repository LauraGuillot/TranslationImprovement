/**
 * ********************************************************************
 * Class TopicManagerImpl
 * Management of the topics
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.tables.Hastopic;
import org.tables.HastopicPK;
import org.tables.Sentence;
import org.tables.Topic;

public class TopicManagerImpl implements TopicManager {

    private EntityManagerFactory emf;
    private static TopicManagerImpl theTopicManager;

    private TopicManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("translationPU");
        }
    }

    public static TopicManager getInstance() {
        if (theTopicManager == null) {
            theTopicManager = new TopicManagerImpl();
        }
        return theTopicManager;
    }

    /**
     * Get all the topics
     *
     * @return List of topics
     */
    @Override
    public List getTopics() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT t FROM Topic t ORDER BY t.topicName ASC");
        List l = q.getResultList();
        return l;
    }

    /**
     * Add a topic to a player
     *
     * @param id Topic ID
     * @param playerID Player ID
     */
    @Override
    public void addTopic(int id, int playerID) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT t FROM Hastopic t WHERE (t.hastopicPK.playerID=:pid AND t.hastopicPK.topicID=:tid)");
        q.setParameter("pid", playerID);
        q.setParameter("tid", id);
        List l = q.getResultList();

        if (l.isEmpty()) {
            HastopicPK pk = new HastopicPK(playerID, id);
            Hastopic t = new Hastopic(pk);
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }
    }

    /**
     * Get the topics of a player
     *
     * @param id Player ID
     * @return List of topics
     */
    @Override
    public ArrayList<Topic> myTopics(int id) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Topic> t = new ArrayList<Topic>();

        Query q = em.createQuery("SELECT h.topic FROM Hastopic h WHERE h.hastopicPK.playerID =:id ORDER BY h.topic.topicName");
        q.setParameter("id", id);
        List l = q.getResultList();

        for (Object o : l) {
            t.add((Topic) o);
        }

        return t;
    }

    /**
     * Get the topics which are not in the player ones
     *
     * @param id Player ID
     * @return List of topics
     */
    @Override
    public ArrayList<Topic> getOthersTopics(int id) {
        List<Topic> t1 = this.getTopics();
        ArrayList<Topic> t2 = this.myTopics(id);
        ArrayList<Topic> t3 = new ArrayList<Topic>();

        for (Topic to1 : t1) {
            boolean b = false;
            for (Topic to2 : t2) {
                if (to1.getTopicID() == to2.getTopicID()) {
                    b = true;
                }
            }
            if (!b) {
                t3.add(to1);
            }
        }
        return t3;
    }

    /**
     * Get the topics of a sentence
     *
     * @param s Sentence
     * @return List of topics
     */
    @Override
    public ArrayList<Topic> getTopics(Sentence s) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Topic> t = new ArrayList<Topic>();

        Query q = em.createQuery("SELECT t.topic FROM Islinkto t WHERE (t.islinktoPK.sentenceID=:id )");
        q.setParameter("id", s.getSentenceID());
        List l = q.getResultList();

        for (Object o : l) {
            t.add((Topic) o);
        }
        return t;
    }

}
