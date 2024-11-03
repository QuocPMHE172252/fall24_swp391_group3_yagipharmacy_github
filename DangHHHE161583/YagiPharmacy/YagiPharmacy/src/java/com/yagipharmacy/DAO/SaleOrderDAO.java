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
import com.yagipharmacy.entities.SaleOrder;
import com.yagipharmacy.entities.SaleOrderDetail;
import java.util.Date;

/**
 *
 * @author admin
 */
public class SaleOrderDAO implements RowMapper<SaleOrder> {

    @Override
    public SaleOrder mapRow(ResultSet rs) throws SQLException, ClassNotFoundException {
        SaleOrderDetailDAO saleOrderDetailDAO = new SaleOrderDetailDAO();
        List<SaleOrderDetail> saleOrderDetails = saleOrderDetailDAO.getListBySaleOrderId(rs.getLong("sale_order_id")+"");
        SaleOrder saleOrder = new SaleOrder();
        Long longDate = CalculatorService.parseLong(rs.getString("created_date"));
        saleOrder.setSaleOrderId(rs.getLong("sale_order_id"));
        saleOrder.setOrderBy(rs.getLong("order_by"));
        saleOrder.setReceiverName(rs.getString("receiver_name"));
        saleOrder.setReceiverPhone(rs.getString("receiver_phone"));
        saleOrder.setReceiverEmail(rs.getString("receiver_email"));
        saleOrder.setProvince(rs.getString("province"));
        saleOrder.setDistrict(rs.getString("district"));
        saleOrder.setCommune(rs.getString("commune"));
        saleOrder.setSpecificAddress(rs.getString("specific_address"));
        saleOrder.setTotalPrice(rs.getDouble("total_price"));
        saleOrder.setCreatedDate(new Date(longDate));
        saleOrder.setPaid(rs.getBoolean("is_paid"));
        saleOrder.setDeleted(rs.getBoolean("is_deleted"));
        saleOrder.setSaleOrderDetails(saleOrderDetails);
        return saleOrder;
    }

    @Override
    public boolean addNew(SaleOrder t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [sale_order] (
                     order_by, 
                     receiver_name, 
                     receiver_phone, 
                     receiver_email, 
                     province, 
                     district, 
                     commune, 
                     specific_address, 
                     total_price, 
                     created_date, 
                     is_paid, 
                     is_deleted) 
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getOrderBy());
            ps.setObject(2, t.getReceiverName());
            ps.setObject(3, t.getReceiverPhone());
            ps.setObject(4, t.getReceiverEmail());
            ps.setObject(5, t.getProvince());
            ps.setObject(6, t.getDistrict());
            ps.setObject(7, t.getCommune());
            ps.setObject(8, t.getSpecificAddress());
            ps.setObject(9, t.getTotalPrice());
            ps.setObject(10, t.getCreatedDate().getTime()+"");
            ps.setObject(11, t.isPaid());
            ps.setObject(12, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<SaleOrder> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [sale_order]";
        List<SaleOrder> saleOrders = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                saleOrders.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleOrders;
    }

    @Override
    public SaleOrder getById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [sale_order] WHERE sale_order_id = ?";
        SaleOrder saleOrder = null;
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, CalculatorService.parseLong(id));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    saleOrder = mapRow(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleOrder;
    }

    @Override
    public boolean updateById(String id, SaleOrder t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [sale_order] SET 
                     order_by = ?, 
                     receiver_name = ?, 
                     receiver_phone = ?, 
                     receiver_email = ?, 
                     province = ?, 
                     district = ?, 
                     commune = ?, 
                     specific_address = ?, 
                     total_price = ?, 
                     created_date = ?, 
                     is_paid = ?, 
                     is_deleted = ?
                     WHERE sale_order_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getOrderBy());
            ps.setObject(2, t.getReceiverName());
            ps.setObject(3, t.getReceiverPhone());
            ps.setObject(4, t.getReceiverEmail());
            ps.setObject(5, t.getProvince());
            ps.setObject(6, t.getDistrict());
            ps.setObject(7, t.getCommune());
            ps.setObject(8, t.getSpecificAddress());
            ps.setObject(9, t.getTotalPrice());
            ps.setObject(10, t.getCreatedDate().getTime()+"");
            ps.setObject(11, t.isPaid());
            ps.setObject(12, t.isDeleted());
            ps.setObject(13, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM [sale_order] WHERE sale_order_id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
    public Long addNewAndGetKey(SaleOrder t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [sale_order] (
                     order_by, 
                     receiver_name, 
                     receiver_phone, 
                     receiver_email, 
                     province, 
                     district, 
                     commune, 
                     specific_address, 
                     total_price, 
                     created_date, 
                     is_paid, 
                     is_deleted) 
                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                     """;
        int check = 0;
        Long key = -1L;
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setObject(1, t.getOrderBy());
            ps.setObject(2, t.getReceiverName());
            ps.setObject(3, t.getReceiverPhone());
            ps.setObject(4, t.getReceiverEmail());
            ps.setObject(5, t.getProvince());
            ps.setObject(6, t.getDistrict());
            ps.setObject(7, t.getCommune());
            ps.setObject(8, t.getSpecificAddress());
            ps.setObject(9, t.getTotalPrice());
            ps.setObject(10, t.getCreatedDate().getTime()+"");
            ps.setObject(11, t.isPaid());
            ps.setObject(12, t.isDeleted());
            check = ps.executeUpdate();
            if(check>0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    key = rs.getLong(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }
    public boolean updateStatusById(String id, String status) throws SQLException, ClassNotFoundException {
        String sql = " Update [sale_order] set [is_deleted] = ? where [sale_order_id] = " + id;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, CalculatorService.parseLong(status));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<SaleOrder> fillterOrder(String status, String isPaid) throws SQLException, ClassNotFoundException {
        String sql = " SELECT *  FROM sale_order   where is_deleted like '%" + status + "%' and [is_paid] like '%" + isPaid + "%'";

        List<SaleOrder> saleOrders = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                saleOrders.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saleOrders;
    }

}
