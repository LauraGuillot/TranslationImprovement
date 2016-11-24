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
@Table(name = "wintrophy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wintrophy.findAll", query = "SELECT w FROM Wintrophy w"),
    @NamedQuery(name = "Wintrophy.findByTrophyID", query = "SELECT w FROM Wintrophy w WHERE w.wintrophyPK.trophyID = :trophyID"),
    @NamedQuery(name = "Wintrophy.findByPlayerID", query = "SELECT w FROM Wintrophy w WHERE w.wintrophyPK.playerID = :playerID"),
    @NamedQuery(name = "Wintrophy.findByTrophyState", query = "SELECT w FROM Wintrophy w WHERE w.trophyState = :trophyState"),
    @NamedQuery(name = "Wintrophy.findByTrophyStateBody", query = "SELECT w FROM Wintrophy w WHERE w.trophyStateBody = :trophyStateBody"),
    @NamedQuery(name = "Wintrophy.findByTrophyDate", query = "SELECT w FROM Wintrophy w WHERE w.trophyDate = :trophyDate")})
public class Wintrophy implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected WintrophyPK wintrophyPK;
    @Column(name = "trophyState")
    private Integer trophyState;
    @Column(name = "trophyStateBody")
    private Integer trophyStateBody;
    @Column(name = "trophyDate")
    private String trophyDate;
    @JoinColumn(name = "playerID", referencedColumnName = "playerID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Player player;
    @JoinColumn(name = "trophyID", referencedColumnName = "trophyID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Trophy trophy;

    public Wintrophy() {
    }

    public Wintrophy(WintrophyPK wintrophyPK) {
        this.wintrophyPK = wintrophyPK;
        this.trophyState = 0;
    }

    public Wintrophy(int trophyID, int playerID) {
        this.wintrophyPK = new WintrophyPK(trophyID, playerID);
        this.trophyState = 0;
    }

    public WintrophyPK getWintrophyPK() {
        return wintrophyPK;
    }

    public void setWintrophyPK(WintrophyPK wintrophyPK) {
        this.wintrophyPK = wintrophyPK;
    }

    public Integer getTrophyState() {
        return trophyState;
    }

    public void setTrophyState(Integer trophyState) {
        this.trophyState = trophyState;
    }

    public Integer getTrophyStateBody() {
        return trophyStateBody;
    }

    public void setTrophyStateBody(Integer trophyStateBody) {
        this.trophyStateBody = trophyStateBody;
    }

    public String getTrophyDate() {
        return trophyDate;
    }

    public void setTrophyDate(String trophyDate) {
        this.trophyDate = trophyDate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Trophy getTrophy() {
        return trophy;
    }

    public void setTrophy(Trophy trophy) {
        this.trophy = trophy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wintrophyPK != null ? wintrophyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wintrophy)) {
            return false;
        }
        Wintrophy other = (Wintrophy) object;
        return !((this.wintrophyPK == null && other.wintrophyPK != null) || (this.wintrophyPK != null && !this.wintrophyPK.equals(other.wintrophyPK)));
    }

    @Override
    public String toString() {
        return "org.tables.Wintrophy[ wintrophyPK=" + wintrophyPK + " ]";
    }

}
