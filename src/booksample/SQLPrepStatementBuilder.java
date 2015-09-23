/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booksample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Keiji
 */
public class SQLPrepStatementBuilder implements PrepStatementBuilderStrategy {

    @Override
    public PreparedStatement buildDeleteStatement(Connection conn_loc, String tableName, String colName, String whereField, Object target) throws SQLException{
        //syntax:
        //delete from [table] where [column] (<,<=,=,>=,>,!=,BETWEEN,LIKE,IN) [value]
        PreparedStatement pstmt = null;
        final StringBuffer sql = new StringBuffer("DELETE FROM ");
        sql.append(tableName);
        if((whereField != null) && (colName != null)){
            sql.append(" WHERE ");
            sql.append(colName); sql.append(" ");
            sql.append((String)target); sql.append(" = ");
            sql.append("?");
            final String finalSQL = sql.toString();
            System.out.println(finalSQL);
            pstmt = conn_loc.prepareStatement(finalSQL);
        }
        return pstmt;
    }

    @Override
    public PreparedStatement buildUpdateStatement(Connection conn_loc, String tableName, List whereColDescriptors, List operators, List targets) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreparedStatement buildInsertStatement(Connection conn_loc, String tableName, List colDescriptors, List colValues) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
