/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rafael.odontosoft.apresentacao.paciente;

import br.com.rafael.odontosoft.modelo.entidade.Contato;
import br.com.rafael.odontosoft.modelo.entidade.Endereco;
import br.com.rafael.odontosoft.modelo.entidade.Paciente;
import br.com.rafael.odontosoft.modelo.service.PacienteService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author Luiz Rafael
 */
public class PacienteActionListener implements ActionListener, MouseInputListener{
    private PacienteFrm frm;
    private PacienteService service;
    
    public PacienteActionListener(PacienteFrm frm) {
        this.frm = frm;
        service = new PacienteService();
        adcionaListener();
    }
    public void adcionaListener(){
        frm.getjB_alterar().addActionListener(this);
        frm.getjB_incluir().addActionListener(this);
        frm.getjB_excluir().addActionListener(this);
        frm.getjB_cancelar().addActionListener(this);
        frm.getjB_salvar().addActionListener(this);
        
    }

    private void enableButtonsToSaveAction(){
         enableOrDesableButtonsToEdit(true);
    
    }
    private void desableButtonsToSaveAction(){
        enableOrDesableButtonsToEdit(false);
    
    }
    private void enableOrDesableButtonsToEdit(boolean enable){
        frm.getjB_incluir().setEnabled(!enable);
        frm.getjB_alterar().setEnabled(!enable);
        frm.getjB_excluir().setEnabled(!enable);
        frm.getjB_salvar().setEnabled(enable);
        frm.getjB_cancelar().setEnabled(enable);
                
    
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getActionCommand().equals("Incluir")){
            incluir();
        }else if (event.getActionCommand().equals("Alterar")){
        
        }else if (event.getActionCommand().equals("Excluir")){
        
        }else if (event.getActionCommand().equals("Salvar")){
            salvar();
        }else if (event.getActionCommand().equals("Cancelar")){
        
        }
    }
    private Paciente formToPaciente(){
        Paciente paciente = new Paciente();
        if( !"".equals(frm.getjL_id().getText())){
            paciente.setId(Long.parseLong(frm.getjL_id().getText()));
        }
            
        paciente.setNome(frm.getjT_nome().getText());
        paciente.setCpf(frm.getjT_cpf().getText());
        paciente.setRg(frm.getjT_rg().getText());
        
        paciente.setContato(formToContato());
        paciente.setEndereco(formToEndereco());
       
        return paciente;
    }
    
    private Endereco formToEndereco(){
        Endereco endereco = new Endereco();
        if( !"".equals(frm.getjL_idEndereco().getText())){
            endereco.setId(Long.parseLong(frm.getjL_idEndereco().getText()));
        }
        endereco.setBairro(frm.getjT_bairro().getText());
        endereco.setCidade(frm.getjT_cidade().getText());
        endereco.setEndereco(frm.getjT_endereco().getText());
        endereco.setCep(frm.getjT_cep().getText());
        
        return endereco;
    
    }
    
    
    
    private Contato formToContato(){
                 
        Contato contato = new Contato();
        if( !"".equals(frm.getjL_idContato().getText())){
            contato.setId(Long.parseLong(frm.getjL_idContato().getText()));
        }
        contato.setEmail(frm.getjT_email().getText());
        contato.setTelefone(frm.getjT_telefone().getText());
        contato.setCelular(frm.getjT_celular().getText());
        
        return contato;
        
    }
    
    
    
    private void incluir(){
        enableButtonsToSaveAction();
    }
    
    private void salvar(){
        service.salvar(formToPaciente());
        JOptionPane.showMessageDialog(frm, "Paciente Salvo", "Salvar", JOptionPane.INFORMATION_MESSAGE);
        desableButtonsToSaveAction();
    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
