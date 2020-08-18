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
public class CreateDaoException extends RuntimeException {

    public CreateDaoException() {
        super();
    }

    public CreateDaoException(String message) {
        super(message);
    }

    public CreateDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreateDaoException(Throwable cause) {
        super(cause);
    }
    
}
