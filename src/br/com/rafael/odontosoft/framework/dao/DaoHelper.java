/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rafael.odontosoft.framework.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luiz Rafael
 */
public class DaoHelper {
    
    private static final ThreadLocal<Connection> context = new ThreadLocal<Connection>();

    public Connection getConnection() throws SQLException {

        Connection conn = null;

        try {

            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/odontosoft", "root", "");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;

    }

    public void beginTransaction() throws SQLException{
        Connection conn = getConnection();
        conn.setAutoCommit(false);
        context.set(conn);
    }
    public void endTransaction()throws SQLException{
        commit ( getConnectionFromContext() );
        releaseTransaction();
    }
    
    public void releaseTransaction () throws SQLException{
        
        Connection conn = getConnectionFromContext();
        release(conn);
        context.remove();
        
    } 
     public void rollbackTransaction (){
        
        Connection conn;
        try {
            conn = getConnectionFromContext();
            rollback(conn);
            release(conn);
        } catch (SQLException ex) {
            Logger.getLogger(DaoHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        context.remove();
        
    } 
    
    public void commit(Connection conn)throws SQLException{
        conn.commit();
    }
    public void rollback(Connection conn)throws SQLException{
       
        if(conn != null) conn.rollback();
        
    }
    
    public Connection getConnectionFromContext()throws SQLException{
       Connection conn= context.get();
       
       if(conn == null) throw new SQLException("Transação invalida");
              
       if(conn.isClosed()) throw new SQLException("Transação invalida, conexão esta fechada");
        
        return context.get();
    }
    public <T> void executeQuery(String query, QueryMapping<T> queryMapping) throws SQLException{
        
        executeQuery(getConnection(), query, queryMapping);
    }
    
    public <T> void executeQuery(Connection conn
            , String query  
            , QueryMapping<T> queryMapping 
            , Object... params) throws SQLException{
        
        Statement stmt = null;
        ResultSet rset = null;
        
        try {
          stmt =  conn.createStatement();
          rset = stmt.executeQuery(query);
        } finally {
            release(rset);
            release(stmt);//
        }
        
       
       
    
    }
    
    public <T> void executePreparedQuery(String query  , QueryMapping<T> queryMapping , Object... params) throws SQLException{
            
        executePreparedQuery(getConnection(), query, queryMapping, params);
    
    }
    public <T> void executePreparedQuery(Connection conn
            , String query 
            , QueryMapping<T> queryMapping 
            , Object... params) throws SQLException{
        
         PreparedStatement pstmt = conn.prepareStatement(query);
         ResultSet rset = null;
         try {
          populatePreparedStatemente(params, pstmt);   
          pstmt =  conn.prepareStatement(query);
          rset = pstmt.executeQuery();
          queryMapping.mapping(rset);
        } finally {
            release(rset);
            release(pstmt);//
            
        }
         queryMapping.mapping(rset);
         
    }
            
            
    public long executePreparedUpdateAndReturnGeneratedKeys(String query, Object... params) throws SQLException {

        return executePreparedUpdateAndReturnGeneratedKeys(getConnectionFromContext(), query, params);

    }
    
    public long executePreparedUpdateAndReturnGeneratedKeys(Connection conn
                                                            , String query
                                                            , Object... params) throws SQLException{

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        long result = 01;
        
        try {
            pstmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            populatePreparedStatemente(params, pstmt);

            pstmt.executeUpdate();
            rset = pstmt.getGeneratedKeys();

            if (rset.next())result = rset.getLong(1);
            
        } finally{
            release(pstmt);
            release(rset);
        }
        return result;
    }

   

    public void executePreparedUpdate(String query, Object... params) throws SQLException {

        executePreparedUpdate(getConnectionFromContext(), query, params);
    }

    public void executePreparedUpdate(Connection conn, String query
                                                    , Object... params) throws SQLException{
       
        PreparedStatement pstmt = null;
       
      
        try {
            pstmt = conn.prepareStatement(query);
            populatePreparedStatemente(params, pstmt);

            pstmt.executeUpdate();
            
        } finally{
            release(pstmt);
          
        }
    }

    public void populatePreparedStatemente(Object[] params, PreparedStatement pstmt) throws SQLException {
        int i = 0;
        for (Object param : params) {
            pstmt.setObject(++i, param);
        }
    }
    
    public void release(Statement stmt) {
        if (stmt == null) 
            return;     
        try {
            stmt.close();
        } catch (SQLException e) {
        }
    }

    public void release(Connection conn) {
        if (conn == null)
            return;      
        try {
            conn.close();
        } catch (SQLException e) {
        }
    }
    public void release(ResultSet rset) {
        if (rset == null)
            return;      
        try {
            rset.close();
        } catch (SQLException e) {
        }
    }
    public void releaseAll(Connection conn, Statement stmt){
        release(stmt);
        release(conn);
    }
        public void releaseAll(Connection conn, Statement stmt, ResultSet rset){
        release(rset);
        release(conn);
    }

}
