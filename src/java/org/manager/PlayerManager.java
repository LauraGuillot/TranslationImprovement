/**
 * ********************************************************************
 * Interface PlayerManager
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.tables.Connect;
import org.tables.Player;

public interface PlayerManager {

    //Displaying of the players
    public Collection<Player> page();

    //Insertion of a player in the database
    public int add(String n);

    //Connection of a player (update the table connect)
    public String connect(Player p);

    //Update the table connect
    public void updateConnect(Connect c);

    //Retrieve the player given his connectID
    public Player getByConnectID(String id);

    //Retrieve the rank of a player
    public int getRank(Player p);

    //Add points to a player
    public void addPoints(Player p, int nb);

    //Get the leaderboard based on points
    public List<Player> leaderboardPoint();

    //Get the leaderboard based on confidence scores
    public List<Player> leaderboardScore();

    //Get a player by his id
    public Player getByPlayerId(int id);

    //Check if a player is in the base 
    public boolean isIN(String name);

    public ArrayList<Integer> rankPoint(List<Player> p);

    public ArrayList<Integer> rankCS(List<Player> p);

    public int getRankPoints(Player p);
}
