/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.ProductImage;
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
public class ProductImageDAO implements RowMapper<ProductImage> {

    @Override
    public ProductImage mapRow(ResultSet rs) throws SQLException {
        return ProductImage.builder()
                .productImageId(rs.getLong("product_image_id"))
                .productId(rs.getLong("product_id"))
                .imageBase64(rs.getString("product_image"))
                .isMain(rs.getBoolean("is_main"))
                .isDeleted(rs.getBoolean("is_deleted"))
                .build();
    }

    @Override
    public boolean addNew(ProductImage t) throws SQLException, ClassNotFoundException {
        String sql = """
        INSERT INTO product_image (
            product_id,
            image_base64,
            is_main,
            is_deleted
        )
        VALUES (?, ?, ?, ?)
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setLong(1, t.getProductId());
            ps.setString(2, t.getImageBase64());
            ps.setBoolean(3, t.isMain());
            ps.setBoolean(4, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<ProductImage> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT *
        FROM product_image
    """;
        List<ProductImage> images = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                images.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }

    @Override
    public ProductImage getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT *
        FROM product_image
        WHERE product_image_id = ?
    """;
        ProductImage image = ProductImage.builder().productImageId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setLong(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                image = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public boolean updateById(String id, ProductImage t) throws SQLException, ClassNotFoundException {
        String sql = """
        UPDATE product_image
        SET
            product_id = ?,
            image_base64 = ?,
            is_main = ?,
            is_deleted = ?
        WHERE
            product_image_id = ?
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setLong(1, t.getProductId());
            ps.setString(2, t.getImageBase64());
            ps.setBoolean(3, t.isMain());
            ps.setBoolean(4, t.isDeleted());
            ps.setLong(5, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
        DELETE FROM product_image
        WHERE product_image_id = ?;
    """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setLong(1, CalculatorService.parseLong(id));
        check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

}
