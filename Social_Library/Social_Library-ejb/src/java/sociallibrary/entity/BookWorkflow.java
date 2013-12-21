package sociallibrary.entity;

public class BookWorkflow {
    private int id;
    private String workflow;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkflow() {
        return workflow;
    }

    public void setWorkflow(String workflow) {
        this.workflow = workflow;
    }

    public BookWorkflow(int id, String workflow) {
        this.id = id;
        this.workflow = workflow;
    }

    public BookWorkflow() {
    }
    
}
