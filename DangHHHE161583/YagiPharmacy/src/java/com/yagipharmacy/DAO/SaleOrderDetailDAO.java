/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.yagipharmacy.entities.SaleOrderDetail;
import com.yagipharmacy.entities.Unit;

/**
 *
 * @author admin
 */
public class SaleOrderDetailDAO implements RowMapper<SaleOrderDetail> {

    @Override
    public SaleOrderDetail mapRow(ResultSet rs) throws SQLException, ClassNotFoundException{
        Product findingProduct = new ProductDAO().getById(rs.getLong("product_id")+"");
        Unit findingUnit = new UnitDAO().getById(rs.getLong("unit_id")+"");
        SaleOrderDetail saleOrderDetail = new SaleOrderDetail();
        saleOrderDetail.setOrderDetailId(rs.getLong("order_detail_id"));
        saleOrderDetail.setSaleOrderId(rs.getLong("sale_order_id"));
        saleOrderDetail.setProductId(rs.getLong("product_id"));
        saleOrderDetail.setUnitId(rs.getLong("unit_id"));
        saleOrderDetail.setQuantity(rs.getLong("quantity"));
        saleOrderDetail.setBatchCode(rs.getString("batch_code"));
        saleOrderDetail.setDeleted(rs.getBoolean("is_deleted"));
        saleOrderDetail.setProduct(findingProduct);
        saleOrderDetail.setUnit(findingUnit);
        return saleOrderDetail;
    }

    @Override
    public boolean addNew(SaleOrderDetail t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [sale_order_detail] (
                     sale_order_id, 
                     product_id, 
                     unit_id, 
                     quantity, 
                     batch_code, 
                     is_deleted) 
                     VALUES (?, ?, ?, ?, ?, ?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, t.getSaleOrderId());
            ps.setLong(2, t.getProductId());
            ps.setLong(3, t.getUnitId());
            ps.setObject(4, t.getQuantity());
            ps.setString(5, t.getBatchCode());
            ps.setBoolean(6, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<SaleOrderDetail> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [sale_order_detail]";
        List<SaleOrderDetail> saleOrderDetails = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                saleOrderDetails.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleOrderDetails;
    }

    @Override
    public SaleOrderDetail getById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [sale_order_detail] WHERE order_detail_id = ?";
        SaleOrderDetail saleOrderDetail = null;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, CalculatorService.parseLong(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    saleOrderDetail = mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleOrderDetail;
    }

    @Override
    public boolean updateById(String id, SaleOrderDetail t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [sale_order_detail] SET 
                     sale_order_id = ?, 
                     product_id = ?, 
                     unit_id = ?, 
                     quantity = ?, 
                     batch_code = ?, 
                     is_deleted = ?
                     WHERE order_detail_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, t.getSaleOrderId());
            ps.setLong(2, t.getProductId());
            ps.setLong(3, t.getUnitId());
            ps.setLong(4, t.getQuantity());
            ps.setString(5, t.getBatchCode());
            ps.setBoolean(6, t.isDeleted());
            ps.setLong(7, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM [sale_order_detail] WHERE order_detail_id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<SaleOrderDetail> getListBySaleOrderId(String saleOrderId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [sale_order_detail] WHERE sale_order_id = ?";
        List<SaleOrderDetail> saleOrderDetails = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(saleOrderId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                saleOrderDetails.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleOrderDetails;
    }
}
