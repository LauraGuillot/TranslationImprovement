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
@Table(name = "mission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mission.findAll", query = "SELECT m FROM Mission m"),
    @NamedQuery(name = "Mission.findByMissionID", query = "SELECT m FROM Mission m WHERE m.missionID = :missionID"),
    @NamedQuery(name = "Mission.findByMissionBody", query = "SELECT m FROM Mission m WHERE m.missionBody = :missionBody")})
public class Mission implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "missionID")
    private Integer missionID;
    @Column(name = "missionBody")
    private String missionBody;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mission")
    private Collection<Hasmission> hasmissionCollection;

    public Mission() {
    }

    public Mission(Integer missionID) {
        this.missionID = missionID;
    }

    public Integer getMissionID() {
        return missionID;
    }

    public void setMissionID(Integer missionID) {
        this.missionID = missionID;
    }

    public String getMissionBody() {
        return missionBody;
    }

    public void setMissionBody(String missionBody) {
        this.missionBody = missionBody;
    }

    @XmlTransient
    public Collection<Hasmission> getHasmissionCollection() {
        return hasmissionCollection;
    }

    public void setHasmissionCollection(Collection<Hasmission> hasmissionCollection) {
        this.hasmissionCollection = hasmissionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (missionID != null ? missionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mission)) {
            return false;
        }
        Mission other = (Mission) object;
        return !((this.missionID == null && other.missionID != null) || (this.missionID != null && !this.missionID.equals(other.missionID)));
    }

    @Override
    public String toString() {
        return "org.tables.Mission[ missionID=" + missionID + " ]";
    }
    
}
