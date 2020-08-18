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
public class ConnectionFailureException extends RuntimeException {

    public ConnectionFailureException() {
        super();
    }
   
    public ConnectionFailureException(String message) {
        super(message);
    }

    public ConnectionFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionFailureException(Throwable cause) {
        super(cause);
    }
    
    
    
}
