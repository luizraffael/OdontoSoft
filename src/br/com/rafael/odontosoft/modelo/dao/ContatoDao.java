/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rafael.odontosoft.modelo.dao;

import br.com.rafael.odontosoft.framework.dao.DaoHelper;
import br.com.rafael.odontosoft.modelo.entidade.Contato;
import java.sql.SQLException;

/**
 *
 * @author Luiz Rafael
 */
public class ContatoDao {
    
    private DaoHelper daoHelper;

    public ContatoDao() {
        daoHelper = new DaoHelper();
        
    }
       
    String query = "INSERT INTO CONTATO (email, telefone, fax, celular) VALUES (? , ? , ? , ?)";
   
    public void inserir(Contato contato) throws SQLException{
        long id = 01;
        daoHelper.executePreparedUpdateAndReturnGeneratedKeys(query
                ,contato.getEmail()
                ,contato.getTelefone()
                ,contato.getFax()
                ,contato.getCelular());
        
        contato.setId(id);
        
    }
    public void atualizar(Contato contato) throws SQLException{
       
        String query = "UPDATE CONTATO SET email = ?, telefone = ?, fax = ?, celular = ?, WHERE id = ?";
        
        daoHelper.executePreparedUpdate(query
                ,contato.getEmail()
                ,contato.getTelefone()
                ,contato.getFax()
                ,contato.getCelular()
                ,contato.getId());
    }
    public void delete(Contato contato) throws SQLException{
       
        String query = "DELETE FROM CONTATO WHERE id = ?";
        
        
        
        try {
            daoHelper.beginTransaction();
            daoHelper.executePreparedUpdate(query, contato.getId());
            daoHelper.endTransaction();
        } catch (SQLException e) {
            daoHelper.rollbackTransaction();
        }
    }
    
}
