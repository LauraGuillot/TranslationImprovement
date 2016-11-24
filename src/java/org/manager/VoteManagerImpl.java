/**
 * ********************************************************************
 * Class VoteManagerImpl
 * Management of the votes
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.tables.Player;
import org.tables.Sentence;
import org.tables.Translation;
import org.tables.Vote;
import org.tables.VotePK;

/**
 *
 * @author Laura
 */
public class VoteManagerImpl implements VoteManager {

    private EntityManagerFactory emf;
    private static VoteManagerImpl theVoteManager;

    private VoteManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("translationPU");
        }
    }

    public static VoteManager getInstance() {
        if (theVoteManager == null) {
            theVoteManager = new VoteManagerImpl();
        }
        return theVoteManager;
    }

    /**
     * Insert a new vote
     *
     * @param playerID Player ID
     * @param sentenceID SentenceID
     * @param vote Translation ID
     */
    @Override
    public void registerVote(int playerID, int sentenceID, int vote) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT v FROM Vote v WHERE v.player.playerID =:id AND v.sentence.sentenceID=:sid");
        q.setParameter("id", playerID);
        q.setParameter("sid", sentenceID);
        List l = q.getResultList();

        if (l.isEmpty()) {
            String format = "dd/MM/yy H:mm:ss";
            java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
            java.util.Date date = new java.util.Date();
            String d = formater.format(date);

            Vote v = new Vote(playerID, sentenceID, vote, d);

            em.getTransaction().begin();
            em.persist(v);
            em.getTransaction().commit();
        }
    }

    /**
     * Get the number of votes made by the player
     *
     * @param playerID player ID
     * @return Number of votes
     */
    @Override
    public int nbVoteByPlayer(int playerID) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Vote.findByPlayerID", Vote.class);
        q.setParameter("playerID", playerID);
        List l = q.getResultList();
        int n = l.size();
        return n;
    }

    /**
     * Get the number of votes made by the player for a translation approved
     *
     * @param PlayerID player ID
     * @return Number of votes for a translation approved
     */
    @Override
    public int nbVoteApprovedByPlayer(int PlayerID) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Vote v WHERE v.player.playerID =:id");
        q.setParameter("id", PlayerID);
        List l = q.getResultList();

        int n = 0;

        for (int i = 0; i < l.size(); i++) {

            Vote v = ((Vote) l.get(i));
            int sid = v.getVotePK().getSentenceID();
            Sentence s = em.find(Sentence.class, sid);
            int vote = ((Vote) l.get(i)).getVoteFor();

            if (s.getSentenceValidation() && s.getTranslationApproved() == vote) {
                n++;
            }
        }
        return n;
    }

    /**
     * Get the number of votes for a translation
     *
     * @param sentenceID Sentence ID
     * @param vote Translation ID
     * @return
     */
    @Override
    public double nbWeightedVotesFor(int sentenceID, int vote) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v.player.playerID FROM Vote v WHERE (v.sentence.sentenceID =:id AND v.voteFor=:vote)");
        q.setParameter("id", sentenceID);
        q.setParameter("vote", vote);
        List l = q.getResultList();

        double n = 0;

        for (int i = 0; i < l.size(); i++) {
            Player p = em.find(Player.class, (Integer) l.get(0));
            n = n + p.getPlayerConfidenceScore();
        }
        return n;
    }

    /**
     * Get the number of votes against a translation
     *
     * @param sentenceID Sentence ID
     * @param vote Translation ID
     * @return
     */
    @Override
    public double nbWeightedVotesAgainst(int sentenceID, int vote) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v.player.playerID FROM Vote v WHERE (v.sentence.sentenceID =:id)");
        q.setParameter("id", sentenceID);
        List l = q.getResultList();

        double n = 0.0;

        for (int i = 0; i < l.size(); i++) {
            Player p = em.find(Player.class, (Integer) l.get(0));
            n = n + p.getPlayerConfidenceScore();
        }
        return n - nbWeightedVotesFor(sentenceID, vote);
    }

    /**
     * Get the number of votes for a sentence
     *
     * @param sentenceID Sentence ID
     * @return
     */
    @Override
    public int nbVotesForSentence(int sentenceID) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v.player.playerID FROM Vote v WHERE (v.sentence.sentenceID =:id )");
        q.setParameter("id", sentenceID);
        List l = q.getResultList();
        return l.size();
    }

    /**
     * Compute the relevance of a translation
     *
     * @param sentenceID Sentence ID
     * @param vote Translation ID
     * @return Double relevance
     */
    @Override
    public double relevancy(int sentenceID, int vote) {
        double r;

        if ((nbWeightedVotesFor(sentenceID, vote) + nbWeightedVotesAgainst(sentenceID, vote)) != 0) {

            r = ((nbWeightedVotesFor(sentenceID, vote) - nbWeightedVotesAgainst(sentenceID, vote))) / ((nbWeightedVotesFor(sentenceID, vote) + nbWeightedVotesAgainst(sentenceID, vote)));
        } else {
            r = 0.0;
        }
        return r;
    }

    /**
     * Compute the confidence score of a player
     *
     * @param playerID Player ID
     * @return Confidence score
     */
    @Override
    public double calculConfidenceScore(int playerID) {
        double n = 0;
        SentenceManager m = SentenceManagerImpl.getInstance();

        if (nbVoteByPlayer(playerID) != 0) {
            n = n + ((double) (nbVoteApprovedByPlayer(playerID)) / ((double) nbVoteByPlayer(playerID)));
        }
        if (m.nbSubmissionsByPlayer(playerID) != 0) {
            n = n + ((double) m.nbSubmissionsApprovedByPlayer(playerID) / (double) m.nbSubmissionsByPlayer(playerID));
        }
        n = n + 1;
        return n * getlevel(playerID);
    }

    /**
     * Update the confidence score of a player
     *
     * @param playerID Player ID
     */
    @Override
    public void updateConfidenceScore(int playerID) {
        double n = calculConfidenceScore(playerID);
        EntityManager em = emf.createEntityManager();

        Player p = em.find(Player.class, playerID);

        if (n != 0) {
            int temp = (int) (n * 100);
            n = (double) temp / 100.0;
        }
        p.setPlayerConfidenceScore(n);

        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();

    }

    /**
     * Test and approvement of a translation
     *
     * @param sentenceID Sentence ID
     * @param translationID Translation ID
     */
    @Override
    public void approveTranslation(int sentenceID, int translationID) {
        Double r = relevancy(sentenceID, translationID);

        int nb = nbVotesForSentence(sentenceID);

        Double limit = 0.8;
        int nblimit = 30;

        EntityManager em = emf.createEntityManager();
        Sentence s = em.find(Sentence.class, sentenceID);

        if (r > limit && nb >= nblimit) {
            s.setTranslationApproved(translationID);
            s.setSentenceValidation(Boolean.TRUE);
            em.getTransaction().begin();
            em.merge(s);
            em.getTransaction().commit();
        }

    }

    /**
     * Update the confidence scores of all the player and check the translation
     * approvements
     */
    @Override
    public void updateScoresAndTranslations() {
        EntityManager em = emf.createEntityManager();

        Query q = em.createNamedQuery("Sentence.findAll", Sentence.class);
        List l = q.getResultList();

        for (Object l1 : l) {

            q = em.createQuery("SELECT t FROM Translation t WHERE (t.sentenceID =:id )");
            q.setParameter("id", ((Sentence) l1));
            List tr = q.getResultList();

            for (Object t : tr) {
                approveTranslation(((Sentence) l1).getSentenceID(), ((Translation) t).getTranslationID());
            }
        }

        Query q1 = em.createNamedQuery("Player.findAll", Player.class);
        List l2 = q1.getResultList();

        for (Object l3 : l2) {
            updateConfidenceScore(((Player) l3).getPlayerID());
        }
    }

    /**
     * Test if a sentence is registered or not
     *
     * @param playerID Player ID
     * @param sentenceID Sentence ID
     * @return Boolean
     */
    @Override
    public boolean isRegistered(int playerID, int sentenceID) {
        VotePK vpk = new VotePK(playerID, sentenceID);
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT v FROM Vote v WHERE (v.votePK =:vpk )");
        q.setParameter("vpk", vpk);
        List l = q.getResultList();

        return !l.isEmpty();
    }

    /**
     * Test is a vote is the same as the majority
     *
     * @param sentenceID Sentence ID
     * @param vote Translation ID
     * @return Boolean
     */
    @Override
    public boolean isEqualToMajority(int sentenceID, int vote) {
        double ns = nbWeightedVotesAgainst(sentenceID, vote);
        double nu = nbWeightedVotesFor(sentenceID, vote);
        boolean b = (nu >= ns);
        return b;
    }

    /**
     * Get the level of a player
     *
     * @param id Player ID
     * @return Level
     */
    public int getlevel(int id) {
        int n = 0;

        EntityManager em = emf.createEntityManager();
        Player p = em.find(Player.class, id);

        int pts = p.getPlayerPoints();

        if (pts < 100) {
            n = 1;
        }
        if (pts >= 100 && pts < 200) {
            n = 2;
        }
        if (pts >= 200 && pts < 500) {
            n = 3;
        }
        if (pts >= 500 && pts < 1000) {
            n = 4;
        }
        if (pts >= 1000) {
            n = 5;
        }

        return n;
    }

}
