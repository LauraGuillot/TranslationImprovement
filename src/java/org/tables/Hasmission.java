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
@Table(name = "hasmission")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hasmission.findAll", query = "SELECT h FROM Hasmission h"),
    @NamedQuery(name = "Hasmission.findByMissionID", query = "SELECT h FROM Hasmission h WHERE h.hasmissionPK.missionID = :missionID"),
    @NamedQuery(name = "Hasmission.findByPlayerID", query = "SELECT h FROM Hasmission h WHERE h.hasmissionPK.playerID = :playerID"),
    @NamedQuery(name = "Hasmission.findByMissionState", query = "SELECT h FROM Hasmission h WHERE h.missionState = :missionState"),
    @NamedQuery(name = "Hasmission.findByMissionStateBody", query = "SELECT h FROM Hasmission h WHERE h.missionStateBody = :missionStateBody"),
    @NamedQuery(name = "Hasmission.findByMissionDate", query = "SELECT h FROM Hasmission h WHERE h.missionDate = :missionDate"),
    @NamedQuery(name = "Hasmission.findByMissionInfo", query = "SELECT h FROM Hasmission h WHERE h.missionInfo = :missionInfo")})
public class Hasmission implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HasmissionPK hasmissionPK;
    @Column(name = "missionState")
    private Integer missionState;
    @Column(name = "missionStateBody")
    private String missionStateBody;
    @Column(name = "missionDate")
    private String missionDate;
    @Column(name = "missionInfo")
    private String missionInfo;
    @JoinColumn(name = "missionID", referencedColumnName = "missionID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Mission mission;
    @JoinColumn(name = "playerID", referencedColumnName = "playerID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Player player;

    public Hasmission() {
    }

    public Hasmission(HasmissionPK hasmissionPK) {
        this.hasmissionPK = hasmissionPK;
    }

    public Hasmission(int missionID, int playerID) {
        this.hasmissionPK = new HasmissionPK(missionID, playerID);
    }

    public HasmissionPK getHasmissionPK() {
        return hasmissionPK;
    }

    public void setHasmissionPK(HasmissionPK hasmissionPK) {
        this.hasmissionPK = hasmissionPK;
    }

    public Integer getMissionState() {
        return missionState;
    }

    public void setMissionState(Integer missionState) {
        this.missionState = missionState;
    }

    public String getMissionStateBody() {
        return missionStateBody;
    }

    public void setMissionStateBody(String missionStateBody) {
        this.missionStateBody = missionStateBody;
    }

    public String getMissionDate() {
        return missionDate;
    }

    public void setMissionDate(String missionDate) {
        this.missionDate = missionDate;
    }

    public String getMissionInfo() {
        return missionInfo;
    }

    public void setMissionInfo(String missionInfo) {
        this.missionInfo = missionInfo;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hasmissionPK != null ? hasmissionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hasmission)) {
            return false;
        }
        Hasmission other = (Hasmission) object;
        return !((this.hasmissionPK == null && other.hasmissionPK != null) || (this.hasmissionPK != null && !this.hasmissionPK.equals(other.hasmissionPK)));
    }

    @Override
    public String toString() {
        return "org.tables.Hasmission[ hasmissionPK=" + hasmissionPK + " ]";
    }
    
}
