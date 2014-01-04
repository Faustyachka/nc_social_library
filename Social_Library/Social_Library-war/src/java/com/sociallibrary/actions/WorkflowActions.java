/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sociallibrary.actions;

import com.sociallibrary.entity.BookWorkflow;
import com.sociallibrary.connection.ConnectionProvider;
import com.sociallibrary.iactions.IWorkflowActions;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nastya Pavlova
 */
public class WorkflowActions implements IWorkflowActions
{
    private Connection connection;

    public WorkflowActions()
    {
        connection = ConnectionProvider.getConnection();
    }

    public List<BookWorkflow> getAllWorkflows()
    {
        List<BookWorkflow> roles = new ArrayList<BookWorkflow>();
        try {

            String sqlRequest ="SELECT * FROM Book_Workflow";
            PreparedStatement ps = connection.prepareStatement(sqlRequest);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                BookWorkflow role = new BookWorkflow();
                role.setId(rs.getInt("id"));
                role.setWorkflow(rs.getString("name"));
                roles.add(role);
            }
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return roles;
    }
}
