/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Price;
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
public class PriceDAO implements RowMapper<Price> {

    @Override
    public Price mapRow(ResultSet rs) throws SQLException {
        return Price.builder()
                .priceId(rs.getLong("price_id"))
                .productId(rs.getLong("product_id"))
                .amountMoney(rs.getDouble("amount_money"))
                .unitCode(rs.getString("unit_code"))
                .isDeleted(rs.getBoolean("is_deleted"))
                .build();
    }

    @Override
    public boolean addNew(Price t) throws SQLException, ClassNotFoundException {
        String sql = """
        INSERT INTO price (
            product_id,
            amount_money,
            unit_code,
            is_deleted
        )
        VALUES (?, ?, ?, ?)
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getProductId());
            ps.setObject(2, t.getAmountMoney());
            ps.setObject(3, t.getUnitCode());
            ps.setObject(4, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Price> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT *
        FROM price
    """;
        List<Price> prices = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                prices.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prices;
    }

    @Override
    public Price getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT *
        FROM price
        WHERE price_id = ?
    """;
        Price price = Price.builder().build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                price = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    @Override
    public boolean updateById(String id, Price t) throws SQLException, ClassNotFoundException {
        String sql = """
        UPDATE price
        SET
            product_id = ?,
            amount_money = ?,
            unit_code = ?,
            is_deleted = ?
        WHERE
            price_id = ?
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getProductId());
            ps.setObject(2, t.getAmountMoney());
            ps.setObject(3, t.getUnitCode());
            ps.setObject(4, t.isDeleted());
            ps.setObject(5, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
        DELETE FROM price
        WHERE
            price_id = ?
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

}
