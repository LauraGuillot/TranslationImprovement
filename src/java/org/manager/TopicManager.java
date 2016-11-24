/**
 * ********************************************************************
 * Interface TopicManager
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.util.ArrayList;
import java.util.List;
import org.tables.*;

public interface TopicManager {

    public List getTopics();

    public void addTopic(int id, int playerID);

    public ArrayList<Topic> myTopics(int id);

    public ArrayList<Topic> getOthersTopics(int id);

    public ArrayList<Topic> getTopics(Sentence s);

}
