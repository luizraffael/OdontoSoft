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
public class DeleteDaoException extends RuntimeException {

    public DeleteDaoException() {
        super();
    }
   
    public DeleteDaoException(String message) {
        super(message);
    }

    public DeleteDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteDaoException(Throwable cause) {
        super(cause);
    }
    
    
    
}
