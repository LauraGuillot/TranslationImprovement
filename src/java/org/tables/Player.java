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
@Table(name = "player")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p"),
    @NamedQuery(name = "Player.findByPlayerID", query = "SELECT p FROM Player p WHERE p.playerID = :playerID"),
    @NamedQuery(name = "Player.findByPlayerName", query = "SELECT p FROM Player p WHERE p.playerName = :playerName"),
    @NamedQuery(name = "Player.findByPlayerConfidenceScore", query = "SELECT p FROM Player p WHERE p.playerConfidenceScore = :playerConfidenceScore"),
    @NamedQuery(name = "Player.findByPlayerPoints", query = "SELECT p FROM Player p WHERE p.playerPoints = :playerPoints")})
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "playerID")
    private Integer playerID;
    @Column(name = "playerName")
    private String playerName;
    @Column(name = "playerConfidenceScore")
    private Double playerConfidenceScore;
    @Column(name = "playerPoints")
    private Integer playerPoints;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private Collection<Wintrophy> wintrophyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private Collection<Hasmission> hasmissionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "player")
    private Collection<Vote> voteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "playerID")
    private Collection<Connect> connectCollection;

    public Player() {
    }

    public Player(String n) {
        this.playerName = n;
        this.playerPoints=0;
        this.playerConfidenceScore = 0.0;
    }


    public Integer getPlayerID() {
        return playerID;
    }

    public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Double getPlayerConfidenceScore() {
        return playerConfidenceScore;
    }

    public void setPlayerConfidenceScore(double playerConfidenceScore) {
        this.playerConfidenceScore = playerConfidenceScore;
    }

    public Integer getPlayerPoints() {
        return playerPoints;
    }

    public void setPlayerPoints(Integer playerPoints) {
        this.playerPoints = playerPoints;
    }

   

    @XmlTransient
    public Collection<Wintrophy> getWintrophyCollection() {
        return wintrophyCollection;
    }

    public void setWintrophyCollection(Collection<Wintrophy> wintrophyCollection) {
        this.wintrophyCollection = wintrophyCollection;
    }

    @XmlTransient
    public Collection<Hasmission> getHasmissionCollection() {
        return hasmissionCollection;
    }

    public void setHasmissionCollection(Collection<Hasmission> hasmissionCollection) {
        this.hasmissionCollection = hasmissionCollection;
    }

    @XmlTransient
    public Collection<Vote> getVoteCollection() {
        return voteCollection;
    }

    public void setVoteCollection(Collection<Vote> voteCollection) {
        this.voteCollection = voteCollection;
    }

    @XmlTransient
    public Collection<Connect> getConnectCollection() {
        return connectCollection;
    }

    public void setConnectCollection(Collection<Connect> connectCollection) {
        this.connectCollection = connectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (playerID != null ? playerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Player)) {
            return false;
        }
        Player other = (Player) object;
        return !((this.playerID == null && other.playerID != null) || (this.playerID != null && !this.playerID.equals(other.playerID)));
    }

    @Override
    public String toString() {
        return "org.tables.Player[ playerID=" + playerID + " ]";
    }
    
}
