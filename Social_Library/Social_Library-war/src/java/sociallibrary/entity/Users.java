package sociallibrary.entity;

/**
 *
 * @author Felix
 */
public class Users {
    private int id;
    private String firsName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private int gender;
    private int confirmed;
    private int banned;
    private String registrationData;
    private int notify;
    private int role;

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getNotify() {
        return notify;
    }

    public void setNotify(int notify) {
        this.notify = notify;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationData() {
        return registrationData;
    }

    public void setRegistrationData(String registrationData) {
        this.registrationData = registrationData;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Users(int id, String firsName, String lastName, String email, String login, String password, int gender, int confirmed, int banned, String registrationData, int notify, int role) {
        this.id = id;
        this.firsName = firsName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.confirmed = confirmed;
        this.banned = banned;
        this.registrationData = registrationData;
        this.notify = notify;
        this.role = role;
    }

    public Users() {
    }
}
