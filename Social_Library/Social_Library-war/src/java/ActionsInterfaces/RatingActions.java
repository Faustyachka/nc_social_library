/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ActionsInterfaces;

import TransferObject.Rating;
import java.util.List;

/**
 *
 * @author Антон
 */
public interface RatingActions {

    public List<Rating> getRatingsByBookId(long id);
    public Rating getRatingsByBookAndUserIds(long userId, long bookId);

}
