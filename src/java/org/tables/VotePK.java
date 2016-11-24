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
public class VotePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "playerID")
    private int playerID;
    @Basic(optional = false)
    @Column(name = "sentenceID")
    private int sentenceID;

    public VotePK() {
    }

    public VotePK(int playerID, int sentenceID) {
        this.playerID = playerID;
        this.sentenceID = sentenceID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getSentenceID() {
        return sentenceID;
    }

    public void setSentenceID(int sentenceID) {
        this.sentenceID = sentenceID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) playerID;
        hash += (int) sentenceID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VotePK)) {
            return false;
        }
        VotePK other = (VotePK) object;
        if (this.playerID != other.playerID) {
            return false;
        }
        return this.sentenceID == other.sentenceID;
    }

    @Override
    public String toString() {
        return "org.tables.VotePK[ playerID=" + playerID + ", sentenceID=" + sentenceID + " ]";
    }
    
}
