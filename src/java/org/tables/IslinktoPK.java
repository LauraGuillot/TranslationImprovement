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
public class IslinktoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "sentenceID")
    private int sentenceID;
    @Basic(optional = false)
    @Column(name = "topicID")
    private int topicID;

    public IslinktoPK() {
    }

    public IslinktoPK(int sentenceID, int topicID) {
        this.sentenceID = sentenceID;
        this.topicID = topicID;
    }

    public int getSentenceID() {
        return sentenceID;
    }

    public void setSentenceID(int sentenceID) {
        this.sentenceID = sentenceID;
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
        hash += (int) sentenceID;
        hash += (int) topicID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IslinktoPK)) {
            return false;
        }
        IslinktoPK other = (IslinktoPK) object;
        if (this.sentenceID != other.sentenceID) {
            return false;
        }
        return this.topicID == other.topicID;
    }

    @Override
    public String toString() {
        return "org.tables.IslinktoPK[ sentenceID=" + sentenceID + ", topicID=" + topicID + " ]";
    }
    
}
