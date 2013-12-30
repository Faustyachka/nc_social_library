/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionsInterfaces;
import TransferObject.Author;
import TransferObject.Library;
import TransferObject.Rating;
import java.util.List;

/**
 *
 * @author Антон
 */
public interface LibraryActions {

    public List<Library> searchBooksByParameter(String where, String what);
    public List<Library> searchBooksByStringMask(String where, String what);
    public List<Library> getBooksByIdInInterval(long from, long to);
    //public List<Library> getBooksByIdInInterval_Condition(long from, long to, String what, String value);
    public List<Author> getAuthorsList(long bookId);
    public List<Rating> getRatingsList(long bookId);
    public int getAverageRate(long bookId);

}
