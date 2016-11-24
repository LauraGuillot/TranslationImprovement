/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tables;

import java.io.Serializable;
import javax.persistence.Column;
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
@Table(name = "islinkto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Islinkto.findAll", query = "SELECT i FROM Islinkto i"),
    @NamedQuery(name = "Islinkto.findBySentenceID", query = "SELECT i FROM Islinkto i WHERE i.islinktoPK.sentenceID = :sentenceID"),
    @NamedQuery(name = "Islinkto.findByTopicID", query = "SELECT i FROM Islinkto i WHERE i.islinktoPK.topicID = :topicID")})
public class Islinkto implements Serializable {

    @Column(name = "weight")
    private Integer weight;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IslinktoPK islinktoPK;
    @JoinColumn(name = "topicID", referencedColumnName = "topicID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Topic topic;

    public Islinkto() {
    }

    public Islinkto(IslinktoPK islinktoPK) {
        this.islinktoPK = islinktoPK;
    }

    public Islinkto(int sentenceID, int topicID) {
        this.islinktoPK = new IslinktoPK(sentenceID, topicID);
    }

    public IslinktoPK getIslinktoPK() {
        return islinktoPK;
    }

    public void setIslinktoPK(IslinktoPK islinktoPK) {
        this.islinktoPK = islinktoPK;
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
        hash += (islinktoPK != null ? islinktoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Islinkto)) {
            return false;
        }
        Islinkto other = (Islinkto) object;
        return !((this.islinktoPK == null && other.islinktoPK != null) || (this.islinktoPK != null && !this.islinktoPK.equals(other.islinktoPK)));
    }

    @Override
    public String toString() {
        return "org.tables.Islinkto[ islinktoPK=" + islinktoPK + " ]";
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
    
}
