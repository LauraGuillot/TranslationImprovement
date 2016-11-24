/**
 * ********************************************************************
 * Class PlayerManagementImpl
 * Management of the players and the leaderboards
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.tables.Connect;
import org.tables.Player;


public class PlayerManagerImpl implements PlayerManager {

    private EntityManagerFactory emf;
    private static PlayerManagerImpl theUserManager;

    private PlayerManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("translationPU");
        }
    }

    public static PlayerManager getInstance() {
        if (theUserManager == null) {
            theUserManager = new PlayerManagerImpl();
        }
        return theUserManager;
    }

    /**
     * Retrieve all the players
     *
     * @return Collection of players
     */
    @Override
    public Collection<Player> page() {
        EntityManager em = emf.createEntityManager();
        Query queryProductsByName = em.createNamedQuery("Player.findAll", Player.class);
        Collection l = queryProductsByName.getResultList();
        return l;
    }

    /**
     * Insert a new player in the database given his name
     *
     * @param n Name of the player
     * @return Id of the entity created
     */
    @Override
    public int add(String n) {
        EntityManager em = emf.createEntityManager();

        if (!isIN(n)) {
            em.getTransaction().begin();
            Player p = new Player(n);
            p.setPlayerConfidenceScore(1.0);
            em.persist(p);
            em.getTransaction().commit();
        }

        Query q1 = em.createQuery("SELECT p.playerID FROM Player p WHERE p.playerName =:n");
        q1.setParameter("n", n);
        List l1 = q1.getResultList();
        return (Integer) l1.get(0);
    }

    /**
     * Create a new connection in the database when a player signs in
     *
     * @param p Player
     * @return Connect ID
     */
    @Override
    public String connect(Player p) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT c FROM Connect c WHERE c.playerID =:playerID");
        q.setParameter("playerID", p);
        List l = q.getResultList();

        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        String d = formater.format(date);

        //If the player is not signed in, we create a new connection
        //Else, we update his connection
        if (l.isEmpty()) {
            String id = Connect.genereID();
            Connect c = new Connect(id, d, p);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return id;
        } else {
            Connect c = em.find(Connect.class, ((Connect) l.get(0)).getConnectID());
            updateConnect(c);
            return ((Connect) l.get(0)).getConnectID();
        }
    }

    /**
     * Update the connection of a player
     *
     * @param c Connect ID
     */
    @Override
    public void updateConnect(Connect c) {
        EntityManager em = emf.createEntityManager();

        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        String d = formater.format(date);
        c.setLastAction(d);

        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }

    /**
     * Find a player given his connect ID
     *
     * @param id Connect ID
     * @return Player
     */
    @Override
    public Player getByConnectID(String id) {
        EntityManager em = emf.createEntityManager();
        Connect c = em.find(Connect.class, id);
        Player p = em.find(Player.class, c.getPlayerID().getPlayerID());
        return p;
    }

    /**
     * Get the rank of a player based on his confidence score
     *
     * @param p Player
     * @return Int - Rank of the player
     */
    @Override
    public int getRank(Player p) {
        int r = 0;

        double score = p.getPlayerConfidenceScore();

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT p FROM Player p WHERE (p.playerConfidenceScore >:s)");
        q.setParameter("s", score);
        List l = q.getResultList();

        r = l.size() + 1;
        return r;
    }

    /**
     * Get the rank of a player based on his points
     *
     * @param p Player
     * @return Int - Rank of the player
     */
    @Override
    public int getRankPoints(Player p) {
        int r = 0;

        double score = p.getPlayerPoints();

        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT p FROM Player p WHERE (p.playerPoints >:s)");
        q.setParameter("s", score);
        List l = q.getResultList();

        r = l.size() + 1;
        return r;
    }

    /**
     * Add points to a players
     *
     * @param p Players
     * @param n Points to add
     */
    @Override
    public void addPoints(Player p, int n) {
        int pts = p.getPlayerPoints();
        p.setPlayerPoints(pts + n);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();

    }

    /**
     * retrieve the list of the players ranked by their points
     *
     * @return List of players
     */
    @Override
    public List<Player> leaderboardPoint() {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT p FROM Player p ORDER BY p.playerPoints DESC");
        List l = q.getResultList();

        return l;
    }

    /**
     * retrieve the list of the players ranked by their confidence score
     *
     * @return List of players
     */
    @Override
    public List<Player> leaderboardScore() {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM Player p ORDER BY p.playerConfidenceScore DESC");
        List l = q.getResultList();

        return l;
    }

    /**
     * Get a player given his ID
     *
     * @param id Player ID
     * @return Player
     */
    @Override
    public Player getByPlayerId(int id) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT p FROM Player p WHERE(p.playerID=:id)");
        q.setParameter("id", id);
        List l = q.getResultList();

        return (Player) l.get(0);
    }

    /**
     * Check if a player is registered or not
     *
     * @param name Name of the player
     * @return Boolean telling if the name is already in the database or not
     */
    @Override
    public boolean isIN(String name) {
        EntityManager em = emf.createEntityManager();
        Query q = em.createNamedQuery("Player.findByPlayerName", Player.class);
        q.setParameter("playerName", name);
        Collection l = q.getResultList();

        return !l.isEmpty();
    }

    /**
     * Given a list of players ranked by their points, retrieve a list of
     * integer in which each the integer i is the rank of the player i
     * considering equality
     *
     * @param p List of players
     * @return List of rank
     */
    @Override
    public ArrayList<Integer> rankPoint(List<Player> p) {
        ArrayList<Integer> l = new ArrayList<Integer>();
        for (Player pl : p) {
            l.add(this.getRankPoints(pl));
        }
        return l;
    }

    /**
     * Given a list of players ranked by their confidence scores, retrieve a
     * list of integer in which each the integer i is the rank of the player i
     * considering equality
     *
     * @param p List of players
     * @return List of rank
     */
    @Override
    public ArrayList<Integer> rankCS(List<Player> p) {
        ArrayList<Integer> l = new ArrayList<Integer>();
        for (Player pl : p) {
            l.add(this.getRank(pl));
        }
        return l;
    }
}
