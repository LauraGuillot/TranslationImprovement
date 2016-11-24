/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicLabelling.labelling.tables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Laura
 */
@Embeddable
public class RelationPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "wordID")
    private int wordID;
    @Basic(optional = false)
    @Column(name = "categoryID")
    private int categoryID;

    public RelationPK() {
    }

    public RelationPK(int wordID, int categoryID) {
        this.wordID = wordID;
        this.categoryID = categoryID;
    }

    public int getWordID() {
        return wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) wordID;
        hash += (int) categoryID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RelationPK)) {
            return false;
        }
        RelationPK other = (RelationPK) object;
        if (this.wordID != other.wordID) {
            return false;
        }
        return this.categoryID == other.categoryID;
    }

    @Override
    public String toString() {
        return "topicLabelling.labelling.tables.RelationPK[ wordID=" + wordID + ", categoryID=" + categoryID + " ]";
    }
    
}
