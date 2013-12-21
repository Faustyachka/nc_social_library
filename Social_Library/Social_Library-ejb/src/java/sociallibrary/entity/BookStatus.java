package sociallibrary.entity;

public class BookStatus {
    private int id;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BookStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public BookStatus() {
    }
    
}
