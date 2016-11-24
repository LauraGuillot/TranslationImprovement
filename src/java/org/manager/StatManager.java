/**
 * ********************************************************************
 * Interface StattManager
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.util.ArrayList;
import org.utils.StatSentence;

public interface StatManager {

    public int nbPlayers();

    public int nbvotes();

    public int NbValidatedSent();

    public int nbInProgressSent();

    public int nbSub();

    public ArrayList<StatSentence> getApprovedSentences();
}
