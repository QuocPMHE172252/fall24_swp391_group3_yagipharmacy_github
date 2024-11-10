/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.entities.ProductUnit;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class ProductUnitDAO implements RowMapper<ProductUnit> {

    @Override
    public ProductUnit mapRow(ResultSet rs) throws SQLException, ClassNotFoundException {
        ProductUnit parent = getById(rs.getLong("parent_id") + "");
        ProductUnit productUnit = new ProductUnit();
        productUnit.setProductUnitId(rs.getLong("product_unit_id"));
        productUnit.setParentId(rs.getLong("parent_id"));
        productUnit.setProductId(rs.getLong("product_id"));
        productUnit.setUnitId(rs.getLong("unit_id"));
        productUnit.setSellPrice(rs.getDouble("sell_price"));
        productUnit.setQuantityPerUnit(rs.getLong("quantity_per_unit"));
        productUnit.setProductUnitName(rs.getString("product_unit_name"));
        productUnit.setCanBeSold(rs.getBoolean("can_be_sold"));
        productUnit.setDeleted(rs.getBoolean("is_deleted"));
        productUnit.setParentProductUnit(parent);
        productUnit.setUnit(new UnitDAO().getById(rs.getString("unit_id")));
        return productUnit;
    }

    @Override
    public boolean addNew(ProductUnit t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [product_unit] (
                     parent_id, 
                     product_id, 
                     unit_id, 
                     sell_price, 
                     quantity_per_unit, 
                     product_unit_name, 
                     can_be_sold, 
                     is_deleted) 
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getParentId());
            ps.setObject(2, t.getProductId());
            ps.setObject(3, t.getUnitId());
            ps.setObject(4, t.getSellPrice());
            ps.setObject(5, t.getQuantityPerUnit());
            ps.setObject(6, t.getProductUnitName());
            ps.setObject(7, t.isCanBeSold());
            ps.setObject(8, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<ProductUnit> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [product_unit]";
        List<ProductUnit> productUnits = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productUnits.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productUnits;
    }

    @Override
    public ProductUnit getById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [product_unit] WHERE product_unit_id = ?";
        ProductUnit productUnit = null;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, CalculatorService.parseLong(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    productUnit = mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productUnit;
    }

    @Override
    public boolean updateById(String id, ProductUnit t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [product_unit] SET 
                     parent_id = ?, 
                     product_id = ?, 
                     unit_id = ?, 
                     sell_price = ?, 
                     quantity_per_unit = ?, 
                     product_unit_name = ?, 
                     can_be_sold = ?, 
                     is_deleted = ?
                     WHERE product_unit_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getParentId());
            ps.setObject(2, t.getProductId());
            ps.setObject(3, t.getUnitId());
            ps.setObject(4, t.getSellPrice());
            ps.setObject(5, t.getQuantityPerUnit());
            ps.setObject(6, t.getProductUnitName());
            ps.setObject(7, t.isCanBeSold());
            ps.setObject(8, t.isDeleted());
            ps.setObject(9, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM [product_unit] WHERE product_unit_id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<ProductUnit> getListByProductId(String productId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [product_unit] WHERE product_id = ?";
        List<ProductUnit> productUnits = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(productId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                productUnits.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productUnits;
    }
    
    public Long addNewAndGetKey(ProductUnit t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [product_unit] (
                     parent_id, 
                     product_id, 
                     unit_id, 
                     sell_price, 
                     quantity_per_unit, 
                     product_unit_name, 
                     can_be_sold, 
                     is_deleted) 
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                     """;
        Long check = 0L;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, t.getParentId());
            ps.setObject(2, t.getProductId());
            ps.setObject(3, t.getUnitId());
            ps.setObject(4, t.getSellPrice());
            ps.setObject(5, t.getQuantityPerUnit());
            ps.setObject(6, t.getProductUnitName());
            ps.setObject(7, t.isCanBeSold());
            ps.setObject(8, t.isDeleted());
            int affectedRows = ps.executeUpdate();
            if(affectedRows>0){
                try(ResultSet rs = ps.getGeneratedKeys()) {
                    if(rs.next()){
                        check = rs.getLong(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
    public ProductUnit getByProductIdAndUnitId(String pid,String uid) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [product_unit] WHERE product_id = ? and unit_id = ?";
        ProductUnit productUnit = null;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, CalculatorService.parseLong(pid));
            ps.setLong(2, CalculatorService.parseLong(uid));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    productUnit = mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productUnit;
    }
}
