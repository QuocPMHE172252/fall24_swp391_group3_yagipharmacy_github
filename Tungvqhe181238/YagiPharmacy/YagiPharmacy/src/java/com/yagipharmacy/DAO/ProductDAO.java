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
import java.util.Date;

/**
 *
 * @author admin
 */
public class ProductDAO implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs) throws SQLException {
        Date createdDate = new Date(CalculatorService.parseLong(rs.getString("created_date")));
        return Product.builder()
                .productId(rs.getLong("product_id"))
                .productCode(rs.getString("product_code"))
                .productCategoryId(rs.getInt("product_category_id"))
                .productCountryCode(rs.getString("product_country_code"))
                .supplierId(rs.getLong("supplier_id"))
                .productTarget(rs.getString("product_target"))
                .productName(rs.getString("product_name"))
                .dosageForm(rs.getString("dosage_form"))
                .productSpecification(rs.getString("product_specification"))
                .productExcipient(rs.getString("product_excipient"))
                .productDescription(rs.getString("product_desciption"))
                .createdDate(createdDate)
                .isDeleted(rs.getBoolean("is_deleted"))
                .build();
    }

    @Override
    public boolean addNew(Product t) throws SQLException, ClassNotFoundException {
        String sql = """
        INSERT INTO product (
            product_code,
            product_category_id,
            product_country_code,
            supplier_id,
            product_target,
            product_name,
            dosage_form,
            product_specification,
            product_excipient,
            product_desciption,
            created_date,
            is_deleted
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getProductCode());
            ps.setObject(2, t.getProductCategoryId());
            ps.setObject(3, t.getProductCountryCode());
            ps.setObject(4, t.getSupplierId());
            ps.setObject(5, t.getProductTarget());
            ps.setObject(6, t.getProductName());
            ps.setObject(7, t.getDosageForm());
            ps.setObject(8, t.getProductSpecification());
            ps.setObject(9, t.getProductExcipient());
            ps.setObject(10, t.getProductDescription());
            ps.setObject(11, t.getCreatedDate());
            ps.setObject(12, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Product> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT *
        FROM product
    """;
        List<Product> products = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT *
        FROM product
        WHERE product_id = ?
    """;
        Product product = Product.builder().productId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setLong(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean updateById(String id, Product t) throws SQLException, ClassNotFoundException {
        String sql = """
        UPDATE product
        SET
            product_code = ?,
            product_category_id = ?,
            product_country_code = ?,
            supplier_id = ?,
            product_target = ?,
            product_name = ?,
            dosage_form = ?,
            product_specification = ?,
            product_excipient = ?,
            product_desciption = ?,
            created_date = ?,
            is_deleted = ?
        WHERE
            product_id = ?
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getProductCode());
            ps.setObject(2, t.getProductCategoryId());
            ps.setObject(3, t.getProductCountryCode());
            ps.setObject(4, t.getSupplierId());
            ps.setObject(5, t.getProductTarget());
            ps.setObject(6, t.getProductName());
            ps.setObject(7, t.getDosageForm());
            ps.setObject(8, t.getProductSpecification());
            ps.setObject(9, t.getProductExcipient());
            ps.setObject(10, t.getProductDescription());
            ps.setObject(11, t.getCreatedDate());
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
        String sql = """
        DELETE FROM product
        WHERE
            product_id = ?;
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setLong(1, CalculatorService.parseLong(id)
            );
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

}
