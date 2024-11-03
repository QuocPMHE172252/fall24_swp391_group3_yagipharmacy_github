/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.MajorCategory;
import com.yagipharmacy.entities.User;
import com.yagipharmacy.entities.Staff;
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
public class StaffDAO implements RowMapper<Staff> {

    @Override
    public Staff mapRow(ResultSet rs) throws SQLException, ClassNotFoundException {
       UserDAO uDAO = new UserDAO();
        Long staffId = rs.getLong("staff_id");
        Long userId = rs.getLong("user_id");
        User findUser = uDAO.getById(rs.getLong("user_id")+"");
        String staffMajor = rs.getString("staff_major");
        String staffEducation = rs.getString("staff_education");
        String staffExperience = rs.getString("staff_experience");
        String staffDescription = rs.getString("staff_description");
        boolean gender = rs.getBoolean("gender");
        boolean isDeleted = rs.getBoolean("is_deleted");
        return Staff.builder()
                .staffId(staffId)
                .userId(userId)
                .user(findUser)
                .staffMajor(staffMajor)
                .staffEducation(staffEducation)
                .staffExperience(staffExperience)
                .staffDescription(staffDescription)
                .gender(gender)
                .isDeleted(isDeleted)
                .build();
    }

    @Override
    public boolean addNew(Staff t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [staff] (
                     user_id, 
                     staff_major, 
                     staff_education, 
                     staff_experience, 
                     staff_description,
                     gender,
                     is_deleted) 
                     VALUES (?, ?, ?, ?, ?, ?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, t.getUserId()
            );
            ps.setObject(2, t.getStaffMajor());
            ps.setObject(3, t.getStaffEducation());
            ps.setObject(4, t.getStaffExperience());
            ps.setObject(5, t.getStaffDescription());
            ps.setObject(6, t.isGender());
            ps.setObject(7, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Staff> getAll() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [staff]";
        List<Staff> staffs = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                staffs.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffs;
    }

    @Override
    public Staff getById(String id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM [staff] WHERE staff_id = ?";
        Staff staff = Staff.builder().staffId(0L).build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                staff = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public boolean updateById(String id, Staff t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [staff] SET 
                     user_id = ?, 
                     staff_major = ?, 
                     staff_education = ?, 
                     staff_experience = ?, 
                     staff_description = ?,
                     gender = ?,
                     is_deleted = ?,
                     WHERE staff_id = ?
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, t.getUserId());
            ps.setObject(2, t.getStaffMajor());
            ps.setObject(3, t.getStaffEducation());
            ps.setObject(4, t.getStaffExperience());
            ps.setObject(5, t.getStaffDescription());
            ps.setObject(6, t.isGender());
            ps.setObject(7, t.isDeleted());
            ps.setObject(8, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
     public List<Staff> filterStaff(String status, String major) throws SQLException, ClassNotFoundException {
        String sql = " SELECT *  FROM staff where is_deleted LIKE '%"+ status + "%' ";
        if (major != null && !"".equals(major)) {
            sql += " and staff_major LIKE  '%"+ major + "%' ";
        }
        List<Staff> staffs = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                staffs.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffs;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM [staff] WHERE staff_id = ?";
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
    
     public boolean updateStatusById(String id, String status) throws SQLException, ClassNotFoundException {
        String sql = " Update [staff] set [is_deleted] = ? where [staff_id] = " + id;
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
