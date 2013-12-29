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
@Table(name = "GENRE")
@NamedQueries({@NamedQuery(name = "Genre.findAll", query = "SELECT g FROM Genre g"), @NamedQuery(name = "Genre.findById", query = "SELECT g FROM Genre g WHERE g.id = :id"), @NamedQuery(name = "Genre.findByGenre", query = "SELECT g FROM Genre g WHERE g.genre = :genre")})
public class Genre implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "GENRE")
    private String genre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private Collection<BookGenre> bookGenreCollection;

    public Genre() {
    }

    public Genre(Integer id) {
        this.id = id;
    }

    public Genre(Integer id, String genre) {
        this.id = id;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Collection<BookGenre> getBookGenreCollection() {
        return bookGenreCollection;
    }

    public void setBookGenreCollection(Collection<BookGenre> bookGenreCollection) {
        this.bookGenreCollection = bookGenreCollection;
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
        if (!(object instanceof Genre)) {
            return false;
        }
        Genre other = (Genre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransferObject.Genre[id=" + id + "]";
    }

}
