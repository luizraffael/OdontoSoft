/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.rafael.odontosoft.framework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Luiz Rafael
 */
public interface QueryMapping<T> {
    
    void mapping(ResultSet rset) throws SQLException;
}
