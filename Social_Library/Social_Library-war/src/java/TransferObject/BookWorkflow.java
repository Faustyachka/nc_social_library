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
@Table(name = "BOOK_WORKFLOW")
@NamedQueries({@NamedQuery(name = "BookWorkflow.findAll", query = "SELECT b FROM BookWorkflow b"), @NamedQuery(name = "BookWorkflow.findById", query = "SELECT b FROM BookWorkflow b WHERE b.id = :id"), @NamedQuery(name = "BookWorkflow.findByWorkflow", query = "SELECT b FROM BookWorkflow b WHERE b.workflow = :workflow")})
public class BookWorkflow implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "WORKFLOW")
    private String workflow;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workflow")
    private Collection<Library> libraryCollection;

    public BookWorkflow() {
    }

    public BookWorkflow(Integer id) {
        this.id = id;
    }

    public BookWorkflow(Integer id, String workflow) {
        this.id = id;
        this.workflow = workflow;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public Collection<Library> getLibraryCollection() {
        return libraryCollection;
    }

    public void setLibraryCollection(Collection<Library> libraryCollection) {
        this.libraryCollection = libraryCollection;
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
        if (!(object instanceof BookWorkflow)) {
            return false;
        }
        BookWorkflow other = (BookWorkflow) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransferObject.BookWorkflow[id=" + id + "]";
    }

}
