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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "sentence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sentence.findAll", query = "SELECT s FROM Sentence s"),
    @NamedQuery(name = "Sentence.findBySentenceID", query = "SELECT s FROM Sentence s WHERE s.sentenceID = :sentenceID"),
    @NamedQuery(name = "Sentence.findBySentenceValidation", query = "SELECT s FROM Sentence s WHERE s.sentenceValidation = :sentenceValidation"),
    @NamedQuery(name = "Sentence.findByTranslationApproved", query = "SELECT s FROM Sentence s WHERE s.translationApproved = :translationApproved"),
    @NamedQuery(name = "Sentence.findBySentenceDate", query = "SELECT s FROM Sentence s WHERE s.sentenceDate = :sentenceDate"),
    @NamedQuery(name = "Sentence.findBySentenceBody", query = "SELECT s FROM Sentence s WHERE s.sentenceBody = :sentenceBody"),
    @NamedQuery(name = "Sentence.findBySentenceLanguage", query = "SELECT s FROM Sentence s WHERE s.sentenceLanguage = :sentenceLanguage")})

public class Sentence implements Serializable {

    @Column(name = "translationApproved")
    private Integer translationApproved;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentenceID")
    private Collection<Translation> translationCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sentenceID")
    private Integer sentenceID;
    @Column(name = "sentenceValidation")
    private Boolean sentenceValidation;
    @Column(name = "sentenceDate")
    private String sentenceDate;
    @Column(name = "sentenceBody")
    private String sentenceBody;
    @Column(name = "sentenceLanguage")
    private String sentenceLanguage;
    @Column(name = "translationLanguage")
    private String translationLanguage;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sentence")
    private Collection<Vote> voteCollection;

   
    public Sentence() {
    }

    public Sentence(Integer sentenceID) {
        this.sentenceID = sentenceID;
    }

    public Integer getSentenceID() {
        return sentenceID;
    }

    public void setSentenceID(Integer sentenceID) {
        this.sentenceID = sentenceID;
    }

    public Boolean getSentenceValidation() {
        return sentenceValidation;
    }

    public void setSentenceValidation(Boolean sentenceValidation) {
        this.sentenceValidation = sentenceValidation;
    }

    public String getSentenceDate() {
        return sentenceDate;
    }

    public void setSentenceDate(String sentenceDate) {
        this.sentenceDate = sentenceDate;
    }

    public String getSentenceBody() {
        return sentenceBody;
    }

    public void setSentenceBody(String sentenceBody) {
        this.sentenceBody = sentenceBody;
    }

    public String getSentenceLanguage() {
        return sentenceLanguage;
    }

    public void setSentenceLanguage(String sentenceLanguage) {
        this.sentenceLanguage = sentenceLanguage;
    }

    public String getTranslationLanguage() {
        return translationLanguage;
    }

    public void setTranslationLanguage(String translationLanguage) {
        this.translationLanguage = translationLanguage;
    }

    @XmlTransient
    public Collection<Vote> getVoteCollection() {
        return voteCollection;
    }

    public void setVoteCollection(Collection<Vote> voteCollection) {
        this.voteCollection = voteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sentenceID != null ? sentenceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sentence)) {
            return false;
        }
        Sentence other = (Sentence) object;
        return !((this.sentenceID == null && other.sentenceID != null) || (this.sentenceID != null && !this.sentenceID.equals(other.sentenceID)));
    }

    @Override
    public String toString() {
        return "org.tables.Sentence[ sentenceID=" + sentenceID + " ]";
    }

    public Integer getTranslationApproved() {
        return translationApproved;
    }

    public void setTranslationApproved(Integer translationApproved) {
        this.translationApproved = translationApproved;
    }

    @XmlTransient
    public Collection<Translation> getTranslationCollection() {
        return translationCollection;
    }

    public void setTranslationCollection(Collection<Translation> translationCollection) {
        this.translationCollection = translationCollection;
    }

}
