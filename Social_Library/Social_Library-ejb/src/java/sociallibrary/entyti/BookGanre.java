package sociallibrary.entyti;

public class BookGanre {
    private int id;
    private int book;
    private int ganre;

    public BookGanre() {
    }

    public BookGanre(int id, int book, int ganre) {
        this.id = id;
        this.book = book;
        this.ganre = ganre;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    public int getGanre() {
        return ganre;
    }

    public void setGanre(int ganre) {
        this.ganre = ganre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
