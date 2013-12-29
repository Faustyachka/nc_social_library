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

@Entity
@Table(name = "AUTHOR")
@NamedQueries({@NamedQuery(name = "Author.findAll", query = "SELECT a FROM Author a"), @NamedQuery(name = "Author.findById", query = "SELECT a FROM Author a WHERE a.id = :id"), @NamedQuery(name = "Author.findByAuthor", query = "SELECT a FROM Author a WHERE a.author = :author")})
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "AUTHOR")
    private String author;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Collection<BookAuthor> bookAuthorCollection;

    public Author() {
    }

    public Author(Long id) {
        this.id = id;
    }

    public Author(Long id, String author) {
        this.id = id;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Collection<BookAuthor> getBookAuthorCollection() {
        return bookAuthorCollection;
    }

    public void setBookAuthorCollection(Collection<BookAuthor> bookAuthorCollection) {
        this.bookAuthorCollection = bookAuthorCollection;
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
        if (!(object instanceof Author)) {
            return false;
        }
        Author other = (Author) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransferObject.Author[id=" + id + "]";
    }
}
