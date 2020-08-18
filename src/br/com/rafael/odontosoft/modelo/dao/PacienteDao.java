/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.rafael.odontosoft.modelo.dao;

import br.com.rafael.odontosoft.framework.dao.CreateDaoException;
import br.com.rafael.odontosoft.framework.dao.DaoHelper;
import br.com.rafael.odontosoft.framework.dao.QueryMapping;
import br.com.rafael.odontosoft.framework.dao.UpdateDaoException;
import br.com.rafael.odontosoft.modelo.entidade.Paciente;
import br.com.rafael.odontosoft.modelo.entidade.SexoType;

//import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Luiz Rafael
 */
public class PacienteDao {
    
    private DaoHelper daoHelper;
    
    public PacienteDao(){
        daoHelper = new DaoHelper();
    }
    
    public void inserir(Paciente paciente) throws CreateDaoException {
   
        Connection conn = null;
        //PreparedStatement stmt = null;

        try {
            daoHelper.beginTransaction();
           //conn  = daoHelper.getConnectionFromContext();
           
            
            //ResultSet rset = null;
           
            
            /* o de baixo é melhor
            int index = 0;
            
            stmt = conn.prepareStatement("INSERT INTO PACIENTE (nome, rg, cpf, sexo) VALUES (? , ? , ? , ?  )", PreparedStatement.RETURN_GENERATED_KEYS );
            
            stmt.setString( ++ index, paciente.getNome());
            stmt.setString( ++ index, paciente.getRg());
            stmt.setString( ++ index, paciente.getCpf());
            stmt.setString( ++ index, paciente.getSexo().toString());
            
            stmt.executeUpdate();
            
            rset = stmt.getGeneratedKeys();
            
            if(rset.next()){
                paciente.setId(rset.getLong(1));
            }
            */
            
            long id =  daoHelper.executePreparedUpdateAndReturnGeneratedKeys("INSERT INTO PACIENTE (nome, rg, cpf, sexo) VALUES (? , ? , ? , ?  )"
                     , paciente.getNome()
                     , paciente.getRg()
                     , paciente.getCpf()
                     , paciente.getSexo().toString());
            
            paciente.setId(id);
            
            inserirPacienteEndereco(paciente);
            inserirPacienteContato(paciente);
            
            daoHelper.endTransaction();
            /*  
            stmt = conn.createStatement();
           // codigo feio e inseguro, por isso usamos o prepareStatement :)
            stmt.executeUpdate("INSERT INTO PACIENTE (nome, rg, cpf, sexo) VALUES "
                    + "( '" + paciente.getNome()
                    + "','" + paciente.getRg()
                    + "','" + paciente.getCpf()
                    + "','" + paciente.getSexo()
                    + "' )");
            */
        } catch (SQLException e) {
            //remover depois, implementar mecanismo de exception handling
            daoHelper.rollbackTransaction();
           throw new CreateDaoException("Não foi possivel realizar a transação", e);
        } 

    }
    private void inserirPacienteEndereco(Paciente paciente) throws SQLException{
       
         EnderecoDao enderecoDao = new EnderecoDao();
         
         enderecoDao.inserir(paciente.getEndereco());
         
                
         String query = "INSERT INTO PACIENTE_ENDERECO (paciente_id, endereco_id) VALUES (? , ? )";
         daoHelper.executePreparedUpdateAndReturnGeneratedKeys(query
                 , paciente.getId()
                 , paciente.getEndereco().getId());
/*
          try {
            daoHelper.beginTransaction();
            conn  = daoHelper.getConnectionFromContext();
            
            int index = 0;
            
            stmt = conn.prepareStatement("INSERT INTO PACIENTE_ENDERECO (paciente_id, endereco_id) VALUES (? , ? )");
            
            stmt.setLong( ++ index, paciente.getId());
            stmt.setLong( ++ index, paciente.getEndereco().getId());
        
            stmt.executeUpdate();
            
          
            
            daoHelper.endTransaction();
            /*  
            stmt = conn.createStatement();
           // codigo feio e inseguro, por isso usamos o prepareStatement :)
            stmt.executeUpdate("INSERT INTO PACIENTE (nome, rg, cpf, sexo) VALUES "
                    + "( '" + paciente.getNome()
                    + "','" + paciente.getRg()
                    + "','" + paciente.getCpf()
                    + "','" + paciente.getSexo()
                    + "' )");
            */
         /*
        } catch (SQLException e) {
            //remover depois, implementar mecanismo de exception handling
           throw new CreateDaoException("Não foi possivel realizar a transação", e);
        } finally {
            daoHelper.releaseAll(conn, stmt);
        }
        */
    }
    
    private void inserirPacienteContato (Paciente paciente) throws SQLException{
        
        ContatoDao contatoDao = new ContatoDao();
        
        contatoDao.inserir(paciente.getContato());
        
        String query = "INSERT INTO PACIENTE_CONTATO (paciente_id, contato_id) VALUES (? , ? )";
         daoHelper.executePreparedUpdateAndReturnGeneratedKeys(query
                 , paciente.getId()
                 , paciente.getContato().getId());
        
        
    }
       public void atualizar(Paciente paciente) throws UpdateDaoException{
       
        String query = "UPDATE PACIENTE SET nome = ?, rg = ?, cpf = ?, sexo = ?, WHERE id = ?";
      
        try {
            daoHelper.beginTransaction();
            
            daoHelper.executePreparedUpdate(query
                     , paciente.getNome()
                     , paciente.getRg()
                     , paciente.getCpf()
                     , paciente.getSexo().toString()
                     , paciente.getId());
              
            
            atualizarPacienteEndereco(paciente);
            atualizarPacienteContato(paciente);
         
            daoHelper.endTransaction();
           } catch (SQLException e) {
               daoHelper.rollbackTransaction();
               throw new UpdateDaoException("Não foi possivel atualizar paciente", e);
           }
        
    }
    
    private void atualizarPacienteEndereco(Paciente paciente) throws SQLException{
        EnderecoDao enderecoDao = new EnderecoDao();
        
        enderecoDao.atualizar(paciente.getEndereco());
    }
    private void atualizarPacienteContato(Paciente paciente) throws SQLException{
        ContatoDao contatoDao = new ContatoDao();
        contatoDao.atualizar(paciente.getContato());
    }
    
       
    public void delete(Paciente paciente) throws UpdateDaoException{
       
        String query = "DELETE FROM PACIENTE WHERE id = ?";
        
        
        
        try {
            daoHelper.beginTransaction();
            daoHelper.executePreparedUpdate(query, paciente.getId());
            daoHelper.endTransaction();
        } catch (SQLException e) {
            daoHelper.rollbackTransaction();
            throw new UpdateDaoException("Não foi possivel excluir paciente", e);
        }
    }
    
    public List <Paciente> listaTodosPacientes(){
        final List<Paciente> pacientes = new ArrayList<Paciente>();
        try {
            daoHelper.executePreparedQuery("SELECT * FROM paciente", new QueryMapping<Paciente>(){

                @Override
                public void mapping(ResultSet rset) throws SQLException {
                    while(rset.next()){
                        Paciente paciente = new Paciente();
                        
                        paciente.setId(rset.getInt("id"));
                        paciente.setNome(rset.getString("nome"));
                        paciente.setRg(rset.getString("rg"));
                        paciente.setCpf(rset.getString("cpf"));
                        paciente.setSexo(SexoType.valueOf(rset.getString("sexo") )) ;
                        
                        pacientes.add(paciente);
                        
                    }
                }
            });
        } catch (SQLException e) {
        }
        return pacientes;
    }

}
