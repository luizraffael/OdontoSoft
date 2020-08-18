/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rafael.odontosoft.framework.dao;

/**
 *
 * @author Luiz Rafael
 */
public class UpdateDaoException extends RuntimeException {

    public UpdateDaoException() {
        super();
    }
   
    public UpdateDaoException(String message) {
        super(message);
    }

    public UpdateDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateDaoException(Throwable cause) {
        super(cause);
    }
    
    
    
}
