/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionsInterfaces;
import TransferObject.Library;
import java.util.List;

/**
 *
 * @author Антон
 */
public interface LibraryActions {

    public List<Library> searchBooksByParameter(String where, String what);
    public List<Library> getBooksByIdInInterval(long from, long to);

}
