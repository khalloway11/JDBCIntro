/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package booksample;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Keiji
 */
public class MySqlDb {
    //class variables
    private static final String SELECT_ALL = "SELECT * FROM ";
    private static final String SELECT_QUERY = "SELECT ? FROM ";
    private static final String SELECT_TARGET = "WHERE ? = ? ";
    private static final String DELETE_QUERY = "DELETE ? FROM ";
    private static final String DELETE_TARGET = "WHERE ? = ? ";
    private static final String INSERT_QUERY = "INSERT INTO ";
    private Connection conn;
    private final PrepStatementBuilderStrategy pstmtBuilder = new SQLPrepStatementBuilder();
    
    public void openConnection(String driverClass, String url, String userName, String password) throws Exception {
        Class.forName (driverClass);
	conn = DriverManager.getConnection(url, userName, password);
    }
    
    public void closeConnection() throws SQLException {
        conn.close();
    }
    
    public List<Map<String, Object>> findAllRecords(String tableName) throws SQLException{
        List<Map<String, Object>> records = new ArrayList<>();
        String sql = SELECT_ALL + tableName;

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData meta = rs.getMetaData();
        int colCount = meta.getColumnCount();
        
        while(rs.next()){
            Map<String, Object> record = new HashMap<>();
            for(int i = 1; i <= colCount; i++){
                record.put(meta.getColumnName(i), rs.getObject(i));
            }
            records.add(record);
        }
        
        return records;
    }
    
    public int deleteById(String tableName, String PKName, Object target) throws SQLException{
        //delete from [table] where [column] = [value]
        
        String sql = "DELETE FROM " + tableName + " WHERE " + PKName + "=";
        if(target instanceof String){
            sql += "\"" + (String)target + "\"";
        } else {
            sql += (String)target;
        }
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }
    
    public int prepDelete(String tableName, String targetCol, Object targetRecord) throws SQLException{
        //syntax:
        //delete from [table] where [column] (<,<=,=,>=,>,!=,BETWEEN,LIKE,IN) [value]
        int deleted = 0;
        PreparedStatement pstmt = pstmtBuilder.buildDeleteStatement(conn, tableName, targetCol, targetRecord);
        
        try{
            deleted = pstmt.executeUpdate();
        } catch (SQLException sqle) {
            System.out.println(sqle);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                pstmt.close();
                conn.close();
            } catch(Exception e) {
                System.out.println(e);
            }
        }
        return deleted;
    }
    
    public void update(String tableName, Object fieldName, Object newValue) throws SQLException{
        //syntax:
        //update [table] set [column] = [new value] 
        //where [column] = [old value]
    }
    
    public void createRecord(String tableName, Object[] newRecordInfo) throws SQLException{
        
    }
    
    public static void main(String[] args) throws Exception{
        MySqlDb db = new MySqlDb();
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
        List<Map<String, Object>> records = db.findAllRecords("author");
        for(Map record : records){
            System.out.println(record);
        }
        db.prepDelete("author", "author_id", 1);
        records = db.findAllRecords("author");
        for(Map record : records){
            System.out.println(record);
        }
        db.closeConnection();
    }
}
