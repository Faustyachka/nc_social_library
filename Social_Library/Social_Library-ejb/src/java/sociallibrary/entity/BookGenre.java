package sociallibrary.entity;

public class BookGenre {
    private int id;
    private int book;
    private int genre;

    public BookGenre() {
    }

    public BookGenre(int id, int book, int ganre) {
        this.id = id;
        this.book = book;
        this.genre = ganre;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int ganre) {
        this.genre = ganre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
