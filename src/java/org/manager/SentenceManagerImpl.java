/**
 * ********************************************************************
 * Class SentenceManagerImpl
 * Management of the sentences
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
import org.tables.*;
import org.utils.Date;

public class SentenceManagerImpl implements SentenceManager {

    private EntityManagerFactory emf;
    private static SentenceManagerImpl theSentenceManager;

    private SentenceManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("translationPU");
        }
    }

    public static SentenceManager getInstance() {
        if (theSentenceManager == null) {
            theSentenceManager = new SentenceManagerImpl();
        }
        return theSentenceManager;
    }

    /**
     * Retrieve all the sentences which are available to the vote
     *
     * @param l1 language of the sentence
     * @param l2 language of his translations
     * @return
     */
    @Override
    public ArrayList<Sentence> getSentenceInProcess(String l1, String l2) {
        ArrayList<Sentence> s = new ArrayList<Sentence>();
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT s FROM Sentence s WHERE (s.sentenceValidation=false AND s.sentenceLanguage=:l1 AND s.translationLanguage=:l2)");
        q.setParameter("l1", l1);
        q.setParameter("l2", l2);
        List l = q.getResultList();

        for (Object o : l) {
            s.add((Sentence) o);
        }
        return s;
    }

    /**
     * Delete from a array of sentences, the sentences submitted by the player
     *
     * @param playerID Player ID
     * @param s Array of sentences
     * @return Array of sentences
     */
    @Override
    public ArrayList<Sentence> getSentenceNotSubmitted(int playerID, ArrayList<Sentence> s) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Sentence> s1 = new ArrayList<Sentence>();

        for (Sentence se : s) {
            Query q = em.createQuery("SELECT t.player FROM Translation t WHERE (t.sentenceID=:id )");
            q.setParameter("id", se);
            List l = q.getResultList();

            boolean add = true;
            for (Object o : l) {
                if ((Integer) o == playerID) {
                    add = false;
                }
            }
            if (add) {
                s1.add(se);
            }
        }
        return s1;
    }

    /**
     * Delete from a sentences list, the sentences for which the player has
     * already votes
     *
     * @param playerID Player ID
     * @param s Array of sentences
     * @return Array of sentences
     */
    @Override
    public ArrayList<Sentence> getSentenceNotVoted(int playerID, ArrayList<Sentence> s) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Sentence> s1 = new ArrayList<Sentence>();

        for (Sentence se : s) {
            Query q = em.createQuery("SELECT t.player.playerID FROM Vote t WHERE (t.sentence.sentenceID=:id)");
            q.setParameter("id", se.getSentenceID());
            List l = q.getResultList();

            boolean add = true;
            for (Object o : l) {
                if ((Integer) o == playerID) {
                    add = false;
                }
            }
            if (add) {
                s1.add(se);
            }
        }
        return s1;
    }

    /**
     * Delete from an Array of sentences, the sentences which have no topics in
     * common with the player's ones
     *
     * @param playerID Player ID
     * @param s Array of sentences
     * @return Array of sentences
     */
    @Override
    public ArrayList<Sentence> getSentenceWithTopics(int playerID, ArrayList<Sentence> s) {
        EntityManager em = emf.createEntityManager();

        ArrayList<Sentence> s1 = new ArrayList<Sentence>();

        //Selection des topics du joueur
        Query q2 = em.createQuery("SELECT h.hastopicPK.topicID FROM Hastopic h WHERE (h.hastopicPK.playerID=:id)");
        q2.setParameter("id", playerID);
        List topics = q2.getResultList();

        for (Sentence se : s) {

            //On selectionne les topics de la phrase
            Query q3 = em.createQuery("SELECT a.islinktoPK.topicID FROM Islinkto a WHERE (a.islinktoPK.sentenceID=:id)");
            q3.setParameter("id", se.getSentenceID());
            List sentenceTopics = q3.getResultList();

            if (this.common(topics, sentenceTopics)) {
                s1.add(se);
            }
        }

        return s1;

    }

    /**
     * Delete from an Array of sentences, the sentences which have topics in
     * common with the player's ones
     *
     * @param playerID playerID
     * @param s Array of sentences
     * @return Array of sentences
     */
    @Override
    public ArrayList<Sentence> getSentenceWithoutTopics(int playerID, ArrayList<Sentence> s) {
        EntityManager em = emf.createEntityManager();

        ArrayList<Sentence> s1 = new ArrayList<Sentence>();

        //Selection des topics du joueur
        Query q2 = em.createQuery("SELECT h.hastopicPK.topicID FROM Hastopic h WHERE (h.hastopicPK.playerID=:id)");
        q2.setParameter("id", playerID);
        List topics = q2.getResultList();

        for (Sentence se : s) {

            //On selectionne les topics de la phrase
            Query q3 = em.createQuery("SELECT a.islinktoPK.topicID FROM Islinkto a WHERE (a.islinktoPK.sentenceID=:id)");
            q3.setParameter("id", se.getSentenceID());
            List sentenceTopics = q3.getResultList();

            if (!this.common(topics, sentenceTopics)) {
                s1.add(se);
            }
        }
        return s1;
    }

    /**
     * Given two lists of int, check if there is at least one int in the both
     * lists
     *
     * @param l1 List of integers
     * @param l2 List of integers
     * @return Boolean
     */
    public static boolean common(List<Integer> l1, List<Integer> l2) {
        boolean b = false;
        for (Integer i1 : l1) {
            for (Integer i2 : l2) {
                if (i1 == i2) {
                    b = true;
                }
            }
        }

        return b;
    }

    /**
     * Generate a list of ten sentences, not already evaluated by the player,
     * not submitted by the player and not validated
     *
     * @param playerID Player ID
     * @param l1 Language of the sentences
     * @param l2 Language of the tranlations
     * @return List of sentences
     */
    @Override
    public ArrayList<Sentence> genere10(int playerID, String l1, String l2) {
        ArrayList<Sentence> s = this.getSentenceInProcess(l1, l2);
        s = this.getSentenceNotSubmitted(playerID, s);
        s = this.getSentenceNotVoted(playerID, s);

        ArrayList<Sentence> s1 = this.getSentenceWithTopics(playerID, s);
        ArrayList<Sentence> s2 = this.getSentenceWithoutTopics(playerID, s);

        if (s1.size() + s2.size() >= 10) {
            if (s1.size() >= 10) {
                ArrayList<Sentence> s3 = new ArrayList<Sentence>();
                for (int i = 0; i < 10; i++) {
                    s3.add(s1.get(i));
                }
                return s3;
            } else {
                ArrayList<Sentence> s3 = new ArrayList<Sentence>();
                for (int i = 0; i < s1.size(); i++) {
                    s3.add(s1.get(i));
                }

                for (int i = s1.size(); i < 10; i++) {
                    s3.add(s2.get(i - s1.size()));
                }
                return s3;
            }

        } else {
            return new ArrayList<Sentence>();
        }
    }

    /**
     * Generate a sentence, not already evaluated by the player, not submitted
     * by the player and not validated
     *
     * @param p Player
     * @param l1 Language of the sentences
     * @param l2 Language of the tranlations
     * @return a sentence
     */
    @Override
    public Sentence getSentence(Player p, String l1, String l2
    ) {
        ArrayList<Sentence> s = this.getSentenceInProcess(l1, l2);
        s = this.getSentenceNotSubmitted(p.getPlayerID(), s);
        s = this.getSentenceNotVoted(p.getPlayerID(), s);
        ArrayList<Sentence> s1 = this.getSentenceWithTopics(p.getPlayerID(), s);
        ArrayList<Sentence> s2 = this.getSentenceWithoutTopics(p.getPlayerID(), s);

        if (!s1.isEmpty()) {
            return s1.get(0);
        } else if (!s2.isEmpty()) {
            return s2.get(0);
        } else {
            return null;
        }
    }

    /**
     * Get the number of submissions made by the player
     *
     * @param playerID Player ID
     * @return Number of submissions
     */
    @Override
    public int nbSubmissionsByPlayer(int playerID
    ) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT t FROM Translation t WHERE (t.player =:id )");
        q.setParameter("id", playerID);
        List l = q.getResultList();
        return l.size();
    }

    /**
     * Get the number of submissions made by the player approved by the
     * community
     *
     * @param playerID Player ID
     * @return number of submissions made by the player approved by the
     * community
     */
    @Override
    public int nbSubmissionsApprovedByPlayer(int playerID) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT s FROM Sentence s WHERE (s.sentenceValidation=true)");
        List l = q.getResultList();
        int n = 0;
        for (Object o : l) {
            Sentence se = (Sentence) o;
            q = em.createQuery("SELECT t FROM Translation t WHERE (t.sentenceID = :se AND t.translationID=:tid)");
            q.setParameter("se", se);
            q.setParameter("tid", se.getTranslationApproved());
            List l1 = q.getResultList();
            if (!l1.isEmpty()) {
                if (((Translation) l1.get(0)).getPlayer() == playerID) {
                    n++;
                }
            }
        }
        return n;
    }

    /**
     * Get the list of translation proposed by the player
     *
     * @param id Player ID
     * @return List of translations
     */
    @Override
    public ArrayList<Translation> mySubmissions(int id
    ) {
        ArrayList<Translation> s = new ArrayList<Translation>();
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT t FROM Translation t WHERE (t.player =:id ) ORDER BY t.translationID DESC ");
        q.setParameter("id", id);
        List l = q.getResultList();

        for (Object o : l) {
            s.add((Translation) o);
        }

        return s;
    }

    /**
     * Given a list of translations, for each translation, get the number of
     * vote for.
     *
     * @param t List of translations
     * @return List if Double - The double i is the number of votes for the
     * translation ti
     */
    @Override
    public ArrayList<Double> votesFor(ArrayList<Translation> t
    ) {
        ArrayList<Double> v = new ArrayList<Double>();
        VoteManager m = VoteManagerImpl.getInstance();

        for (int i = 0; i < t.size(); i++) {
            int sid =t.get(i).getSentenceID().getSentenceID(); 
            int tid = t.get(i).getTranslationID();
            double n = m.nbWeightedVotesFor(sid, tid);
            v.add(n);
        }
        return v;
    }

    /**
     * Given a list of translations, for each translation, get the number of
     * vote against.
     *
     * @param t List of translations
     * @return List if Double - The double i is the number of votes against the
     * translation ti
     */
    @Override
    public ArrayList<Double> votesAgainst(ArrayList<Translation> t
    ) {
        ArrayList<Double> v = new ArrayList<Double>();
        VoteManager m = VoteManagerImpl.getInstance();

        for (int i = 0; i < t.size(); i++) {
            double n = m.nbWeightedVotesAgainst(t.get(i).getSentenceID().getSentenceID(), t.get(i).getTranslationID());
            v.add(n);
        }
        return v;
    }

    /**
     * Given a list of sentences, get the list of topics of each sentence
     *
     * @param s List of sentences
     * @return List of list of topics
     */
    @Override
    public ArrayList<ArrayList<Topic>> getTopics(ArrayList<Sentence> s
    ) {
        EntityManager em = emf.createEntityManager();

        ArrayList<ArrayList<Topic>> top = new ArrayList<ArrayList<Topic>>();

        for (Sentence sent : s) {
            ArrayList<Topic> t = new ArrayList<Topic>();

            Query q = em.createQuery("SELECT t.topic FROM Islinkto t WHERE (t.islinktoPK.sentenceID=:id )");
            q.setParameter("id", sent.getSentenceID());
            List l = q.getResultList();

            for (Object o : l) {
                t.add((Topic) o);
            }

            top.add(t);
        }
        return (top);
    }

    /**
     * Given a list of sentences, get the list of translations of each sentence
     *
     * @param s List of sentences
     * @return List of list of translations
     *
     */
    @Override
    public ArrayList<ArrayList<Translation>> getTranslations(ArrayList<Sentence> s) {
        EntityManager em = emf.createEntityManager();

        ArrayList<ArrayList<Translation>> top = new ArrayList<ArrayList<Translation>>();

        for (Sentence sent : s) {
            ArrayList<Translation> t = new ArrayList<Translation>();

            Query q = em.createQuery("SELECT t FROM Translation t WHERE (t.sentenceID=:id)");
            q.setParameter("id", sent);
            List l = q.getResultList();

            for (Object o : l) {
                t.add((Translation) o);
            }

            top.add(t);
        }
        return (top);
    }

    /**
     * Insert a translation in the database
     *
     * @param sentenceID Sentence ID
     * @param tr Translation
     * @param playerID Player ID
     * @return Boolean equal to true if the translation was not already in the
     * database
     */
    @Override
    public boolean registerTranslation(int sentenceID, String tr, int playerID) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT t FROM Translation t WHERE (t.sentenceID=:sid AND t.player=:pid)");
        q.setParameter("sid", sentenceID);
        q.setParameter("pid", playerID);
        List l = q.getResultList();

        if (l.isEmpty()) {
            Translation t = new Translation();
            t.setSentenceID(em.find(Sentence.class, sentenceID));
            t.setPlayer(playerID);
            t.setTranslationBody(tr);

            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }
        return l.isEmpty();
    }

    /**
     * Given a list of translations, retrieve the sentences associated
     *
     * @param tr List of translations
     * @return List of sentences
     */
    @Override
    public ArrayList<Sentence> mySubmissionsSentences(ArrayList<Translation> tr) {
        ArrayList<Sentence> s = new ArrayList<Sentence>();
        EntityManager em = emf.createEntityManager();
        for (Translation t : tr) {
            Sentence sent = em.find(Sentence.class, t.getSentenceID().getSentenceID());
            s.add(sent);
        }

        return s;
    }

    /**
     * Given a sentence, get all the translations for this sentence
     *
     * @param s Sentence
     * @return List of translations
     */
    @Override
    public ArrayList<Translation> getTranslationsSentence(Sentence s) {
        ArrayList<Translation> t = new ArrayList<Translation>();

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT t FROM Translation t WHERE (t.sentenceID=:id)");
        q.setParameter("id", s);
        List l = q.getResultList();

        for (Object o : l) {
            t.add((Translation) o);
        }
        return t;
    }

    /**
     * Insert a new sentence in the database with his list of topics
     *
     * @param s Sentence
     * @param t Translation
     * @param id Player ID
     * @param l1 Sentence language
     * @param l2 Translation language
     * @param topics List of topics
     * @param w List of integer (a weight for each topic)
     * @param isregistered Boolean : true is the sentence is already in the
     * database (register only the translation) and false otherwise
     */
    @Override
    public void registerSentence(String s, String t, int id, String l1, String l2, ArrayList<String> topics, ArrayList<Integer> w, boolean isregistered) {
        EntityManager em = emf.createEntityManager();

        //Register the sentence
        Sentence sent = new Sentence();
        sent.setSentenceBody(s);
        sent.setSentenceLanguage(l1);
        sent.setSentenceValidation(Boolean.FALSE);
        sent.setTranslationLanguage(l2);
        sent.setSentenceDate(Date.getDate());

        Query q1 = em.createQuery("SELECT s FROM Sentence s WHERE (s.sentenceBody LIKE :body AND s.sentenceLanguage=:l1 AND s.translationLanguage=:l2 )");
        q1.setParameter("l1", l1);
        q1.setParameter("l2", l2);
        q1.setParameter("body", s);

        List list = q1.getResultList();

        int sentID;
        if (list.isEmpty() && !isregistered) {
            em.getTransaction().begin();
            em.persist(sent);
            em.flush();
            sentID = sent.getSentenceID();
            em.getTransaction().commit();
        } else {
            sentID = ((Sentence) list.get(0)).getSentenceID();
        }

        //Save the topics
        ArrayList<Topic> topics1 = new ArrayList<Topic>();
        for (String top : topics) {
            String top1 = top.toUpperCase();

            Query q = em.createQuery("SELECT t FROM Topic t WHERE (t.topicName:=n)");
            q.setParameter("n", top1);
            List l = q.getResultList();

            if (l.isEmpty()) {
                Topic topic = new Topic();
                topic.setTopicName(top1);
                em.getTransaction().begin();
                em.persist(topic);
                em.flush();
                em.getTransaction().commit();
                topics1.add(topic);
            } else {
                topics1.add((Topic) l.get(0));
            }
        }

        //Create link between the sentence and the topics
        for (Topic top : topics1) {
            Query q = em.createQuery("SELECT l FROM Islinkto l WHERE (l.islinktoPK.sentenceID=:sid AND l.islinktoPK.topicID=:tid)");
            q.setParameter("sid", sentID);
            q.setParameter("tid", top.getTopicID());
            List l = q.getResultList();

            if (l.isEmpty()) {
                Islinkto link = new Islinkto();
                IslinktoPK pk = new IslinktoPK();
                pk.setSentenceID(sentID);
                pk.setTopicID(top.getTopicID());
                link.setIslinktoPK(pk);
                link.setWeight(w.get(topics1.indexOf(top)));

                em.getTransaction().begin();
                em.persist(link);
                em.getTransaction().commit();
            }
        }

        //Register the translation
        Translation tr = new Translation();
        tr.setPlayer(id);
        tr.setSentenceID(em.find(Sentence.class, sentID));
        tr.setTranslationBody(t);
        em.getTransaction().begin();
        em.persist(tr);
        em.getTransaction().commit();
    }

    /**
     * Given a list of sentences and the topics of each sentences, get the
     * weigth of each topic for the sentence
     *
     * @param top List of lists of topics
     * @param sent List of sentences
     * @return List of lists of integer
     */
    @Override
    public ArrayList<ArrayList<Integer>> getWeight(ArrayList<ArrayList<Topic>> top, ArrayList<Sentence> sent) {
        EntityManager em = emf.createEntityManager();
        ArrayList<ArrayList<Integer>> w = new ArrayList<ArrayList<Integer>>();

        for (int i = 0; i < sent.size(); i++) {
            ArrayList<Topic> t = top.get(i);
            ArrayList<Integer> w1 = new ArrayList<Integer>();
            for (Topic to : t) {
                Query q = em.createQuery("SELECT l.weight FROM Islinkto l WHERE (l.islinktoPK.sentenceID=:sid AND l.islinktoPK.topicID=:tid)");
                q.setParameter("sid", sent.get(i).getSentenceID());
                q.setParameter("tid", to.getTopicID());
                List l = q.getResultList();
                w1.add((Integer) l.get(0));
            }
            w.add(w1);
        }
        return w;
    }

    /**
     * Given a sentence and the topics , get the weigth of each topic for the
     * sentence
     *
     * @param topics List of topics
     * @param s Sentence
     * @return List of doubles
     */
    @Override
    public ArrayList<Integer> getWeightSentence(ArrayList<Topic> topics, Sentence s) {
        EntityManager em = emf.createEntityManager();

        ArrayList<Integer> w = new ArrayList<Integer>();

        for (Topic to : topics) {
            Query q = em.createQuery("SELECT l.weight FROM Islinkto l WHERE (l.islinktoPK.sentenceID=:sid AND l.islinktoPK.topicID=:tid)");
            q.setParameter("sid", s.getSentenceID());
            q.setParameter("tid", to.getTopicID());
            List l = q.getResultList();
            w.add((Integer) l.get(0));
        }
        return w;
    }

    /**
     * Test is a sentence is already in the database or not
     *
     * @param s Sentence
     * @param id Player ID
     * @param l1 language of the sentence
     * @param l2 language of the translation
     * @return Boolean
     */
    @Override
    public boolean isregisterSentence(String s, int id, String l1, String l2) {
        EntityManager em = emf.createEntityManager();

        Query q1 = em.createQuery("SELECT s FROM Sentence s WHERE (s.sentenceBody LIKE :body AND s.sentenceLanguage=:l1 AND s.translationLanguage=:l2 )");

        q1.setParameter("l1", l1);
        q1.setParameter("l2", l2);
        q1.setParameter("body", s);

        List list = q1.getResultList();

        if (list.isEmpty()) {
            return false;
        } else {
            int sid = ((Sentence) list.get(0)).getSentenceID();

            Query q = em.createQuery("SELECT t FROM Translation t WHERE (t.sentenceID.sentenceID=:sid AND t.player=:pid )");
            q.setParameter("sid", sid);
            q.setParameter("pid", id);
            List l = q.getResultList();
            return !l.isEmpty();
        }
    }

    public ArrayList<Translation> getTranslationsApp(ArrayList<Sentence> s) {
        ArrayList<Translation> tr = new ArrayList<Translation>();
        EntityManager em = emf.createEntityManager();
        for (Sentence se : s) {
            Translation t = em.find(Translation.class, se.getTranslationApproved());
            tr.add(t);
        }
        return tr;
    }

}
