package com.sociallibrary.crud;

import com.sociallibrary.Entities.Gender;
import com.sociallibrary.Entities.Genre;
import com.sociallibrary.EntitiesInterfaces.GenderDAO;
import com.sociallibrary.EntitiesInterfaces.GenreDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class GenderCRUD implements GenderDAO {

    public void createGender(Gender gender) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Genre readGender(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void updateGender(Gender genderOld, Gender genderNew) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteGender(Gender gender) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
