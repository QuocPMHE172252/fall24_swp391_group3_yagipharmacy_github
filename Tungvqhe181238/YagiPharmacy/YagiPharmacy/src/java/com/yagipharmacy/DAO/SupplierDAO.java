/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Supplier;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class SupplierDAO implements RowMapper<Supplier> {

    @Override
    public Supplier mapRow(ResultSet rs) throws SQLException {
        return Supplier.builder()
                .supplierId(rs.getLong("supplier_id"))
                .supplierCode(rs.getString("supplier_code"))
                .supplierName(rs.getString("supplier_name"))
                .supplierCountryCode(rs.getString("supplier_country_code"))
                .isDeleted(rs.getBoolean("is_deleted"))
                .build();
    }

    @Override
    public boolean addNew(Supplier t) throws SQLException, ClassNotFoundException {
        String sql = """
        INSERT INTO supplier (
            supplier_code,
            supplier_name,
            supplier_country_code,
            is_deleted
        )
        VALUES (?,?,?,?)
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getSupplierCode());
            ps.setString(2, t.getSupplierName());
            ps.setString(3, t.getSupplierCountryCode());
            ps.setBoolean(4, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
                        SELECT *
                        FROM supplier
                    """;
        List<Supplier> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Supplier getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT *
        FROM supplier
        WHERE supplier_id = ? AND is_deleted = 0  -- Only retrieve non-deleted supplier
    """;
        Supplier supplier = Supplier.builder().build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                supplier = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public boolean updateById(String id, Supplier t) throws SQLException, ClassNotFoundException {
        String sql = """
        UPDATE supplier
        SET
            supplier_code = ?,
            supplier_name = ?,
            supplier_country_code = ?,
            is_deleted = ?
        WHERE
            supplier_id = ?
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getSupplierCode());
            ps.setObject(2, t.getSupplierName());
            ps.setObject(3, t.getSupplierCountryCode());
            ps.setObject(4, t.isDeleted());
            ps.setObject(5, t.getSupplierId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
        DELETE FROM supplier
        WHERE supplier_id = ?
    """;
    int check = 0;
    try (Connection con = SQLServerConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
      ps.setLong(1, CalculatorService.parseLong(id));
      check = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return check > 0;
    }
}
