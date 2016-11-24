/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tables;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "hastopic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hastopic.findAll", query = "SELECT h FROM Hastopic h"),
    @NamedQuery(name = "Hastopic.findByPlayerID", query = "SELECT h FROM Hastopic h WHERE h.hastopicPK.playerID = :playerID"),
    @NamedQuery(name = "Hastopic.findByTopicID", query = "SELECT h FROM Hastopic h WHERE h.hastopicPK.topicID = :topicID")})
public class Hastopic implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HastopicPK hastopicPK;
    @JoinColumn(name = "topicID", referencedColumnName = "topicID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Topic topic;

    public Hastopic() {
    }

    public Hastopic(HastopicPK hastopicPK) {
        this.hastopicPK = hastopicPK;
    }

    public Hastopic(int playerID, int topicID) {
        this.hastopicPK = new HastopicPK(playerID, topicID);
    }

    public HastopicPK getHastopicPK() {
        return hastopicPK;
    }

    public void setHastopicPK(HastopicPK hastopicPK) {
        this.hastopicPK = hastopicPK;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hastopicPK != null ? hastopicPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hastopic)) {
            return false;
        }
        Hastopic other = (Hastopic) object;
        return !((this.hastopicPK == null && other.hastopicPK != null) || (this.hastopicPK != null && !this.hastopicPK.equals(other.hastopicPK)));
    }

    @Override
    public String toString() {
        return "org.tables.Hastopic[ hastopicPK=" + hastopicPK + " ]";
    }
    
}
