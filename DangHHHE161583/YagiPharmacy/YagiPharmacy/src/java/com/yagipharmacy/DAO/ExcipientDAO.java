/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.entities.Excipient;
import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
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
public class ExcipientDAO implements RowMapper<Excipient> {

    @Override
    public Excipient mapRow(ResultSet rs) throws SQLException {
        return Excipient.builder()
                .excipientId(rs.getLong("excipient_id"))
                .excipientName(rs.getString("excipient_name"))
                .isDeleted(rs.getBoolean("is_deleted"))
                .build();
    }

    @Override
    public boolean addNew(Excipient t) throws SQLException, ClassNotFoundException {
        String sql = """
                 INSERT INTO [excipient] (
                 excipient_name, 
                 is_deleted) 
                 VALUES (?, ?)
                 """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getExcipientName());
            ps.setObject(2, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Excipient> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [excipient]";
        List<Excipient> excipients = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                excipients.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excipients;
    }

    @Override
    public Excipient getById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [excipient] WHERE excipient_id = ?";
        Excipient excipient = Excipient.builder()
                .excipientId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                excipient = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return excipient;
    }

    @Override
    public boolean updateById(String id, Excipient t) throws SQLException, ClassNotFoundException {
        String sql = """
                 UPDATE [excipient] SET 
                 excipient_name = ?, 
                 is_deleted = ? 
                 WHERE excipient_id = ?
                 """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, t.getExcipientName());
            ps.setObject(2, t.isDeleted());
            ps.setObject(3, CalculatorService.parseLong(id));
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM [excipient] WHERE excipient_id = ?";
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
