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
@Table(name = "vote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vote.findAll", query = "SELECT v FROM Vote v"),
    @NamedQuery(name = "Vote.findByPlayerID", query = "SELECT v FROM Vote v WHERE v.votePK.playerID = :playerID"),
    @NamedQuery(name = "Vote.findBySentenceID", query = "SELECT v FROM Vote v WHERE v.votePK.sentenceID = :sentenceID"),
    @NamedQuery(name = "Vote.findByVoteFor", query = "SELECT v FROM Vote v WHERE v.voteFor = :voteFor"),
    @NamedQuery(name = "Vote.findByVoteDate", query = "SELECT v FROM Vote v WHERE v.voteDate = :voteDate")})
public class Vote implements Serializable {

    @Column(name = "voteFor")
    private Integer voteFor;

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VotePK votePK;
    @Column(name = "voteDate")
    private String voteDate;
    @JoinColumn(name = "playerID", referencedColumnName = "playerID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Player player;
    @JoinColumn(name = "sentenceID", referencedColumnName = "sentenceID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sentence sentence;

    public Vote() {
    }

    public Vote(VotePK votePK) {
        this.votePK = votePK;
    }

    public Vote(int playerID, int sentenceID) {
        this.votePK = new VotePK(playerID, sentenceID);
    }

    public Vote(int playerID, int sentenceID, int vote, String d){
        this.votePK = new VotePK(playerID, sentenceID);
        this.voteDate=d;
        this.voteFor=vote;
    }
    
    
    
    public VotePK getVotePK() {
        return votePK;
    }

    public void setVotePK(VotePK votePK) {
        this.votePK = votePK;
    }


    public String getVoteDate() {
        return voteDate;
    }

    public void setVoteDate(String voteDate) {
        this.voteDate = voteDate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (votePK != null ? votePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vote)) {
            return false;
        }
        Vote other = (Vote) object;
        return !((this.votePK == null && other.votePK != null) || (this.votePK != null && !this.votePK.equals(other.votePK)));
    }

    @Override
    public String toString() {
        return "org.tables.Vote[ votePK=" + votePK + " ]";
    }

    public Integer getVoteFor() {
        return voteFor;
    }

    public void setVoteFor(Integer voteFor) {
        this.voteFor = voteFor;
    }
    
}
