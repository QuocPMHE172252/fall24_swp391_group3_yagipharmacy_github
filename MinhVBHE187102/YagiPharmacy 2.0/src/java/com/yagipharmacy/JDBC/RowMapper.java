/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.JDBC;

/**
 *
 * @author admin
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs) throws SQLException;

    boolean addNew(T t) throws SQLException, ClassNotFoundException;

    List<T> getAll() throws SQLException, ClassNotFoundException;

    T getById(String id) throws SQLException, ClassNotFoundException;

    boolean updateById(String id, T t) throws SQLException, ClassNotFoundException;

    boolean deleteById(String id) throws SQLException, ClassNotFoundException;
}
