/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.ProductCategory;
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
public class ProductCategoryDAO implements RowMapper<ProductCategory> {

    @Override
    public ProductCategory mapRow(ResultSet rs) throws SQLException, ClassNotFoundException {

        Long productCategoryId = rs.getLong("product_category_id");
        Long productCategoryParentId = rs.getLong("product_category_parent_id");
        Long productCategoryLevel = rs.getLong("product_category_level");
        String productCategoryCode = rs.getString("product_category_code");
        String productCategoryName = rs.getString("product_category_name");
        String productCategoryDetail = rs.getString("product_category_detail");
        boolean isDeleted = rs.getBoolean("is_deleted");
        ProductCategory parent = new ProductCategory();
        if (productCategoryParentId != null && productCategoryParentId != 0) {
            parent = new ProductCategoryDAO().getById(productCategoryParentId.toString());
        }
        return ProductCategory.builder()
                .productCategoryId(productCategoryId)
                .productCategoryParentId(productCategoryParentId)
                .productCategoryLevel(productCategoryLevel)
                .productCategoryCode(productCategoryCode)
                .productCategoryName(productCategoryName)
                .productCategoryDetail(productCategoryDetail)
                .isDeleted(isDeleted)
                .parentProductCategory(parent)
                .build();

    }

    @Override
    public boolean addNew(ProductCategory t) throws SQLException, ClassNotFoundException {
        String sql = """
                INSERT INTO [product_category] (
                    product_category_parent_id,
                    product_category_level,
                    product_category_code,
                    product_category_name,
                    product_category_detail,
                    is_deleted
                )
                VALUES (?,?,?,?,?,?)
                """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getProductCategoryParentId());
            ps.setObject(2, t.getProductCategoryLevel());
            ps.setObject(3, t.getProductCategoryCode());
            ps.setObject(4, t.getProductCategoryName());
            ps.setObject(5, t.getProductCategoryDetail());
            ps.setObject(6, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<ProductCategory> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
                SELECT *
                FROM [product_category]
                """;
        List<ProductCategory> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
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
    public ProductCategory getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                SELECT *
                FROM [product_category] WHERE product_category_id = ?
                """;
        ProductCategory productCategory = ProductCategory.builder()
                .productCategoryId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                productCategory = mapRow(rs);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productCategory;
    }

    @Override
    public boolean updateById(String id, ProductCategory t) throws SQLException, ClassNotFoundException {
        String sql = """
                UPDATE [product_category]
                SET
                    product_category_parent_id = ?,
                    product_category_level = ?,
                    product_category_code = ?,
                    product_category_name = ?,
                    product_category_detail = ?,
                    is_deleted = ?
                WHERE
                    product_category_id = ?;
                """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getProductCategoryParentId());
            ps.setObject(2, t.getProductCategoryLevel());
            ps.setObject(3, t.getProductCategoryCode());
            ps.setObject(4, t.getProductCategoryName());
            ps.setObject(5, t.getProductCategoryDetail());
            ps.setObject(6, t.isDeleted());
            ps.setObject(7, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                DELETE FROM [product_category]
                WHERE product_category_id = ?
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

    public boolean updateStatusById(String id, String status) throws SQLException, ClassNotFoundException {
        String sql = "  update [product_category] set [is_deleted] = ? where [product_category_id] =  ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(status));
            ps.setObject(2, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    public List<ProductCategory> getListChildren() throws SQLException, ClassNotFoundException {
        String sql = """
                SELECT * 
                FROM product_category 
                WHERE product_category_id NOT IN (
                    SELECT DISTINCT product_category_parent_id 
                    FROM product_category
                    WHERE product_category_parent_id IS NOT NULL
                )
                AND is_deleted = 0;
                """;
        List<ProductCategory> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
