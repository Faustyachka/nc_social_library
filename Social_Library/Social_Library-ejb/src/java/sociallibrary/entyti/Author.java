package sociallibrary.entyti;

public class Author {
    private int id;
    private String author;

    public Author() {
    }

    public Author(int id, String author) {
        this.id = id;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
