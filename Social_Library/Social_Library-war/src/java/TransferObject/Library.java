/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TransferObject;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author mazafaka
 */
@Entity
@Table(name = "LIBRARY")
@NamedQueries({@NamedQuery(name = "Library.findAll", query = "SELECT l FROM Library l"), @NamedQuery(name = "Library.findById", query = "SELECT l FROM Library l WHERE l.id = :id"), @NamedQuery(name = "Library.findByIsbn", query = "SELECT l FROM Library l WHERE l.isbn = :isbn"), @NamedQuery(name = "Library.findByTitle", query = "SELECT l FROM Library l WHERE l.title = :title"), @NamedQuery(name = "Library.findByCover", query = "SELECT l FROM Library l WHERE l.cover = :cover"), @NamedQuery(name = "Library.findByDescription", query = "SELECT l FROM Library l WHERE l.description = :description"), @NamedQuery(name = "Library.findByPages", query = "SELECT l FROM Library l WHERE l.pages = :pages"), @NamedQuery(name = "Library.findByAuthor", query = "SELECT l FROM Library l WHERE l.author = :author"), @NamedQuery(name = "Library.findByGenre", query = "SELECT l FROM Library l WHERE l.genre = :genre")})
public class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ISBN")
    private String isbn;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "COVER")
    private String cover;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PAGES")
    private Integer pages;
    @Column(name = "AUTHOR")
    private Long author;
    @Column(name = "GENRE")
    private Integer genre;
    @OneToMany(mappedBy = "book")
    private List<Catalog> catalogList;
    @JoinColumn(name = "USERS", referencedColumnName = "ID")
    @ManyToOne
    private Users users;
    @OneToMany(mappedBy = "book")
    private List<BookGenre> bookGenreList;
    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthorList;
    @OneToMany(mappedBy = "book")
    private List<Rating> ratingList;

    public Library() {
    }

    public Library(Long id) {
        this.id = id;
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

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
        this.genre = genre;
    }

    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<BookGenre> getBookGenreList() {
        return bookGenreList;
    }

    public void setBookGenreList(List<BookGenre> bookGenreList) {
        this.bookGenreList = bookGenreList;
    }

    public List<BookAuthor> getBookAuthorList() {
        return bookAuthorList;
    }

    public void setBookAuthorList(List<BookAuthor> bookAuthorList) {
        this.bookAuthorList = bookAuthorList;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
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
