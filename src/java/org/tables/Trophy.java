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
@Table(name = "trophy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trophy.findAll", query = "SELECT t FROM Trophy t"),
    @NamedQuery(name = "Trophy.findByTrophyID", query = "SELECT t FROM Trophy t WHERE t.trophyID = :trophyID"),
    @NamedQuery(name = "Trophy.findByTrophyBody", query = "SELECT t FROM Trophy t WHERE t.trophyBody = :trophyBody")})
public class Trophy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trophyID")
    private Integer trophyID;
    @Column(name = "trophyBody")
    private String trophyBody;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trophy")
    private Collection<Wintrophy> wintrophyCollection;

    public Trophy() {
    }

    public Trophy(Integer trophyID) {
        this.trophyID = trophyID;
    }

    public Integer getTrophyID() {
        return trophyID;
    }

    public void setTrophyID(Integer trophyID) {
        this.trophyID = trophyID;
    }

    public String getTrophyBody() {
        return trophyBody;
    }

    public void setTrophyBody(String trophyBody) {
        this.trophyBody = trophyBody;
    }

    @XmlTransient
    public Collection<Wintrophy> getWintrophyCollection() {
        return wintrophyCollection;
    }

    public void setWintrophyCollection(Collection<Wintrophy> wintrophyCollection) {
        this.wintrophyCollection = wintrophyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (trophyID != null ? trophyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trophy)) {
            return false;
        }
        Trophy other = (Trophy) object;
        return !((this.trophyID == null && other.trophyID != null) || (this.trophyID != null && !this.trophyID.equals(other.trophyID)));
    }

    @Override
    public String toString() {
        return "org.tables.Trophy[ trophyID=" + trophyID + " ]";
    }
    
}
