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
public class HasmissionPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "missionID")
    private int missionID;
    @Basic(optional = false)
    @Column(name = "playerID")
    private int playerID;

    public HasmissionPK() {
    }

    public HasmissionPK(int missionID, int playerID) {
        this.missionID = missionID;
        this.playerID = playerID;
    }

    public int getMissionID() {
        return missionID;
    }

    public void setMissionID(int missionID) {
        this.missionID = missionID;
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
        hash += (int) missionID;
        hash += (int) playerID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HasmissionPK)) {
            return false;
        }
        HasmissionPK other = (HasmissionPK) object;
        if (this.missionID != other.missionID) {
            return false;
        }
        return this.playerID == other.playerID;
    }

    @Override
    public String toString() {
        return "org.tables.HasmissionPK[ missionID=" + missionID + ", playerID=" + playerID + " ]";
    }
    
}
