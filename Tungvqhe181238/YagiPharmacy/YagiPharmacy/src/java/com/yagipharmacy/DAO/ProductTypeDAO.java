/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.ProductType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProductTypeDAO implements RowMapper<ProductType> {

    @Override
    public ProductType mapRow(ResultSet rs) throws SQLException {
        return ProductType.builder()
                .productTypeId(rs.getLong("product_type_id"))
                .categoryId(rs.getLong("category_id"))
                .productTypeCode(rs.getString("product_type_code"))
                .productTypeName(rs.getString("product_type_name"))
                .isDeleted(rs.getBoolean("is_deleted"))
                .build();
    }

    @Override
    public boolean addNew(ProductType t) throws SQLException, ClassNotFoundException {
        String sql = """
        INSERT INTO product_type (
            category_id,
            product_type_code,
            product_type_name,
            is_deleted
        )
        VALUES (?, ?, ?, ?)
    """;

        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getCategoryId()
            );
            ps.setString(2, t.getProductTypeCode());
            ps.setString(3, t.getProductTypeName());
            ps.setObject(4, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<ProductType> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
                        SELECT *
                        FROM product_type
                     """;

        List<ProductType> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ProductType getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT *
        FROM product_type
        WHERE product_type_id = ?
    """;

        ProductType productType = ProductType.builder().build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                productType = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productType;
    }

    @Override
    public boolean updateById(String id, ProductType t) throws SQLException, ClassNotFoundException {
        String sql = """
        UPDATE product_type
        SET
            category_id = ?,
            product_type_code = ?,
            product_type_name = ?,
            is_deleted = ?
        WHERE
            product_type_id = ?
    """;

        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getCategoryId());
            ps.setString(2, t.getProductTypeCode());
            ps.setString(3, t.getProductTypeName());
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
                        DELETE FROM product_type
                        WHERE product_type_id = ?
                     """;

        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
}
