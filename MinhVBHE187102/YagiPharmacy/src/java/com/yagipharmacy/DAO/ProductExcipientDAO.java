/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Excipient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.yagipharmacy.entities.ProductExcipient;

/**
 *
 * @author admin
 */
public class ProductExcipientDAO implements RowMapper<ProductExcipient> {
    
    @Override
    public ProductExcipient mapRow(ResultSet rs) throws SQLException,ClassNotFoundException {
        ExcipientDAO excipientDAO = new ExcipientDAO();
        Excipient excipient = excipientDAO.getById(rs.getLong("excipient_id")+"");
        ProductExcipient productExcipient = new ProductExcipient();
        productExcipient.setProductExcipientId(rs.getLong("product_excipient_id"));
        productExcipient.setProductId(rs.getLong("product_id"));
        productExcipient.setExcipientId(rs.getLong("excipient_id"));
        productExcipient.setQuantity(rs.getDouble("quantity"));
        productExcipient.setUnitMeasurement(rs.getString("unit_measurement"));
        productExcipient.setExcipient(excipient);
        return productExcipient;
    }
    
    @Override
    public boolean addNew(ProductExcipient t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [product_excipient] (
                     product_id, 
                     excipient_id, 
                     quantity, 
                     unit_measurement) 
                     VALUES (?, ?, ?, ?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, t.getProductId());
            ps.setLong(2, t.getExcipientId());
            ps.setDouble(3, t.getQuantity());
            ps.setString(4, t.getUnitMeasurement());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
    @Override
    public List<ProductExcipient> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [product_excipient]";
        List<ProductExcipient> productExcipients = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                productExcipients.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productExcipients;
    }
    
    @Override
    public ProductExcipient getById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [product_excipient] WHERE product_excipient_id = ?";
        ProductExcipient productExcipient = null;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setLong(1, CalculatorService.parseLong(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    productExcipient = mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productExcipient;
    }
    
    @Override
    public boolean updateById(String id, ProductExcipient t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [product_excipient] SET 
                     product_id = ?, 
                     excipient_id = ?, 
                     quantity = ?, 
                     unit_measurement = ?
                     WHERE product_excipient_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, t.getProductId());
            ps.setLong(2, t.getExcipientId());
            ps.setDouble(3, t.getQuantity());
            ps.setString(4, t.getUnitMeasurement());
            ps.setLong(5, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM [product_excipient] WHERE product_excipient_id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
    public List<ProductExcipient> getListByProductId(String productId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [product_excipient] WHERE product_id = ?";
        List<ProductExcipient> productExcipients = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(productId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                productExcipients.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productExcipients;
    }
    
}
