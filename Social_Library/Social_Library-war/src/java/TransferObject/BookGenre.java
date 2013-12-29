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
@Table(name = "BOOK_GENRE")
@NamedQueries({@NamedQuery(name = "BookGenre.findAll", query = "SELECT b FROM BookGenre b"), @NamedQuery(name = "BookGenre.findById", query = "SELECT b FROM BookGenre b WHERE b.id = :id")})
public class BookGenre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "GENRE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Genre genre;
    @JoinColumn(name = "BOOK", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Library book;

    public BookGenre() {
    }

    public BookGenre(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Library getBook() {
        return book;
    }

    public void setBook(Library book) {
        this.book = book;
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
        if (!(object instanceof BookGenre)) {
            return false;
        }
        BookGenre other = (BookGenre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransferObject.BookGenre[id=" + id + "]";
    }

}
