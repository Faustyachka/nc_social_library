package sociallibrary.entyti;

public class BookAuthor {
    private int id;
    private int book;
    private int author;

    public BookAuthor() {
    }

    public BookAuthor(int id, int book, int author) {
        this.id = id;
        this.book = book;
        this.author = author;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
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
    
    
}
