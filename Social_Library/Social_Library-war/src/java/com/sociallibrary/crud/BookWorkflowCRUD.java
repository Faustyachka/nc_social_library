/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.crud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.Entities.*;
import com.sociallibrary.EntitiesInterfaces.BookWorkflowDAO;
/**
 *
 * @author Антон
 */
public class BookWorkflowCRUD implements BookWorkflowDAO{
    
    private Connection connection;

    public BookWorkflowCRUD() {
        connection = ConnectionProvider.getConnection();
    }

    public void createBookWorkflow(BookWorkflow bookWorkflow) {
        try {
                String sqlRequest =
                        "INSERT INTO Book_Workflow (ID, Name)  values(?, '?')";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setInt(1, bookWorkflow.getId());
            ps.setString(2, bookWorkflow.getWorkflow());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public BookWorkflow readBookWorkflow(int id) {
        BookWorkflow role = new BookWorkflow();
        try {
                String sqlRequest =
                        "SELECT * FROM Book_Workflow WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role.setId(rs.getInt("id"));
                role.setWorkflow(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    public void updateBookWorkflow(BookWorkflow bookWorkflow) {
        try {
            String sqlRequest = "UPDATE Book_Workflow SET WORKFLOW='?' WHERE ID=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            String[] bookWorkflowParams = new String[2];
            bookWorkflowParams = bookWorkflow.toStringList().toArray(bookWorkflowParams);
            ps.setString(1, bookWorkflowParams[1]);
            ps.setString(2, bookWorkflowParams[0]);

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBookWorkflow(int id) {
        try {
            String sqlRequest = "DELETE FROM Book_Workflow WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
