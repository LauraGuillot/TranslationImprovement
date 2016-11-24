/**
 * ********************************************************************
 * Class MissionManagerImpl
 * Management of the players missions
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.util.ArrayList;
import org.utils.Date;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.tables.*;

public class MissionManagerImpl implements MissionManager {

    private EntityManagerFactory emf;
    private static MissionManagerImpl theMissionManager;

    private MissionManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("translationPU");
        }
    }

    public static MissionManager getInstance() {
        if (theMissionManager == null) {
            theMissionManager = new MissionManagerImpl();
        }
        return theMissionManager;
    }

    /**
     * Generate a new mission ID for the player. This ID must not already be in
     * the players missions
     *
     * @param playerID Player ID
     * @return Int (1<=n<=11)
     */
    @Override
    public int genereId(int playerID) {
        EntityManager em = emf.createEntityManager();
        Player p = em.find(Player.class, playerID);
        PlayerManager man = PlayerManagerImpl.getInstance();

        Query q = em.createQuery("SELECT h.hasmissionPK.missionID FROM Hasmission h WHERE h.hasmissionPK.playerID=:id");
        q.setParameter("id", playerID);
        List l = q.getResultList();

        Random rand = new Random();
        int n = rand.nextInt(11) + 1;

        boolean b = true;

        for (Object o : l) {
            int i = (Integer) o;
            if (i == n) {
                b = false;
            }
        }

        if (p.getPlayerPoints() >= 1000 && n == 6) {
            b = false;
        }

        if (man.getRank(p) <= 10 && n == 7) {
            b = false;
        }

        if (p.getPlayerConfidenceScore() >= 13 && n == 8) {
            b = false;
        }

        while (!b) {
            n = rand.nextInt(11) + 1;
            b = true;
            for (Object o : l) {
                int i = (Integer) o;
                if (i == n) {
                    b = false;
                }
            }
            if (p.getPlayerPoints() >= 1000 && n == 6) {
                b = false;
            }
            if (man.getRank(p) <= 10 && n == 7) {
                b = false;
            }
            if (p.getPlayerConfidenceScore() >= 13 && n == 8) {
                b = false;
            }
        }
        return n;
    }

    /**
     * Generate and register a new mission for a player
     *
     * @param playerID Player ID
     */
    @Override
    public void newMission(int playerID) {
        int m = genereId(playerID);
        EntityManager em = emf.createEntityManager();

        HasmissionPK pk = new HasmissionPK(m, playerID);
        Hasmission hm = new Hasmission(pk);
        hm.setMissionDate(Date.getDate());

        Player p = em.find(Player.class, playerID);
        PlayerManager man = PlayerManagerImpl.getInstance();
        SentenceManager sman = SentenceManagerImpl.getInstance();

        switch (m) {
            case 1:
                String s = sman.nbSubmissionsApprovedByPlayer(playerID) + "";
                hm.setMissionInfo(s);
                hm.setMissionState(0);
                hm.setMissionStateBody("0/10");
                break;
            case 2:
                hm.setMissionInfo("");
                hm.setMissionState(0);
                hm.setMissionStateBody("0/10");
                break;
            case 3:
                hm.setMissionInfo("");
                hm.setMissionState(0);
                hm.setMissionStateBody("0/20");
                break;
            case 4:
                hm.setMissionInfo("");
                hm.setMissionState(0);
                hm.setMissionStateBody("0/50");
                break;
            case 5:
                hm.setMissionStateBody("0/1");
                hm.setMissionState(0);
                Query q = em.createQuery("SELECT w.wintrophyPK.trophyID FROM Wintrophy w WHERE w.wintrophyPK.playerID=:id AND w.trophyState=1");
                q.setParameter("id", playerID);
                List l = q.getResultList();
                String s2 = "" + l.size();
                hm.setMissionInfo(s2);
                break;
            case 6:
                hm.setMissionInfo(p.getPlayerPoints() + "");
                hm.setMissionState(p.getPlayerPoints());
                hm.setMissionStateBody("0/1");
                break;
            case 7:
                hm.setMissionInfo(man.getRank(p) + "");
                hm.setMissionState(0);
                hm.setMissionStateBody("0/10");
                break;
            case 8:
                String s1 = p.getPlayerConfidenceScore() + "";
                hm.setMissionInfo(s1);
                hm.setMissionState(0);
                hm.setMissionStateBody("0/2");
                break;
            case 9:
                hm.setMissionInfo("");
                hm.setMissionState(0);
                hm.setMissionStateBody("0/1");
                break;
            case 10:
                hm.setMissionInfo("");
                hm.setMissionState(0);
                hm.setMissionStateBody("0/40");
                break;
            default:
                hm.setMissionInfo(Date.getDate());
                hm.setMissionState(1);
                hm.setMissionStateBody("1/4");
                break;
        }

        em.getTransaction().begin();
        em.persist(hm);
        em.getTransaction().commit();
    }

    /**
     * Update a mission state body with the current state of the mission
     *
     * @param s Old mission state body (a String like x/y)
     * @param n The new mission state
     * @return The new mission state body (n/y)
     */
    @Override
    public String newBody(String s, double n) {
        String s1;
        StringTokenizer st = new StringTokenizer(s, "/");
        st.nextToken();
        Integer a = (int) n;
        s1 = a + "/" + st.nextToken();
        return s1;
    }

    /**
     * Test is a mission is accomplished
     *
     * @param s A mission state body like x/y
     * @return Boolean telling if a mission is accomplished or not (x==y)
     */
    @Override
    public boolean success(String s) {
        StringTokenizer st = new StringTokenizer(s, "/");
        double a = Double.parseDouble(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        return a >= b;
    }

    /**
     * Updating a mission by calling the appropriate method depending of the
     * missionID
     *
     * @param m - mission
     */
    @Override
    public void updateMission(Hasmission m) {
        int id = m.getHasmissionPK().getMissionID();

        switch (id) {
            case 1:
                update1(m);
                break;
            case 2:
                update2(m);
                break;
            case 3:
                update3(m);
                break;
            case 4:
                update4(m);
                break;
            case 5:
                update5(m);
                break;
            case 6:
                update6(m);
                break;
            case 7:
                update7(m);
                break;
            case 8:
                update8(m);
                break;
            case 9:
                update9(m);
                break;
            case 10:
                update10(m);
                break;
            default:
                update11(m);
                break;
        }
    }

    /**
     * Update a mission with missionID=1
     *
     * @param m - Mission
     */
    @Override
    public void update1(Hasmission m) {
        EntityManager em = emf.createEntityManager();

        SentenceManager man = SentenceManagerImpl.getInstance();
        int n = man.nbSubmissionsApprovedByPlayer(m.getHasmissionPK().getPlayerID());
        int ni = Integer.parseInt(m.getMissionInfo());
        String s = this.newBody(m.getMissionStateBody(), n - ni);

        m.setMissionStateBody(s);
        m.setMissionState(n - ni);

        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();

    }

    /**
     * Update a mission with missionID=2 when submissions are made by the player
     *
     * @param m Mission
     * @param n Number of submissions made
     */
    @Override
    public void updateMissionSub(Hasmission m, int n) {
        if (m.getHasmissionPK().getMissionID() == 2) {
            m.setMissionState(m.getMissionState() + n);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
        }
    }

    /**
     * Update a mission with missionID=2
     *
     * @param m Mission
     */
    @Override
    public void update2(Hasmission m) {
        String s = this.newBody(m.getMissionStateBody(), m.getMissionState());
        m.setMissionStateBody(s);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
    }

    /**
     * Update a mission with missionID=4 or missionID=3 when evaluations are
     * made by the player
     *
     * @param m Mission
     * @param n Number of evaluations made
     */
    @Override
    public void updateMissionEval(Hasmission m, int n) {
        if (m.getHasmissionPK().getMissionID() == 4 || m.getHasmissionPK().getMissionID() == 3) {
            m.setMissionState(m.getMissionState() + n);
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
        }
    }

    /**
     * Update a mission with missionID=3
     *
     * @param m Mission
     */
    @Override
    public void update3(Hasmission m) {
        String s = this.newBody(m.getMissionStateBody(), m.getMissionState());
        m.setMissionStateBody(s);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
    }

    /**
     * Update a mission with missionID=4
     *
     * @param m Mission
     */
    @Override
    public void update4(Hasmission m) {
        String s = this.newBody(m.getMissionStateBody(), m.getMissionState());
        m.setMissionStateBody(s);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
    }

    /**
     * Update a mission with missionID=5
     *
     * @param m Mission
     */
    @Override
    public void update5(Hasmission m) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT w.wintrophyPK.trophyID FROM Wintrophy w WHERE w.wintrophyPK.playerID=:id AND w.trophyState=1");
        q.setParameter("id", m.getHasmissionPK().getPlayerID());
        List l = q.getResultList();

        int diff = l.size() - Integer.parseInt(m.getMissionInfo());
        String s = this.newBody(m.getMissionStateBody(), diff);
        m.setMissionStateBody(s);
        m.setMissionState(diff);

        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
    }

    /**
     * Update a mission with missionID=6
     *
     * @param m Mission
     */
    @Override
    public void update6(Hasmission m) {
        EntityManager em = emf.createEntityManager();

        Player p = em.find(Player.class, m.getHasmissionPK().getPlayerID());
        int ptsI = Integer.parseInt(m.getMissionInfo());
        int ptsF = p.getPlayerPoints();

        int pass = 0;

        if (ptsI < 100 && ptsF >= 100) {
            pass = 1;
        } else if (ptsI >= 100 && ptsI < 200 && ptsF >= 200) {
            pass = 1;
        } else if (ptsI >= 200 && ptsI < 500 && ptsF >= 500) {
            pass = 1;
        } else if (ptsI >= 500 && ptsI < 1000 && ptsF >= 1000) {
            pass = 1;
        }

        String s = this.newBody(m.getMissionStateBody(), pass);
        m.setMissionStateBody(s);
        m.setMissionState(pass);

        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();

    }

    /**
     * Update a mission with missionID=7
     *
     * @param m Mission
     */
    @Override
    public void update7(Hasmission m) {
        EntityManager em = emf.createEntityManager();
        PlayerManager man = PlayerManagerImpl.getInstance();
        Player p = em.find(Player.class, m.getHasmissionPK().getPlayerID());

        int rI = Integer.parseInt(m.getMissionInfo());
        int rF = man.getRank(p);
        int diff = rI - rF;
        if (diff < 0) {
            diff = 0;
        }

        String s = this.newBody(m.getMissionStateBody(), diff);
        m.setMissionStateBody(s);
        m.setMissionState(diff);

        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();

    }

    /**
     * Update a mission with missionID=8
     *
     * @param m Mission
     */
    @Override
    public void update8(Hasmission m) {
        EntityManager em = emf.createEntityManager();
        Player p = em.find(Player.class, m.getHasmissionPK().getPlayerID());

        double sI = Double.parseDouble(m.getMissionInfo());
        double sF = p.getPlayerConfidenceScore();
        double diff = sF - sI;
        if (diff < 0) {
            diff = 0;
        }

        String s = this.newBody(m.getMissionStateBody(), diff);
        m.setMissionStateBody(s);

        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
    }

    /**
     * Update a mission with missionID=9 when the player makes a score of 20 in
     * the challenge mode
     *
     * @param m Mission
     */
    @Override
    public void challenge20(Hasmission m) {
        if (m.getHasmissionPK().getMissionID() == 9) {
            m.setMissionStateBody("1/1");
            m.setMissionState(20);
        }
    }

    /**
     * Update a mission with missionID=9
     *
     * @param m Mission
     */
    @Override
    public void update9(Hasmission m) {
    }

    /**
     * Update a mission with missionID=10 when a player earns points
     *
     * @param m - Mission
     * @param pts - Points earned
     */
    @Override
    public void incrPtsMission(Hasmission m, int pts) {
        if (m.getHasmissionPK().getMissionID() == 10) {

            String auj = Date.getDate();
            String date = m.getMissionDate();

            StringTokenizer st = new StringTokenizer(auj, " ");
            auj = st.nextToken();

            StringTokenizer st1 = new StringTokenizer(date, " ");
            date = st1.nextToken();

            if (date.equals(auj)) {

                int temp = m.getMissionState() + pts;
                m.setMissionState(temp);
                m.setMissionStateBody(temp + "/40");
            } else {
                m.setMissionState(pts);
                m.setMissionStateBody(pts + "/40");
                m.setMissionDate(Date.getDate());
            }
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
        }
    }

    /**
     * Update a mission with missionID=10
     *
     * @param m Mission
     */
    @Override
    public void update10(Hasmission m) {

    }

    /**
     * Update a mission with missionID=11
     *
     * @param m Mission
     */
    @Override
    public void update11(Hasmission m) {
        EntityManager em = emf.createEntityManager();

        String auj = Date.getDate();
        String hier = m.getMissionInfo();
        int diff = Date.nbJour(hier, auj);

        if (diff > 1) {
            m.setMissionDate(auj);
            m.setMissionInfo(auj);
            m.setMissionState(1);
            m.setMissionStateBody("1/4");

            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();

        } else if (diff == 1) {
            m.setMissionInfo(auj);
            int n = m.getMissionState();
            n++;
            m.setMissionState(n);
            m.setMissionStateBody(n + "/4");

            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
        }
    }

    /**
     * Updating all the missions of a player. If a mission is achieved, generate
     * a new one. This method is called each time a user plays.
     *
     * @param id Player ID
     * @param eval Number of evaluations made
     * @param sub Number of submissions made
     * @param pts Number of points earned
     * @param challenge20 Boolean equals to true if the player has made a score
     * of 20 in the challenge mode
     * @return Array of achieved missions
     */
    @Override
    public ArrayList<String> updateMissionGlobal(int id, int eval, int sub, int pts, boolean challenge20) {
        EntityManager em = emf.createEntityManager();
        ArrayList<Hasmission> t = this.getMyMissions(id);
        ArrayList<String> win = new ArrayList<String>();

        for (Hasmission h : t) {

            this.incrPtsMission(h, pts);
            this.updateMissionEval(h, eval);
            this.updateMissionSub(h, sub);

            if (challenge20) {
                this.challenge20(h);
            }

            this.updateMission(h);

            Hasmission hh = em.find(Hasmission.class, h.getHasmissionPK());
            if (success(hh.getMissionStateBody())) {

                /*Recuperation de la mission reussie*/
                Mission m = em.find(Mission.class, hh.getHasmissionPK().getMissionID());
                String mi = m.getMissionBody();
                win.add(mi);

                /*Incrementation des points*/
                Player p = em.find(Player.class, h.getHasmissionPK().getPlayerID());
                p.setPlayerPoints(p.getPlayerPoints() + 15);
                em.getTransaction().begin();
                em.merge(p);
                em.getTransaction().commit();

                /*Suppression de la mission*/
                em.getTransaction().begin();
                em.remove(hh);
                em.getTransaction().commit();

                /*Ajout d'une nouvelle mission*/
                this.newMission(p.getPlayerID());
            }
        }
        return win;
    }

    /**
     * Retrieve the missions of a player
     *
     * @param id Player ID
     * @return Array of missions
     */
    @Override
    public ArrayList<Hasmission> getMyMissions(int id) {
        ArrayList<Hasmission> h = new ArrayList<Hasmission>();

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT h FROM Hasmission h WHERE h.hasmissionPK.playerID=:id");
        q.setParameter("id", id);
        List l = q.getResultList();

        for (Object o : l) {
            h.add((Hasmission) o);
        }

        return h;
    }

    /**
     * Retrieve the description of a player's missions
     *
     * @param id Player ID
     * @return Array of Strings - Each string describes a mission
     */
    @Override
    public ArrayList<String> getMissionTxt(int id) {
        ArrayList<Hasmission> h = this.getMyMissions(id);
        EntityManager em = emf.createEntityManager();
        ArrayList<String> l = new ArrayList<String>();
        for (Hasmission m : h) {

            Query q = em.createQuery("SELECT m.missionBody FROM Mission m WHERE m.missionID=:id");
            q.setParameter("id", m.getHasmissionPK().getMissionID());
            List l1 = q.getResultList();

            String s = (String) l1.get(0);
            s = s + " (" + m.getMissionStateBody() + ")";
            l.add(s);
        }
        return l;
    }

    /**
     * Generate 3 new missions for a player. This method is called when the
     * player signs in for the first time
     *
     * @param id Player ID
     */
    @Override
    public void get3New(int id) {
        this.newMission(id);
        this.newMission(id);
        this.newMission(id);
    }
}
