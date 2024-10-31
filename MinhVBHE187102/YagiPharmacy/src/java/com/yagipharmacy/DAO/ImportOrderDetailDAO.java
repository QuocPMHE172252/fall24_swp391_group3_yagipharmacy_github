/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.ImportOrderDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author admin
 */
public class ImportOrderDetailDAO implements RowMapper<ImportOrderDetail> {

    @Override
    public ImportOrderDetail mapRow(ResultSet rs) throws SQLException,ClassNotFoundException {
        Long longDate = CalculatorService.parseLong(rs.getString("import_date"));
        ImportOrderDetail importOrderDetail = new ImportOrderDetail();
        importOrderDetail.setImportOrderDetailId(rs.getLong("import_order_detail_id"));
        importOrderDetail.setBatchCode(rs.getString("batch_code"));
        importOrderDetail.setImportOrderId(rs.getLong("import_order_id"));
        importOrderDetail.setProductId(rs.getLong("product_id"));
        importOrderDetail.setUnitId(rs.getLong("unit_id"));
        importOrderDetail.setQuantity(rs.getLong("quantity"));
        importOrderDetail.setImportPrice(rs.getDouble("import_price"));
        importOrderDetail.setImportDate(new Date(longDate));
        importOrderDetail.setSupplierId(rs.getLong("supplier_id"));
        importOrderDetail.setProcessing(rs.getLong("processing"));
        importOrderDetail.setDeleted(rs.getBoolean("is_deleted"));
        importOrderDetail.setSupplier(new SupplierDAO().getById(rs.getLong("supplier_id")+""));
        return importOrderDetail;
    }

    @Override
    public boolean addNew(ImportOrderDetail t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [import_order_detail] (
                     batch_code, 
                     import_order_id, 
                     product_id, 
                     unit_id, 
                     quantity, 
                     import_price, 
                     import_date,
                     supplier_id,
                     processing,
                     is_deleted) 
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getBatchCode());
            ps.setObject(2, t.getImportOrderId());
            ps.setObject(3, t.getProductId());
            ps.setObject(4, t.getUnitId());
            ps.setObject(5, t.getQuantity());
            ps.setObject(6, t.getImportPrice());
            ps.setObject(7, t.getImportDate().getTime() + "");
            ps.setObject(8, t.getSupplierId());
            ps.setObject(9, t.getProcessing());
            ps.setObject(10, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<ImportOrderDetail> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [import_order_detail]";
        List<ImportOrderDetail> importOrderDetails = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                importOrderDetails.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return importOrderDetails;
    }

    @Override
    public ImportOrderDetail getById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [import_order_detail] WHERE import_order_detail_id = ?";
        ImportOrderDetail importOrderDetail = null;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, CalculatorService.parseLong(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    importOrderDetail = mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return importOrderDetail;
    }

    @Override
    public boolean updateById(String id, ImportOrderDetail t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [import_order_detail] SET 
                     batch_code = ?, 
                     import_order_id = ?, 
                     product_id = ?, 
                     unit_id = ?, 
                     quantity = ?, 
                     import_price = ?, 
                     import_date = ?,
                     supplier_id = ?,
                     processing = ?,
                     is_deleted = ?
                     WHERE import_order_detail_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getBatchCode());
            ps.setObject(2, t.getImportOrderId());
            ps.setObject(3, t.getProductId());
            ps.setObject(4, t.getUnitId());
            ps.setObject(5, t.getQuantity());
            ps.setObject(6, t.getImportPrice());
            ps.setObject(7, t.getImportDate().getTime() + "");
            ps.setObject(8, t.getSupplierId());
            ps.setObject(9, t.getProcessing());
            ps.setObject(10, t.isDeleted());
            ps.setObject(11, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM [import_order_detail] WHERE import_order_detail_id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<ImportOrderDetail> getByImportOderId(String importOderId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [import_order_detail] where import_order_id = ?";
        List<ImportOrderDetail> importOrderDetails = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(importOderId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                importOrderDetails.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return importOrderDetails;
    }
}
