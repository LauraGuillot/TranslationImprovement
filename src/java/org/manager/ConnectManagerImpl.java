/**
 * ********************************************************************
 * Class ConnectManagerImpl
 * Management of the players connections
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.tables.Connect;

public class ConnectManagerImpl implements ConnectManager {

    private EntityManagerFactory emf;
    private static ConnectManagerImpl theConnectManager;

    private ConnectManagerImpl() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("translationPU");
        }
    }

    public static ConnectManager getInstance() {
        if (theConnectManager == null) {
            theConnectManager = new ConnectManagerImpl();
        }
        return theConnectManager;
    }

    /**
     * Disconnection of a user from the database
     *
     * @param id Connection ID of the user
     */
    @Override
    public void deco(String id) {
        EntityManager em = emf.createEntityManager();

        Query queryProductsByName = em.createNamedQuery("Connect.findByConnectID", Connect.class);
        queryProductsByName.setParameter("connectID", id);
        List l = queryProductsByName.getResultList();

        if (!l.isEmpty()) {
            Connect c = (Connect) l.get(0);
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
        }
    }

    /**
     * Updating the connection of a user. This method is called each time a
     * controller is called and updates the connection date with the current
     * date
     *
     * @param id Connection ID of the user
     */
    @Override
    public void udpateMyConnection(String id) {
        EntityManager em = emf.createEntityManager();

        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        String d = formater.format(date);

        Connect c = em.find(Connect.class, id);

        c.setLastAction(d);

        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
    }

    /**
     * Updating connections of all the players: the players who had been
     * inactive for more than 1 hour are disconnected
     */
    @Override
    public void updateConnections() {

        EntityManager em = emf.createEntityManager();

        //Current date
        String format = "dd/MM/yy H:mm:ss";
        java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
        java.util.Date date = new java.util.Date();
        String now = formater.format(date);

        //Selection of the table connect
        Query queryProductsByName = em.createNamedQuery("Connect.findAll", Connect.class);
        List l = queryProductsByName.getResultList();

        //Test of the connection
        for (int i = 0; i < l.size(); i++) {
            if (isOld(((Connect) l.get(i)).getLastAction(), now)) {
                Connect c = em.find(Connect.class, ((Connect) l.get(i)).getConnectID());
                em.getTransaction().begin();
                em.remove(c);
                em.getTransaction().commit();
            }
        }
    }

    /**
     * Compare a date with the current one
     *
     * @param lastAction A date
     * @param now Current date
     * @return Boolean : lastAction > now - 1 hour
     */
    private boolean isOld(String lastAction, String now) {

        Date date1;
        Date date2;
        boolean isold = false;

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy H:mm:ss");
            date1 = formatter.parse(lastAction);
            date2 = formatter.parse(now);

            //Diff in milliseconds
            long diff = -date1.getTime() + date2.getTime();

            isold = (diff > 3600000);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isold;
    }

    /**
     * Test id a user is connected
     *
     * @param id Connection ID of the user
     * @return Boolean
     */
    @Override
    public boolean isConnected(String id) {
        EntityManager em = emf.createEntityManager();
        Query queryProductsByName = em.createNamedQuery("Connect.findByConnectID", Connect.class);
        queryProductsByName.setParameter("connectID", id);
        List l = queryProductsByName.getResultList();

        return !(l.isEmpty());
    }
}
