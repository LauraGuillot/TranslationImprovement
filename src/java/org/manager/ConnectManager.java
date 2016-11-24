/**
 * ********************************************************************
 * Interface ConnectManager
 * --------------------------------------------------------------------
 * Last update : 16/06/2016
 * Author : Laura GUILLOT
 *********************************************************************
 */
package org.manager;

public interface ConnectManager {

    public void deco(String id);

    public void udpateMyConnection(String id);

    public void updateConnections();

    public boolean isConnected(String id);

}
