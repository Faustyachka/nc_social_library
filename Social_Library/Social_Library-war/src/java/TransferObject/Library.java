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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Назар
 */
@Entity
@Table(name = "LIBRARY")
@NamedQueries({@NamedQuery(name = "Library.findAll", query = "SELECT l FROM Library l"), @NamedQuery(name = "Library.findById", query = "SELECT l FROM Library l WHERE l.id = :id"), @NamedQuery(name = "Library.findByIsbn", query = "SELECT l FROM Library l WHERE l.isbn = :isbn"), @NamedQuery(name = "Library.findByTitle", query = "SELECT l FROM Library l WHERE l.title = :title"), @NamedQuery(name = "Library.findByCover", query = "SELECT l FROM Library l WHERE l.cover = :cover"), @NamedQuery(name = "Library.findByDescription", query = "SELECT l FROM Library l WHERE l.description = :description"), @NamedQuery(name = "Library.findByPages", query = "SELECT l FROM Library l WHERE l.pages = :pages")})
public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "ISBN")
    private String isbn;
    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @Column(name = "COVER")
    private String cover;
    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "PAGES")
    private int pages;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Collection<Catalog> catalogCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Collection<Rating> ratingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Collection<BookAuthor> bookAuthorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Collection<BookGenre> bookGenreCollection;
    @JoinColumn(name = "WORKFLOW", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private BookWorkflow workflow;
    @JoinColumn(name = "USERS", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Users users;

    public Library() {
    }

    public Library(Long id) {
        this.id = id;
    }

    public Library(Long id, String isbn, String title, String cover, String description, int pages) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.cover = cover;
        this.description = description;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Collection<Catalog> getCatalogCollection() {
        return catalogCollection;
    }

    public void setCatalogCollection(Collection<Catalog> catalogCollection) {
        this.catalogCollection = catalogCollection;
    }

    public Collection<Rating> getRatingCollection() {
        return ratingCollection;
    }

    public void setRatingCollection(Collection<Rating> ratingCollection) {
        this.ratingCollection = ratingCollection;
    }

    public Collection<BookAuthor> getBookAuthorCollection() {
        return bookAuthorCollection;
    }

    public void setBookAuthorCollection(Collection<BookAuthor> bookAuthorCollection) {
        this.bookAuthorCollection = bookAuthorCollection;
    }

    public Collection<BookGenre> getBookGenreCollection() {
        return bookGenreCollection;
    }

    public void setBookGenreCollection(Collection<BookGenre> bookGenreCollection) {
        this.bookGenreCollection = bookGenreCollection;
    }

    public BookWorkflow getWorkflow() {
        return workflow;
    }

    public void setWorkflow(BookWorkflow workflow) {
        this.workflow = workflow;
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
        if (!(object instanceof Library)) {
            return false;
        }
        Library other = (Library) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransferObject.Library[id=" + id + "]";
    }

}
