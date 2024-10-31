/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.yagipharmacy.entities.Stock;
import java.util.Date;

/**
 *
 * @author admin
 */
public class StockDAO implements RowMapper<Stock> {

    @Override
    public Stock mapRow(ResultSet rs) throws SQLException {
        Stock stock = new Stock();
        Long mfgLong = CalculatorService.parseLong(rs.getString("MFG_date"));
        Long expLong = CalculatorService.parseLong(rs.getString("EXP_date"));
        stock.setStockId(rs.getLong("stock_id"));
        stock.setBatchCode(rs.getString("batch_code"));
        stock.setQuantity(rs.getInt("quantity"));
        stock.setProductId(rs.getLong("product_id"));
        stock.setUnitId(rs.getLong("unit_id"));
        stock.setMfgDate(new Date(mfgLong));
        stock.setExpDate(new Date(expLong ));
        stock.setDeleted(rs.getBoolean("is_deleted"));
        return stock;
    }

    @Override
    public boolean addNew(Stock t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [stock] (
                     batch_code, 
                     quantity, 
                     product_id, 
                     unit_id, 
                     MFG_date, 
                     EXP_date, 
                     is_deleted) 
                     VALUES (?, ?, ?, ?, ?, ?, ?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getBatchCode());
            ps.setInt(2, t.getQuantity());
            ps.setLong(3, t.getProductId());
            ps.setLong(4, t.getUnitId());
            ps.setString(5, t.getMfgDate().getTime()+"");
            ps.setString(6, t.getExpDate().getTime()+"");
            ps.setBoolean(7, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Stock> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [stock]";
        List<Stock> stocks = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                stocks.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stocks;
    }

    @Override
    public Stock getById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [stock] WHERE stock_id = ?";
        Stock stock = null;
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, CalculatorService.parseLong(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    stock = mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stock;
    }

    @Override
    public boolean updateById(String id, Stock t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [stock] SET 
                     batch_code = ?, 
                     quantity = ?, 
                     product_id = ?, 
                     unit_id = ?, 
                     MFG_date = ?, 
                     EXP_date = ?, 
                     is_deleted = ?
                     WHERE stock_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getBatchCode());
            ps.setInt(2, t.getQuantity());
            ps.setLong(3, t.getProductId());
            ps.setLong(4, t.getUnitId());
            ps.setString(5, t.getMfgDate().getTime()+"");
            ps.setString(6, t.getExpDate().getTime()+"");
            ps.setBoolean(7, t.isDeleted());
            ps.setLong(8, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM [stock] WHERE stock_id = ?";
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
