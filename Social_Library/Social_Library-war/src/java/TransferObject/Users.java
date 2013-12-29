/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TransferObject;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Назар
 */
@Entity
@Table(name = "USERS")
@NamedQueries({@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"), @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"), @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"), @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"), @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"), @NamedQuery(name = "Users.findByLogin", query = "SELECT u FROM Users u WHERE u.login = :login"), @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"), @NamedQuery(name = "Users.findByGender", query = "SELECT u FROM Users u WHERE u.gender = :gender"), @NamedQuery(name = "Users.findByConfirmed", query = "SELECT u FROM Users u WHERE u.confirmed = :confirmed"), @NamedQuery(name = "Users.findByBanned", query = "SELECT u FROM Users u WHERE u.banned = :banned"), @NamedQuery(name = "Users.findByRegistrationDate", query = "SELECT u FROM Users u WHERE u.registrationDate = :registrationDate"), @NamedQuery(name = "Users.findByNotify", query = "SELECT u FROM Users u WHERE u.notify = :notify")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @Column(name = "LOGIN")
    private String login;
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "GENDER")
    private Short gender;
    @Basic(optional = false)
    @Column(name = "CONFIRMED")
    private short confirmed;
    @Basic(optional = false)
    @Column(name = "BANNED")
    private short banned;
    @Basic(optional = false)
    @Column(name = "REGISTRATION_DATE")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;
    @Basic(optional = false)
    @Column(name = "NOTIFY")
    private short notify;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Catalog> catalogCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Rating> ratingCollection;
    @JoinColumn(name = "ROLE", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Role role;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Library> libraryCollection;

    public Users() {
    }

    public Users(Long id) {
        this.id = id;
    }

    public Users(Long id, String firstName, String lastName, String email, String login, String password, short confirmed, short banned, Date registrationDate, short notify) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.confirmed = confirmed;
        this.banned = banned;
        this.registrationDate = registrationDate;
        this.notify = notify;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public short getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(short confirmed) {
        this.confirmed = confirmed;
    }

    public short getBanned() {
        return banned;
    }

    public void setBanned(short banned) {
        this.banned = banned;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public short getNotify() {
        return notify;
    }

    public void setNotify(short notify) {
        this.notify = notify;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TransferObject.Users[id=" + id + "]";
    }

}
