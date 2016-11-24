/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicLabelling.labelling.tables;

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
@Table(name = "word")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Word.findAll", query = "SELECT w FROM Word w"),
    @NamedQuery(name = "Word.findByWordID", query = "SELECT w FROM Word w WHERE w.wordID = :wordID"),
    @NamedQuery(name = "Word.findByW", query = "SELECT w FROM Word w WHERE w.w = :w")})
public class Word implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "wordID")
    private Integer wordID;
    @Column(name = "w")
    private String w;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "word")
    private Collection<Relation> relationCollection;

    public Word() {
    }

    public Word(Integer wordID) {
        this.wordID = wordID;
    }

    public Integer getWordID() {
        return wordID;
    }

    public void setWordID(Integer wordID) {
        this.wordID = wordID;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    @XmlTransient
    public Collection<Relation> getRelationCollection() {
        return relationCollection;
    }

    public void setRelationCollection(Collection<Relation> relationCollection) {
        this.relationCollection = relationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wordID != null ? wordID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Word)) {
            return false;
        }
        Word other = (Word) object;
        return !((this.wordID == null && other.wordID != null) || (this.wordID != null && !this.wordID.equals(other.wordID)));
    }

    @Override
    public String toString() {
        return "topicLabelling.labelling.tables.Word[ wordID=" + wordID + " ]";
    }
    
}
