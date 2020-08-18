/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rafael.odontosoft.modelo.dao;

import br.com.rafael.odontosoft.modelo.entidade.Contato;
import br.com.rafael.odontosoft.modelo.entidade.Endereco;
import br.com.rafael.odontosoft.modelo.entidade.Paciente;
import br.com.rafael.odontosoft.modelo.entidade.SexoType;
import java.util.List;

/**
 *
 * @author Luiz Rafael
 */
public class PacienteDaoTest {
    
    public static void main(String[] args){
    
        Paciente paciente = new Paciente("Katatau3", "333333333", "112111111", SexoType.M);
        Endereco end = new Endereco("Endereco Kata3",
                           "Cidade Kata3", 
                           "Bairro Kata3",
                           "CepKata3");
        Contato contato = new Contato();
        
        contato.setCelular("77333333");
        contato.setEmail("katatau@gmail.com");
        contato.setFax("77333333");
        contato.setTelefone("773333333");
        
        
        paciente.setContato(contato);
        paciente.setEndereco(end);
       
        PacienteDao dao = new PacienteDao();
        dao.inserir(paciente);
        
        List<Paciente> pacientes = dao.listaTodosPacientes();
        
        for (Paciente paciente2 : pacientes) {
            System.out.println("HERE" +paciente2);
        }
    
    }
    
}
