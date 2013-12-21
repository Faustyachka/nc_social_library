package sociallibrary.entity;

public class Catalog {
    private int id;
    private int users;
    private int book;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public Catalog(int id, int users, int book, int status) {
        this.id = id;
        this.users = users;
        this.book = book;
        this.status = status;
    }

    public Catalog() {
    }
    
}
