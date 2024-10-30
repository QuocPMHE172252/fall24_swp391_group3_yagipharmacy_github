/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Excipient;
import com.yagipharmacy.entities.Product;
import com.yagipharmacy.entities.ProductCategory;
import com.yagipharmacy.entities.ProductExcipient;
import com.yagipharmacy.entities.ProductImage;
import com.yagipharmacy.entities.ProductUnit;
import com.yagipharmacy.entities.Supplier;
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
    public Product mapRow(ResultSet rs) throws SQLException, ClassNotFoundException{
        Date createdDate = new Date(CalculatorService.parseLong(rs.getString("created_date")));
        ProductCategoryDAO productCategoryDAO = new ProductCategoryDAO();
        ProductUnitDAO productUnitDAO = new ProductUnitDAO();
        ProductExcipientDAO productExcipientDAO = new ProductExcipientDAO();
        ProductImageDAO productImageDAO = new ProductImageDAO();
        SupplierDAO supplierDAO = new SupplierDAO();
        ProductCategory findingProductCate = productCategoryDAO.getById(rs.getLong("product_category_id")+"");
        List<ProductExcipient> productExcipients = productExcipientDAO.getListByProductId(rs.getLong("product_id")+"");
        List<ProductUnit> productUnits = productUnitDAO.getListByProductId(rs.getLong("product_id")+"");
        List<ProductImage> productImages = productImageDAO.getListByProductId(rs.getLong("product_id")+"");
        Supplier supplier = supplierDAO.getById(rs.getString("supplier_id"));
        return Product.builder()
                .productId(rs.getLong("product_id"))
                .productCode(rs.getString("product_code"))
                .productCategoryId(rs.getLong("product_category_id"))
                .productCountryCode(rs.getString("product_country_code"))
                .supplierId(rs.getLong("supplier_id"))
                .productTarget(rs.getString("product_target"))
                .productName(rs.getString("product_name"))
                .dosageForm(rs.getString("dosage_form"))
                .productSpecification(rs.getString("product_specification"))
                .productDescription(rs.getString("product_desciption"))
                .createdDate(createdDate)
                .isDeleted(rs.getBoolean("is_deleted"))
                .productCategory(findingProductCate)
                .productExcipients(productExcipients)
                .productUnits(productUnits)
                .productImages(productImages)
                .supplier(supplier)
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
            product_desciption,
            created_date,
            is_deleted
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
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
            ps.setObject(9, t.getProductDescription());
            ps.setObject(10, t.getCreatedDate());
            ps.setObject(11, t.isDeleted());
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
            ps.setObject(9, t.getProductDescription());
            ps.setObject(10, t.getCreatedDate());
            ps.setObject(11, t.isDeleted());
            ps.setObject(12, CalculatorService.parseLong(id));
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
    
    public Product getByProductCode(String productCode) throws SQLException, ClassNotFoundException {
        String sql = """
        SELECT *
        FROM product
        WHERE product_code = ?
    """;
        Product product = Product.builder().productId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, productCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
    
    public Long addNewAndGetKey(Product t) throws SQLException, ClassNotFoundException {
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
            product_desciption,
            created_date,
            is_deleted
        )
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;
        Long check = -1L;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setObject(1, t.getProductCode());
            ps.setObject(2, t.getProductCategoryId());
            ps.setObject(3, t.getProductCountryCode());
            ps.setObject(4, t.getSupplierId());
            ps.setObject(5, t.getProductTarget());
            ps.setObject(6, t.getProductName());
            ps.setObject(7, t.getDosageForm());
            ps.setObject(8, t.getProductSpecification());
            ps.setObject(9, t.getProductDescription());
            ps.setObject(10, t.getCreatedDate().getTime()+"");
            ps.setObject(11, t.isDeleted());
            int affectedRows = ps.executeUpdate();
            if(affectedRows>0){
                try(ResultSet rs = ps.getGeneratedKeys()) {
                    if(rs.next()){
                        check = rs.getLong(1);
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }
    
     public List<Product> filterProduct(String status, String cateId) throws SQLException, ClassNotFoundException {
        String sql = " SELECT *  FROM product   where is_deleted like '%" + status + "%' ";
        if (cateId != null && !"".equals(cateId)) {
            sql += " and product_category_id = " + cateId;
        }
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

    public boolean updateStatusById(String id, String status) throws SQLException, ClassNotFoundException {
        String sql = " Update [product] set [is_deleted] = ? where [product_id] = " + id;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, CalculatorService.parseLong(status));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }


}
