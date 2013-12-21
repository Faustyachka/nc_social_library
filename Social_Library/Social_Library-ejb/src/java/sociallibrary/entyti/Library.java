package sociallibrary.entyti;

public class Library {
    private int id;
    private String isbn;
    private String title;
    private String cover;
    private String description;
    private int pages;
    private int author;
    private int ganre;
    private int users;
    private int workflow;

    public int getWorkflow() {
        return workflow;
    }

    public void setWorkflow(int workflow) {
        this.workflow = workflow;
    }

    public Library() {
    }

    public Library(int id, String isbn, String title, String cover, String description, int pages, int author, int ganre, int users, int workflow) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.cover = cover;
        this.description = description;
        this.pages = pages;
        this.author = author;
        this.ganre = ganre;
        this.users = users;
        this.workflow = workflow;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }
    
    
}
