/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.entities.ImportOrder;
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
public class ImportOrderDAO implements RowMapper<ImportOrder> {

    @Override
    public ImportOrder mapRow(ResultSet rs) throws SQLException, ClassNotFoundException{
        ImportOrderDetailDAO importOrderDetailDAO = new ImportOrderDetailDAO();
        List<ImportOrderDetail> importOrderDetails = importOrderDetailDAO.getByImportOderId(rs.getLong("import_order_id")+"");
        ImportOrder importOrder = new ImportOrder();
        importOrder.setImportOrderId(rs.getLong("import_order_id"));
        importOrder.setImportOrderCode(rs.getString("import_order_code"));
        importOrder.setCreatedBy(rs.getLong("created_by"));
        importOrder.setCreatedDate(new Date(CalculatorService.parseLong(rs.getString("created_date"))));
        importOrder.setApprovedBy(rs.getLong("approved_by"));
        importOrder.setApprovedDate(new Date(CalculatorService.parseLong(rs.getString("approved_date"))));
        importOrder.setImportExpectedDate(new Date(CalculatorService.parseLong(rs.getString("import_expected_date"))));
        importOrder.setAccepted(rs.getBoolean("is_accepted"));
        importOrder.setRejectedReason(rs.getString("rejected_reason"));
        importOrder.setDeleted(rs.getBoolean("is_deleted"));
        importOrder.setImportOrderDetails(importOrderDetails);
        return importOrder;
    }

    @Override
    public boolean addNew(ImportOrder t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [import_order] (
                     import_order_code, 
                     created_by, 
                     created_date, 
                     approved_by, 
                     approved_date,
                     import_expected_date,
                     is_accepted, 
                     rejected_reason, 
                     is_deleted) 
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getImportOrderCode());
            ps.setLong(2, t.getCreatedBy());
            ps.setString(3, t.getCreatedDate().getTime() + "");
            ps.setLong(4, t.getApprovedBy());
            ps.setString(5, t.getApprovedDate().getTime()+"");
            ps.setString(6, t.getImportExpectedDate().getTime()+"");
            ps.setBoolean(7, t.isAccepted());
            ps.setString(8, t.getRejectedReason());
            ps.setBoolean(9, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<ImportOrder> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [import_order]";
        List<ImportOrder> importOrders = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                importOrders.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return importOrders;
    }

    @Override
    public ImportOrder getById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [import_order] WHERE import_order_id = ?";
        ImportOrder importOrder = ImportOrder.builder().build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, CalculatorService.parseLong(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    importOrder = mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return importOrder;
    }

    @Override
    public boolean updateById(String id, ImportOrder t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [import_order] SET 
                     import_order_code = ?, 
                     created_by = ?, 
                     created_date = ?, 
                     approved_by = ?, 
                     approved_date = ?,
                     import_expected_date = ?,
                     is_accepted = ?, 
                     rejected_reason = ?, 
                     is_deleted = ?
                     WHERE import_order_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getImportOrderCode());
            ps.setLong(2, t.getCreatedBy());
            ps.setString(3, t.getCreatedDate().getTime() + "");
            ps.setLong(4, t.getApprovedBy());
            ps.setString(5, t.getApprovedDate().getTime()+"");
            ps.setString(6, t.getImportExpectedDate().getTime()+"");
            ps.setBoolean(7, t.isAccepted());
            ps.setString(8, t.getRejectedReason());
            ps.setBoolean(9, t.isDeleted());
            ps.setLong(10, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM [import_order] WHERE import_order_id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

}
