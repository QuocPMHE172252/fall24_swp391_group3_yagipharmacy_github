/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Unit;
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
public class UnitDAO implements RowMapper<Unit> {

    @Override
    public Unit mapRow(ResultSet rs) throws SQLException {
        return Unit.builder()
                .unitId(rs.getLong("unit_id"))
                .unitName(rs.getString("unit_name"))
                .build();
    }

    @Override
    public boolean addNew(Unit t) throws SQLException, ClassNotFoundException {
        String sql = """
                 INSERT INTO [unit] (
                 unit_name) 
                 VALUES (?)
                 """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getUnitName());
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Unit> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [unit]";
        List<Unit> units = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                units.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return units;
    }

    @Override
    public Unit getById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [unit] WHERE unit_id = ?";
        Unit unit = Unit.builder().unitId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                unit = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return unit;
    }

    @Override
    public boolean updateById(String id, Unit t) throws SQLException, ClassNotFoundException {
        String sql = """
                 UPDATE [unit] SET 
                 unit_name = ? 
                 WHERE unit_id = ?
                 """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getUnitName());
            ps.setLong(2, CalculatorService.parseLong(id)); // Assuming CalculatorService is used for parsing ID
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM [unit] WHERE unit_id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, CalculatorService.parseLong(id));  // Assuming CalculatorService is used for parsing ID
            check = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
}
