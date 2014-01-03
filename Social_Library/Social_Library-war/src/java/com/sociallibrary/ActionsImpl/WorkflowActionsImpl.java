/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.ActionsImpl;

import com.sociallibrary.Entities.BookWorkflow;
import com.sociallibrary.connection.ConnectionProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mazafaka
 */
public class WorkflowActionsImpl
{
    private Connection connection;

    public WorkflowActionsImpl() {
        connection = ConnectionProvider.getConnection();
    }

    public List<BookWorkflow> getAllWorkflows()
    {
        List<BookWorkflow> roles = new ArrayList<BookWorkflow>();
        try {
                String sqlRequest =
                        "SELECT * FROM Book_Workflow";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                BookWorkflow role = new BookWorkflow();
                role.setId(rs.getInt("id"));
                role.setWorkflow(rs.getString("name"));
                roles.add(role);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

}
