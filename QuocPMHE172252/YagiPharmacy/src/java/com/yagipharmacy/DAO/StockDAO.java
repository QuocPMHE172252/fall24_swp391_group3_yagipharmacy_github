/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.ProductUnit;
import com.yagipharmacy.entities.QuanOfProductUnit;
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
        stock.setQuantity(rs.getLong("quantity"));
        stock.setProductId(rs.getLong("product_id"));
        stock.setUnitId(rs.getLong("unit_id"));
        stock.setMfgDate(new Date(mfgLong));
        stock.setExpDate(new Date(expLong));
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
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getBatchCode());
            ps.setObject(2, t.getQuantity());
            ps.setObject(3, t.getProductId());
            ps.setObject(4, t.getUnitId());
            ps.setObject(5, t.getMfgDate().getTime() + "");
            ps.setObject(6, t.getExpDate().getTime() + "");
            ps.setObject(7, t.isDeleted());
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
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

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
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

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
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getBatchCode());
            ps.setObject(2, t.getQuantity());
            ps.setObject(3, t.getProductId());
            ps.setObject(4, t.getUnitId());
            ps.setObject(5, t.getMfgDate().getTime() + "");
            ps.setObject(6, t.getExpDate().getTime() + "");
            ps.setObject(7, t.isDeleted());
            ps.setObject(8, CalculatorService.parseLong(id));
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
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<Stock> getByBatchCode(String batchCode) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [stock] WHERE batch_code = ?";
        List<Stock> stocks = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, batchCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stocks.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stocks;
    }

    public List<Stock> getByProductIdAndUnitId(String productId, String unitId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [stock] WHERE product_id = ? and unit_id = ?";
        List<Stock> stocks = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(productId));
            ps.setObject(2, CalculatorService.parseLong(unitId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stocks.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stocks;
    }

    public Long getNotOutOfDateSumQuantityOfProductUnit(String productId, String unitId) throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT SUM(quantity) AS total_quantity
                     FROM stock
                     WHERE product_id = ? AND unit_id = ? AND is_deleted = 0 AND cast(EXP_date as real)-(?)>0;
                     """;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(productId));
            ps.setObject(2, CalculatorService.parseLong(unitId));
            ps.setObject(3, new Date().getTime());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("total_quantity");
            }
        }
        return 0L;
    }

    public List<Stock> getAllNotOutOfDateByProductIdAndUnitId(String productId, String unitId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [stock] WHERE product_id = ? AND unit_id = ? AND is_deleted = 0 AND cast(EXP_date as int)-(?)>0;";
        List<Stock> stocks = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(productId));
            ps.setObject(2, CalculatorService.parseLong(unitId));
            ps.setObject(3, new Date().getTime());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stocks.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stocks;
    }

    public boolean updateStockAsyncById(Connection conn, Stock stock, String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     update stock set quantity = ? where stock_id =?
                     """;
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setObject(1, stock.getQuantity());
        stmt.setObject(2, CalculatorService.parseLong(id));
        int rowE = stmt.executeUpdate();
        return rowE > 0;
    }
    
    public List<Stock> getAllNotOutOfDateAsyncByProductIdAndUnitId(Connection conn,String productId, String unitId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [stock] WHERE product_id = ? AND unit_id = ? AND is_deleted = 0 AND cast(EXP_date as int)-(?)>0;";
        List<Stock> stocks = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(productId));
            ps.setObject(2, CalculatorService.parseLong(unitId));
            ps.setObject(3, new Date().getTime());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                stocks.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stocks;
    }
    public boolean updateTransStock(String productId,String unitId,String quantity) throws SQLException, ClassNotFoundException{
        List<QuanOfProductUnit> quanOfProductUnits = new ArrayList<>();
        Product findingProduct = new ProductDAO().getById(productId);
        for (ProductUnit pu : findingProduct.getProductUnits()) {
            quanOfProductUnits.add(QuanOfProductUnit.builder()
                    .productUnit(pu)
                    .totalQuan(getNotOutOfDateSumQuantityOfProductUnit(productId, unitId))
                    .build());
        }
        ProductUnit curentProductUnit = new ProductUnitDAO().getByProductIdAndUnitId(productId, unitId);
        for (QuanOfProductUnit quanOfProductUnit : quanOfProductUnits) {
            
        }
        return true;
    }
}
