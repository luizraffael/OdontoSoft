/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rafael.odontosoft.modelo.dao;

import br.com.rafael.odontosoft.framework.dao.CreateDaoException;
import br.com.rafael.odontosoft.framework.dao.DaoHelper;
import br.com.rafael.odontosoft.framework.dao.UpdateDaoException;
import br.com.rafael.odontosoft.modelo.entidade.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luiz Rafael
 */
public class EnderecoDao {
    
    private DaoHelper daoHelper;
    
    public EnderecoDao(){
        daoHelper = new DaoHelper();
    }
    public void inserir(Endereco endereco){
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        try {
            int i = 0;
            conn = daoHelper.getConnectionFromContext();
            pstmt = conn.prepareStatement("INSERT INTO endereco (endereco, cidade, cep, bairro) VALUES(?,?,?,?) ", 
                    PreparedStatement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(++i, endereco.getEndereco());
            pstmt.setString(++i, endereco.getCidade());
            pstmt.setString(++i, endereco.getCep());
            pstmt.setString(++i, endereco.getBairro());
            
            pstmt.executeUpdate();
  
            rset = pstmt.getGeneratedKeys();
            
            if(rset.next()){    
                endereco.setId(rset.getLong(1) );
            }
            
        } catch (SQLException e) {
            throw new CreateDaoException("Não foi possivel armazenar endereco" + endereco, e);
        }
        
    }
    public void atualizar(Endereco endereco) throws SQLException{
       
        String query = "UPDATE ENDERECO SET endereco = ?, cidade = ?, cep = ?, bairro = ?, WHERE id = ?";
        
        daoHelper.executePreparedUpdate(query
                ,endereco.getEndereco()
                ,endereco.getCidade()
                ,endereco.getCep()
                ,endereco.getBairro());
    }
    public void delete(Endereco endereco) throws SQLException{
       
        String query = "DELETE FROM ENDERECO WHERE id = ?";
        
        
        
        try {
            daoHelper.beginTransaction();
            daoHelper.executePreparedUpdate(query, endereco.getId());
            daoHelper.endTransaction();
        } catch (Exception e) {
            daoHelper.rollbackTransaction();
        }
    }
    
}
