/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rafael.odontosoft.modelo.entidade;

/**
 *
 * @author Luiz Rafael
 */
public enum SexoType {

    M("Masculino")
    ,F("Feminino");
    private String descricao;
    

    private SexoType(String descricao){
        this.descricao = descricao;    
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
