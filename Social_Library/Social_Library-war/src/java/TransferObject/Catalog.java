/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TransferObject;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Назар
 */
@Entity
@Table(name = "CATALOG")
@NamedQueries({@NamedQuery(name = "Catalog.findAll", query = "SELECT c FROM Catalog c"), @NamedQuery(name = "Catalog.findById", query = "SELECT c FROM Catalog c WHERE c.id = :id"), @NamedQuery(name = "Catalog.findByEventTime", query = "SELECT c FROM Catalog c WHERE c.eventTime = :eventTime")})
public class Catalog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "EVENT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;
    @JoinColumn(name = "STATUS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private BookStatus status;
    @JoinColumn(name = "BOOK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Library book;
    @JoinColumn(name = "USERS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Users users;

    public Catalog() {
    }

    public Catalog(Long id) {
        this.id = id;
    }

    public Catalog(Long id, Date eventTime) {
        this.id = id;
        this.eventTime = eventTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Library getBook() {
        return book;
    }

    public void setBook(Library book) {
        this.book = book;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
        if (!(object instanceof Catalog)) {
            return false;
        }
        Catalog other = (Catalog) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransferObject.Catalog[id=" + id + "]";
    }

}
