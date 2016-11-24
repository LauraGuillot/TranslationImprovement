/**
 * ********************************************************************
 * Interface TrophyManager
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.util.ArrayList;

/**
 *
 * @author Laura
 */
public interface TrophyManager {
    
    public void addNewTrophies(int id);
    public ArrayList<Integer> getMyTrophies (int id);
    
    public boolean isInTop(int n, int id);
    public void incrNbEval(int n,int id);
    public void incrNbSub(int n, int id);
    
    public void updateTrophies123(int id);
    
    public void updateTrophies(int id, int nbeval, int nbsub);
}
