/**
 * ********************************************************************
 * Class TrophyManagerImpl
 * Management of the trophies
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
import org.utils.*;

public class TrophyManagerImpl implements TrophyManager {

    private EntityManagerFactory emf;
    private static TrophyManagerImpl theTrophyManager;

    private TrophyManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("translationPU");
        }
    }

    public static TrophyManager getInstance() {
        if (theTrophyManager == null) {
            theTrophyManager = new TrophyManagerImpl();
        }
        return theTrophyManager;
    }

    /**
     * Get the state (won or not) of each trophy of a player
     *
     * @param id Player ID
     * @return List of integer
     */
    @Override
    public ArrayList<Integer> getMyTrophies(int id) {
        ArrayList<Integer> t = new ArrayList<Integer>();

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT t FROM  Wintrophy t WHERE(t.wintrophyPK.playerID=:id) ORDER BY t.wintrophyPK.trophyID");
        q.setParameter("id", id);
        List l = q.getResultList();

        for (Object i : l) {
            t.add(((Wintrophy) i).getTrophyState());
        }
        return t;
    }

    /**
     * Create 9 trophies for a player. (when he signs in for the first time)
     *
     * @param id Player ID
     */
    @Override
    public void addNewTrophies(int id) {

        ArrayList<Integer> l = this.getMyTrophies(id);

        if (l.isEmpty()) {

            Wintrophy t1 = new Wintrophy(1, id);
            t1.setTrophyStateBody(0);
            Wintrophy t2 = new Wintrophy(2, id);
            t2.setTrophyStateBody(0);
            Wintrophy t3 = new Wintrophy(3, id);
            t3.setTrophyStateBody(0);
            Wintrophy t4 = new Wintrophy(4, id);
            t4.setTrophyStateBody(0);
            Wintrophy t5 = new Wintrophy(5, id);
            t5.setTrophyStateBody(0);
            Wintrophy t6 = new Wintrophy(6, id);
            t6.setTrophyStateBody(0);
            Wintrophy t7 = new Wintrophy(7, id);
            t7.setTrophyStateBody(0);
            Wintrophy t8 = new Wintrophy(8, id);
            t8.setTrophyStateBody(0);
            Wintrophy t9 = new Wintrophy(9, id);
            t9.setTrophyStateBody(0);

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(t1);
            em.persist(t1);
            em.persist(t2);
            em.persist(t3);
            em.persist(t4);
            em.persist(t5);
            em.persist(t6);
            em.persist(t7);
            em.persist(t8);
            em.persist(t9);
            em.getTransaction().commit();
        }
    }

    /**
     * Test if a player is in the top n
     *
     * @param n Top
     * @param id Player ID
     * @return Boolean
     */
    @Override
    public boolean isInTop(int n, int id) {
        boolean b;

        EntityManager em = emf.createEntityManager();

        Player p = em.find(Player.class, id);

        Query q = em.createQuery("SELECT p FROM  Player p WHERE(p.playerPoints>:sc)");
        q.setParameter("sc", p.getPlayerPoints());
        List l = q.getResultList();

        b = (l.size() < n);
        return b;
    }

    /**
     * Update the trophy 4,5,6 of the player when he has made evaluations
     *
     * @param n Number of evaluations made
     * @param id Player ID
     */
    @Override
    public void incrNbEval(int n, int id) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT t FROM Wintrophy t WHERE(t.wintrophyPK.playerID=:id AND t.wintrophyPK.trophyID=4)");
        q.setParameter("id", id);
        List l = q.getResultList();
        Wintrophy w = (Wintrophy) l.get(0);
        w.setTrophyStateBody(w.getTrophyStateBody() + n);

        if (w.getTrophyStateBody() >= 50 && w.getTrophyState() == 0) {
            w.setTrophyState(1);
            w.setTrophyDate(Date.getDate());
        }
        em.getTransaction().begin();
        em.merge(w);
        em.getTransaction().commit();

        q = em.createQuery("SELECT t FROM Wintrophy t WHERE(t.wintrophyPK.playerID=:id AND t.wintrophyPK.trophyID=5)");
        q.setParameter("id", id);
        l = q.getResultList();
        w = (Wintrophy) l.get(0);
        w.setTrophyStateBody(w.getTrophyStateBody() + n);
        if (w.getTrophyStateBody() >= 100 && w.getTrophyState() == 0) {
            w.setTrophyState(1);
            w.setTrophyDate(Date.getDate());
        }
        em.getTransaction().begin();
        em.merge(w);
        em.getTransaction().commit();

        q = em.createQuery("SELECT t FROM Wintrophy t WHERE(t.wintrophyPK.playerID=:id AND t.wintrophyPK.trophyID=6)");
        q.setParameter("id", id);
        l = q.getResultList();
        w = (Wintrophy) l.get(0);
        w.setTrophyStateBody(w.getTrophyStateBody() + n);
        if (w.getTrophyStateBody() >= 500 && w.getTrophyState() == 0) {
            w.setTrophyState(1);
            w.setTrophyDate(Date.getDate());
        }
        em.getTransaction().begin();
        em.merge(w);
        em.getTransaction().commit();

    }

    /**
     * Update the trophy 7,8,9 of the player when he has made submissions
     *
     * @param n Number of submissions made
     * @param id Player ID
     */
    @Override
    public void incrNbSub(int n, int id) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT t FROM Wintrophy t WHERE(t.wintrophyPK.playerID=:id AND t.wintrophyPK.trophyID=7)");
        q.setParameter("id", id);
        List l = q.getResultList();
        Wintrophy w = (Wintrophy) l.get(0);
        w.setTrophyStateBody(w.getTrophyStateBody() + n);
        if (w.getTrophyStateBody() >= 10 && w.getTrophyState() == 0) {
            w.setTrophyState(1);
            w.setTrophyDate(Date.getDate());
        }
        em.getTransaction().begin();
        em.merge(w);
        em.getTransaction().commit();

        q = em.createQuery("SELECT t FROM Wintrophy t WHERE(t.wintrophyPK.playerID=:id AND t.wintrophyPK.trophyID=8)");
        q.setParameter("id", id);
        l = q.getResultList();
        w = (Wintrophy) l.get(0);
        w.setTrophyStateBody(w.getTrophyStateBody() + n);
        if (w.getTrophyStateBody() >= 25 && w.getTrophyState() == 0) {
            w.setTrophyState(1);
            w.setTrophyDate(Date.getDate());
        }
        em.getTransaction().begin();
        em.merge(w);
        em.getTransaction().commit();

        q = em.createQuery("SELECT t FROM Wintrophy t WHERE(t.wintrophyPK.playerID=:id AND t.wintrophyPK.trophyID=9)");
        q.setParameter("id", id);
        l = q.getResultList();
        w = (Wintrophy) l.get(0);
        w.setTrophyStateBody(w.getTrophyStateBody() + n);
        if (w.getTrophyStateBody() >= 50 && w.getTrophyState() == 0) {
            w.setTrophyState(1);
            w.setTrophyDate(Date.getDate());
        }
        em.getTransaction().begin();
        em.merge(w);
        em.getTransaction().commit();

    }

    /**
     * Update the trophies 1,2,3 of a player
     *
     * @param id Player ID
     */
    @Override
    public void updateTrophies123(int id) {
        EntityManager em = emf.createEntityManager();

        if (this.isInTop(50, id)) {
            Query q = em.createQuery("SELECT t FROM Wintrophy t WHERE(t.wintrophyPK.playerID=:id AND t.wintrophyPK.trophyID=1)");
            q.setParameter("id", id);
            List l = q.getResultList();
            Wintrophy w = (Wintrophy) l.get(0);
            if (w.getTrophyState() == 0) {
                w.setTrophyState(1);
                w.setTrophyDate(Date.getDate());
            }

            em.getTransaction().begin();
            em.merge(w);
            em.getTransaction().commit();
        }

        if (this.isInTop(20, id)) {
            Query q = em.createQuery("SELECT t FROM Wintrophy t WHERE(t.wintrophyPK.playerID=:id AND t.wintrophyPK.trophyID=2)");
            q.setParameter("id", id);
            List l = q.getResultList();
            Wintrophy w = (Wintrophy) l.get(0);
            if (w.getTrophyState() == 0) {
                w.setTrophyState(1);
                w.setTrophyDate(Date.getDate());
            }

            em.getTransaction().begin();
            em.merge(w);
            em.getTransaction().commit();
        }

        if (this.isInTop(5, id)) {
            Query q = em.createQuery("SELECT t FROM Wintrophy t WHERE(t.wintrophyPK.playerID=:id AND t.wintrophyPK.trophyID=3)");
            q.setParameter("id", id);
            List l = q.getResultList();
            Wintrophy w = (Wintrophy) l.get(0);
            if (w.getTrophyState() == 0) {
                w.setTrophyState(1);
                w.setTrophyDate(Date.getDate());
            }

            em.getTransaction().begin();
            em.merge(w);
            em.getTransaction().commit();
        }

    }

    /**
     * Update all the trophies of a player. It is called each time a user plays.
     *
     * @param id Player ID
     * @param nbeval Number of evaluations made
     * @param nbsub Number of submissions made
     */
    @Override
    public void updateTrophies(int id, int nbeval, int nbsub) {
        this.incrNbEval(nbeval, id);
        this.incrNbSub(nbsub, id);
        this.updateTrophies123(id);
    }

}
