/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.EntitiesInterfaces;

import com.sociallibrary.Entities.*;

/**
 *
 * @author mazafaka
 */
public interface GenderDAO {

    public void createGender(Gender gender);
     public Genre readGender(int id);
     public void updateGender(Gender genderOld, Gender genderNew);
     public void deleteGender(Gender gender);

}
