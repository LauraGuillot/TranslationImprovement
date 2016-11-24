/**
 * ********************************************************************
 * Class manager
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package topicLabelling.labelling.manager;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import topicLabelling.labelling.tables.*;

/**
 *
 * @author Laura
 */
public class manager {

    private EntityManagerFactory emf;
    private static manager theManager;

    private manager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("translationPU2");
        }
    }

    public static manager getInstance() {
        if (theManager == null) {
            theManager = new manager();
        }
        return theManager;
    }

    /**
     * Get all the relations of a word
     *
     * @param w Word
     * @return List of Relations
     */
    public ArrayList<Relation> getRelations(String w) {
        EntityManager em = emf.createEntityManager();

        Query q = em.createQuery("SELECT l FROM Relation l WHERE (l.word.w=:w)");
        q.setParameter("w", w);
        List li = q.getResultList();

        ArrayList<Relation> l = new ArrayList();

        for (Object o : li) {
            l.add((Relation) o);
        }

        return l;
    }

}
