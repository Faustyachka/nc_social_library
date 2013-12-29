/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TransferObjectInterface;
import TransferObject.BookWorkflow;
/**
 *
 * @author Назар
 */
public interface BookWorkflowDAO {

    public void createBookWorkflow(BookWorkflow bookWorkflow);
     public BookWorkflow readBookWorkflow(int id);
     public void updateBookWorkflow(BookWorkflow bookWorkflowOld, BookWorkflow bookWorkflowNew);
     public void deleteBookWorkflow(BookWorkflow bookWorkflow);

}
