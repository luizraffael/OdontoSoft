/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rafael.odontosoft.modelo.service;

import br.com.rafael.odontosoft.modelo.dao.PacienteDao;
import br.com.rafael.odontosoft.modelo.entidade.Paciente;
import java.util.List;

/**
 *
 * @author Luiz Rafael
 */
public class PacienteService {
    
    private PacienteDao dao;
    
    public PacienteService(){
        dao = new PacienteDao();
    }
    
    public void salvar(Paciente paciente){
        if(paciente.getId() != 0){
            dao.atualizar(paciente);
        }else{
            dao.inserir(paciente);
        }
        
    }
    public List<Paciente> getPacientes(){
       return dao.listaTodosPacientes();
    }
}
