/**
 * ********************************************************************
 * Class StatManagerImpl
 * Management of the statistics
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
import org.tables.Player;
import org.tables.Sentence;
import org.tables.Translation;
import org.utils.StatSentence;

public class StatManagerImpl implements StatManager {

    private EntityManagerFactory emf;
    private static StatManagerImpl theStatManager;

    private StatManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("translationPU");
        }
    }

    public static StatManager getInstance() {
        if (theStatManager == null) {
            theStatManager = new StatManagerImpl();
        }
        return theStatManager;
    }

    /**
     * Get the number of players
     *
     * @return Number of players
     */
    @Override
    public int nbPlayers() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM Player p");
        List l = q.getResultList();
        return l.size();
    }

    /**
     * Get the number of votes
     *
     * @return Number of votes
     */
    @Override
    public int nbvotes() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Vote v");
        List l = q.getResultList();
        return l.size();
    }

    /**
     * Get the number of validated sentences
     *
     * @return number of validated sentences
     */
    @Override
    public int NbValidatedSent() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT s FROM Sentence s WHERE (s.sentenceValidation=TRUE)");
        List l = q.getResultList();
        return l.size();
    }

    /**
     * Get the number of sentences available for the vote
     *
     * @return number of sentences available for the vote
     */
    @Override
    public int nbInProgressSent() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT s FROM Sentence s WHERE (s.sentenceValidation!=TRUE)");
        List l = q.getResultList();
        return l.size();
    }

    /**
     * Get the number of submissions
     *
     * @return number of submissions
     */
    @Override
    public int nbSub() {
        EntityManager em = emf.createEntityManager();
        int cpt = 0;

        Query q = em.createQuery("SELECT s FROM Sentence s");
        List l = q.getResultList();

        for (Object o : l) {
            Sentence s = (Sentence) o;
            Query q1 = em.createQuery("SELECT t FROM Translation t WHERE t.sentenceID=:id");
            q1.setParameter("id", s);
            List l1 = q1.getResultList();
            cpt = cpt + l1.size() - 1;
        }
        return cpt;
    }

    /**
     * Get the list of the approved sentences and the associated translations
     *
     * @return List of StatSentence (StatSentence is a class of utils which
     * enables to keep the useful data)
     */
    @Override
    public ArrayList<StatSentence> getApprovedSentences() {
        ArrayList<StatSentence> stat = new ArrayList<StatSentence>();
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT s FROM Sentence s WHERE (s.sentenceValidation=TRUE) ORDER BY (s.sentenceLanguage,s.translationLanguage)");
        List l = q.getResultList();

        for (Object o : l) {
            Sentence s = (Sentence) o;

            String body = s.getSentenceBody();
            String l1 = s.getSentenceLanguage();
            String l2 = s.getTranslationLanguage();
     
            Translation t = em.find(Translation.class, s.getTranslationApproved());
            String tr = t.getTranslationBody();

            String name;
            if (t.getPlayer() == 0) {
                name = "Translator";
            } else {
                Player p = em.find(Player.class, t.getPlayer());
                name = p.getPlayerName();
            }

            StatSentence sent = new StatSentence(l1, l2, body, name, tr);
            stat.add(sent);
        }

        return stat;
    }

}
