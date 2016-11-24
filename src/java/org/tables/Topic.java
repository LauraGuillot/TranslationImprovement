/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tables;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Laura
 */
@Entity
@Table(name = "topic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t"),
    @NamedQuery(name = "Topic.findByTopicID", query = "SELECT t FROM Topic t WHERE t.topicID = :topicID"),
    @NamedQuery(name = "Topic.findByTopicName", query = "SELECT t FROM Topic t WHERE t.topicName = :topicName")})
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "topicID")
    private Integer topicID;
    @Column(name = "topicName")
    private String topicName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private Collection<Islinkto> islinktoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topic")
    private Collection<Hastopic> hastopicCollection;

    public Topic() {
    }

    public Topic(Integer topicID) {
        this.topicID = topicID;
    }

    public Integer getTopicID() {
        return topicID;
    }

    public void setTopicID(Integer topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    @XmlTransient
    public Collection<Islinkto> getIslinktoCollection() {
        return islinktoCollection;
    }

    public void setIslinktoCollection(Collection<Islinkto> islinktoCollection) {
        this.islinktoCollection = islinktoCollection;
    }

    @XmlTransient
    public Collection<Hastopic> getHastopicCollection() {
        return hastopicCollection;
    }

    public void setHastopicCollection(Collection<Hastopic> hastopicCollection) {
        this.hastopicCollection = hastopicCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (topicID != null ? topicID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        return !((this.topicID == null && other.topicID != null) || (this.topicID != null && !this.topicID.equals(other.topicID)));
    }

    @Override
    public String toString() {
        return "org.tables.Topic[ topicID=" + topicID + " ]";
    }
    
}
