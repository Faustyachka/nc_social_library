/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TransferObjectInterface;

import TransferObject.Library;

/**
 *
 * @author mazafaka
 */
public interface LibraryDAO {

     public void createLibrary(Library library);
     public Library readLibrary(int id);
     public void updateLibrary(Library libraryOld, Library libraryNew);
     public void deleteLibrary(Library library);

}
