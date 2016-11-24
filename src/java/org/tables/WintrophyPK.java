/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Laura
 */
@Embeddable
public class WintrophyPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "trophyID")
    private int trophyID;
    @Basic(optional = false)
    @Column(name = "playerID")
    private int playerID;

    public WintrophyPK() {
    }

    public WintrophyPK(int trophyID, int playerID) {
        this.trophyID = trophyID;
        this.playerID = playerID;
    }

    public int getTrophyID() {
        return trophyID;
    }

    public void setTrophyID(int trophyID) {
        this.trophyID = trophyID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) trophyID;
        hash += (int) playerID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WintrophyPK)) {
            return false;
        }
        WintrophyPK other = (WintrophyPK) object;
        if (this.trophyID != other.trophyID) {
            return false;
        }
        return this.playerID == other.playerID;
    }

    @Override
    public String toString() {
        return "org.tables.WintrophyPK[ trophyID=" + trophyID + ", playerID=" + playerID + " ]";
    }
    
}
