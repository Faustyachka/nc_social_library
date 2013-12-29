/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TransferObject;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Назар
 */
@Entity
@Table(name = "RATING")
@NamedQueries({@NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r"), @NamedQuery(name = "Rating.findById", query = "SELECT r FROM Rating r WHERE r.id = :id"), @NamedQuery(name = "Rating.findByRate", query = "SELECT r FROM Rating r WHERE r.rate = :rate")})
public class Rating implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "RATE")
    private short rate;
    @JoinColumn(name = "BOOK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Library book;
    @JoinColumn(name = "USERS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Users users;

    public Rating() {
    }

    public Rating(Long id) {
        this.id = id;
    }

    public Rating(Long id, short rate) {
        this.id = id;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getRate() {
        return rate;
    }

    public void setRate(short rate) {
        this.rate = rate;
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
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransferObject.Rating[id=" + id + "]";
    }

}
