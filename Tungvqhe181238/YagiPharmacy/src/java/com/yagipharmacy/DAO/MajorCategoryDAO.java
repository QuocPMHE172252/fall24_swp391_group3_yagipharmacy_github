/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.entities.MajorCategory;
import com.yagipharmacy.DAO.UserDAO;
import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tunkyo
 */
public class MajorCategoryDAO implements RowMapper<MajorCategory> {

    @Override
    public MajorCategory mapRow(ResultSet rs) throws SQLException {
        Long majorCategoryId = rs.getLong("major_category_id");
        String majorCategoryName = rs.getString("major_category_name");
        String majorCategoryDescription = rs.getString("major_category_description");
        boolean isDeleted = rs.getBoolean("is_deleted");

        return MajorCategory.builder()
                .MajorCategoryId(majorCategoryId)
                .MajorCategoryName(majorCategoryName)
                .MajorCategoryDetail(majorCategoryDescription)
                .isDeleted(isDeleted)
                .build();
    }

    @Override
    public boolean addNew(MajorCategory t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO major_category(major_category_name,major_category_description,is_deleted)
                     VALUES (?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getMajorCategoryName());
            ps.setObject(2, t.getMajorCategoryDetail());
            ps.setObject(3, t.getIsDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;

    }

    @Override
    public List<MajorCategory> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
                SELECT *
                FROM [major_category]
                """;
        List<MajorCategory> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getLong("major_category_id"));
                System.out.println(rs.getString("Major_category_name"));
                System.out.println(rs.getString("major_category_description"));
                System.out.println(rs.getBoolean("is_deleted"));
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public MajorCategory getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                SELECT *
                FROM [major_category] WHERE major_category_id = ?
                """;
        MajorCategory mahorCategory = MajorCategory.builder()
                .MajorCategoryId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mahorCategory = mapRow(rs);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mahorCategory;
    }

    @Override
    public boolean updateById(String id, MajorCategory t) throws SQLException, ClassNotFoundException {
        String sql = """
                UPDATE [major_category]
                SET
                   
                  
                 
                    major_category_name = ?,
                    major_category_description = ?,
                    is_deleted = ?
                WHERE
                    major_category_id = ?;
                """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, t.getMajorCategoryName());
            ps.setObject(2, t.getMajorCategoryDetail());
            ps.setObject(3, t.getIsDeleted());
            ps.setObject(4, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateStatusById(String id, String status) throws SQLException, ClassNotFoundException {
        String sql = "  update [major_category] set [is_deleted] = ? where [major_category_id] =  ?";
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

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
