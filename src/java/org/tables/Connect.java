/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Laura
 */
@Entity
@Table(name = "connect")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Connect.findAll", query = "SELECT c FROM Connect c"),
    @NamedQuery(name = "Connect.findByConnectID", query = "SELECT c FROM Connect c WHERE c.connectID = :connectID"),
    @NamedQuery(name = "Connect.findByLastAction", query = "SELECT c FROM Connect c WHERE c.lastAction = :lastAction")})
public class Connect implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "connectID")
    private String connectID;
    @Column(name = "lastAction")
    private String lastAction;
    @JoinColumn(name = "playerID", referencedColumnName = "playerID")
    @ManyToOne(optional = false)
    private Player playerID;

    public Connect() {
    }

    public Connect(String connectID) {
        this.connectID = connectID;
    }

    public Connect(String connectID, String lastAction, Player playerID) {
        this.connectID = connectID;
        this.lastAction = lastAction;
        this.playerID = playerID;
    }

    public String getConnectID() {
        return connectID;
    }

    public void setConnectID(String connectID) {
        this.connectID = connectID;
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    public Player getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Player playerID) {
        this.playerID = playerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (connectID != null ? connectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Connect)) {
            return false;
        }
        Connect other = (Connect) object;
        return !((this.connectID == null && other.connectID != null) || (this.connectID != null && !this.connectID.equals(other.connectID)));
    }

    @Override
    public String toString() {
        return "org.tables.Connect[ connectID=" + connectID + " ]";
    }

    public static String genereID() {
        String id = "";
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int x = 0; x < 100; x++) {
            int i = (int) Math.floor(Math.random() * 62);
            id += chars.charAt(i);
        }

        return id;
    }
}
