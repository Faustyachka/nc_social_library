/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author mazafaka
 */
@Entity
@Table(name = "BOOK_AUTHOR")
@NamedQueries({@NamedQuery(name = "BookAuthor.findAll", query = "SELECT b FROM BookAuthor b"), @NamedQuery(name = "BookAuthor.findById", query = "SELECT b FROM BookAuthor b WHERE b.id = :id")})
public class BookAuthor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @JoinColumn(name = "AUTHOR", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
    @JoinColumn(name = "BOOK", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Library book;

    public BookAuthor() {
    }

    public BookAuthor(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
        if (!(object instanceof BookAuthor)) {
            return false;
        }
        BookAuthor other = (BookAuthor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.BookAuthor[id=" + id + "]";
    }

}
