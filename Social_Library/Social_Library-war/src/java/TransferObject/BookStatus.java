/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TransferObject;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Назар
 */
@Entity
@Table(name = "BOOK_STATUS")
@NamedQueries({@NamedQuery(name = "BookStatus.findAll", query = "SELECT b FROM BookStatus b"), @NamedQuery(name = "BookStatus.findById", query = "SELECT b FROM BookStatus b WHERE b.id = :id"), @NamedQuery(name = "BookStatus.findByStatus", query = "SELECT b FROM BookStatus b WHERE b.status = :status")})
public class BookStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Short id;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
    private Collection<Catalog> catalogCollection;

    public BookStatus() {
    }

    public BookStatus(Short id) {
        this.id = id;
    }

    public BookStatus(Short id, String status) {
        this.id = id;
        this.status = status;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<Catalog> getCatalogCollection() {
        return catalogCollection;
    }

    public void setCatalogCollection(Collection<Catalog> catalogCollection) {
        this.catalogCollection = catalogCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BookStatus)) {
            return false;
        }
        BookStatus other = (BookStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransferObject.BookStatus[id=" + id + "]";
    }

}
