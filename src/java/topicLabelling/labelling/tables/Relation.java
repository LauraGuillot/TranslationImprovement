/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicLabelling.labelling.tables;

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
@Table(name = "relation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Relation.findAll", query = "SELECT r FROM Relation r"),
    @NamedQuery(name = "Relation.findByWordID", query = "SELECT r FROM Relation r WHERE r.relationPK.wordID = :wordID"),
    @NamedQuery(name = "Relation.findByCategoryID", query = "SELECT r FROM Relation r WHERE r.relationPK.categoryID = :categoryID"),
    @NamedQuery(name = "Relation.findByOccurence", query = "SELECT r FROM Relation r WHERE r.occurence = :occurence")})
public class Relation implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RelationPK relationPK;
    @Column(name = "occurence")
    private Integer occurence;
    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Category category;
    @JoinColumn(name = "wordID", referencedColumnName = "wordID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Word word;

    public Relation() {
    }

    public Relation(RelationPK relationPK) {
        this.relationPK = relationPK;
    }

    public Relation(int wordID, int categoryID) {
        this.relationPK = new RelationPK(wordID, categoryID);
    }

    public RelationPK getRelationPK() {
        return relationPK;
    }

    public void setRelationPK(RelationPK relationPK) {
        this.relationPK = relationPK;
    }

    public Integer getOccurence() {
        return occurence;
    }

    public void setOccurence(Integer occurence) {
        this.occurence = occurence;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relationPK != null ? relationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Relation)) {
            return false;
        }
        Relation other = (Relation) object;
        return !((this.relationPK == null && other.relationPK != null) || (this.relationPK != null && !this.relationPK.equals(other.relationPK)));
    }

    @Override
    public String toString() {
        return "topicLabelling.labelling.tables.Relation[ relationPK=" + relationPK + " ]";
    }
    
}
