package sociallibrary.entyti;

public class Rating {
    private int id;
    private int rate;
    private int users;
    private  int book;

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

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }

    public Rating(int id, int rate, int users, int book) {
        this.id = id;
        this.rate = rate;
        this.users = users;
        this.book = book;
    }

    public Rating() {
    }
    
}
