package sociallibrary.entyti;

public class Users {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private int gender;
    private int confirmed;
    private int banned;
    private String registrationDate;
    private int notify;
    private int role;

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int danned) {
        this.banned = danned;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Users(int id, String firstName, String lastName, String email, String login, String password, int gender, int confirmed, int danned, String registrationDate, int notify, int role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.confirmed = confirmed;
        this.banned = danned;
        this.registrationDate = registrationDate;
        this.notify = notify;
        this.role = role;
    }

    public Users() {
    }
    
}
