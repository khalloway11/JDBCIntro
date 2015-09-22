/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booksample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Keiji
 */
public class SQLPrepStatementBuilder implements PrepStatementBuilderStrategy {

    @Override
    public PreparedStatement buildDeleteStatement(Connection conn_loc, String tableName, List colDescriptors, String operator, Object target) throws SQLException{
        PreparedStatement pstmt = null;
        
        return pstmt;
    }

    @Override
    public PreparedStatement buildUpdateStatement(Connection conn_loc, String tableName, List colDescriptors, String whereField) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement buildInsertStatement(Connection conn_loc, String tableName, List colDescriptors, String whereField) throws SQLException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
