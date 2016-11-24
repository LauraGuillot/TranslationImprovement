/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "translation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Translation.findAll", query = "SELECT t FROM Translation t"),
    @NamedQuery(name = "Translation.findByTranslationID", query = "SELECT t FROM Translation t WHERE t.translationID = :translationID"),

    @NamedQuery(name = "Translation.findByTranslationBody", query = "SELECT t FROM Translation t WHERE t.translationBody = :translationBody")})
public class Translation implements Serializable {

    @Column(name = "player")
    private Integer player;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "translationID")
    private Integer translationID;

    @Column(name = "translationBody")
    private String translationBody;
    @JoinColumn(name = "sentenceID", referencedColumnName = "sentenceID")
    @ManyToOne(optional = false)
    private Sentence sentenceID;

    public Translation() {
    }

    public Translation(Integer translationID) {
        this.translationID = translationID;
    }
               
    public Integer getTranslationID() {
        return translationID;
    }

    public void setTranslationID(Integer translationID) {
        this.translationID = translationID;
    }

    public String getTranslationBody() {
        return translationBody;
    }

    public void setTranslationBody(String translationBody) {
        this.translationBody = translationBody;
    }

    public Sentence getSentenceID() {
        return sentenceID;
    }

    public void setSentenceID(Sentence sentenceID) {
        this.sentenceID = sentenceID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (translationID != null ? translationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Translation)) {
            return false;
        }
        Translation other = (Translation) object;
        return !((this.translationID == null && other.translationID != null) || (this.translationID != null && !this.translationID.equals(other.translationID)));
    }

    @Override
    public String toString() {
        return "org.tables.Translation[ translationID=" + translationID + " ]";
    }

    public Integer getPlayer() {
        return player;
    }

    public void setPlayer(Integer player) {
        this.player = player;
    }

}
