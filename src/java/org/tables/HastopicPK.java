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
public class HastopicPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "playerID")
    private int playerID;
    @Basic(optional = false)
    @Column(name = "topicID")
    private int topicID;

    public HastopicPK() {
    }

    public HastopicPK(int playerID, int topicID) {
        this.playerID = playerID;
        this.topicID = topicID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) playerID;
        hash += (int) topicID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HastopicPK)) {
            return false;
        }
        HastopicPK other = (HastopicPK) object;
        if (this.playerID != other.playerID) {
            return false;
        }
        return this.topicID == other.topicID;
    }

    @Override
    public String toString() {
        return "org.tables.HastopicPK[ playerID=" + playerID + ", topicID=" + topicID + " ]";
    }
    
}
