/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TransferObjectInterface;

import TransferObject.Author;

/**
 *
 * @author mazafaka
 */
public interface AuthorDAO {

     public void createAuthor(Author author);
     public Author readAuthor(int id);
     public void updateAuthor(Author authorOld, Author authorNew);
     public void deleteAuthor(Author author);
}
