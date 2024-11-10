/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class UserDAO implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs) throws SQLException {
        Long dateTime = CalculatorService.parseLong(rs.getString("created_date"));
        Long dateOfBirthTime = CalculatorService.parseLong(rs.getString("date_of_birth"));
        return User.builder()
                .userId(rs.getLong("user_id"))
                .userName(rs.getString("user_name"))
                .userPhone(rs.getString("user_phone"))
                .userEmail(rs.getString("user_email"))
                .userPassword(rs.getString("user_password"))
                .roleLevel(rs.getLong("role_level"))
                .userAvatar(rs.getString("user_avatar"))
                .userProvince(rs.getString("user_province"))
                .userDistrict(rs.getString("user_district"))
                .userCommune(rs.getString("user_commune"))
                .specificAddress(rs.getString("specific_address"))
                .dateOfBirth(new Date(dateOfBirthTime))
                .createdDate(new Date(dateTime)) // Assumes it's stored as a DATE or TIMESTAMP in DB
                .activeCode(rs.getString("active_code"))
                .isActive(rs.getBoolean("is_active"))
                .isDeleted(rs.getBoolean("is_deleted"))
                .build();
    }

    @Override
    public boolean addNew(User t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [user] (
                         [user_name],
                         user_phone,
                         user_email,
                         user_password,
                         role_level,
                         user_avatar,
                         user_province,
                         user_district,
                         user_commune,
                         specific_address,
                         date_of_birth,
                         created_date,
                         active_code,
                         is_active,
                         is_deleted
                     )
                     VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getUserName());
            ps.setObject(2, t.getUserPhone());
            ps.setObject(3, t.getUserEmail());
            ps.setObject(4, t.getUserPassword());
            ps.setObject(5, t.getRoleLevel());
            ps.setObject(6, t.getUserAvatar());
            ps.setObject(7, t.getUserProvince());
            ps.setObject(8, t.getUserDistrict());
            ps.setObject(9, t.getUserCommune());
            ps.setObject(10, t.getSpecificAddress());
            ps.setObject(11, t.getDateOfBirth() != null ? t.getDateOfBirth().getTime() + "" : null);
            ps.setObject(12, t.getCreatedDate().getTime() + "");
            ps.setObject(13, t.getActiveCode());
            ps.setObject(14, t.isActive());
            ps.setObject(15, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<User> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT * FROM [user]
                     """;
        List<User> list = new ArrayList<>();
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
    public User getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT * FROM [user] WHERE user_id = ?
                     """;
        User user = User.builder()
                .userId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateById(String id, User t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [user]
                     SET
                         [user_name] = ?,
                         user_phone = ?,
                         user_email = ?,
                         user_password = ?,
                         role_level = ?,
                         user_avatar = ?,
                         user_province = ?,
                         user_district = ?,
                         user_commune = ?,
                         specific_address = ?,
                         date_of_birth = ?,
                         created_date = ?,
                         active_code = ?,
                         is_active = ?,
                         is_deleted = ?
                     WHERE
                         [user_id] = ?;
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getUserName());
            ps.setObject(2, t.getUserPhone());
            ps.setObject(3, t.getUserEmail());
            ps.setObject(4, t.getUserPassword());
            ps.setObject(5, t.getRoleLevel());
            ps.setObject(6, t.getUserAvatar());
            ps.setObject(7, t.getUserProvince());
            ps.setObject(8, t.getUserDistrict());
            ps.setObject(9, t.getUserCommune());
            ps.setObject(10, t.getSpecificAddress());
            ps.setObject(11, t.getDateOfBirth().getTime() + "");
            ps.setObject(12, t.getCreatedDate().getTime() + "");
            ps.setObject(13, t.getActiveCode());
            ps.setObject(14, t.isActive());
            ps.setObject(15, t.isDeleted());
            ps.setObject(16, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     DELETE FROM [user]
                     WHERE
                         [user_id] = 1;
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

    public User getUserByPhone(String phone) throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT * FROM [user] WHERE user_phone = ?
                     """;
        User user = User.builder()
                .userId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByEmail(String email) throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT * FROM [user] WHERE user_email = ?
                     """;
        User user = User.builder()
                .userId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public boolean updateStatusById(String id, String status) throws SQLException, ClassNotFoundException {
        String sql = " Update [user] set [is_active] = ? where [user_id] = " + id;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

            ps.setObject(1, CalculatorService.parseLong(status));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<User> getUsers(String search, String isActive, int page, int pageSize) throws SQLException, ClassNotFoundException {
        StringBuilder sql = new StringBuilder("""
        SELECT * FROM [user]
        WHERE (user_name LIKE ? OR user_phone LIKE ? OR user_email LIKE ?)
        """);

        if (isActive != null) {
            sql.append(" AND is_active = ?");
        }

        sql.append(" ORDER BY user_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;");

        List<User> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql.toString())) {
            String searchPattern = "%" + search + "%";

            ps.setObject(1, searchPattern);
            ps.setObject(2, searchPattern);
            ps.setObject(3, searchPattern);

            int paramIndex = 4;
            if (isActive != null) {
                ps.setObject(paramIndex++, isActive);
            }

            ps.setObject(paramIndex++, (page - 1) * pageSize);  // Offset
            ps.setObject(paramIndex, pageSize);  // Limit

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public User getByEmail(String emai) throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT * FROM [user] WHERE [user_email] = ? and  is_deleted = 0
                     """;
        User user = User.builder()
                .userId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, emai);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getByUsername(String username) throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT * FROM [user] WHERE [user_name] = ? and  is_deleted = 0
                     """;
        User user = User.builder()
                .userId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                user = mapRow(rs);
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByPhone(String phone) throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT * FROM [user] WHERE [user_phone] = ? and  is_deleted = 0
                     """;
        User user = User.builder()
                .userId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = mapRow(rs);

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAllAvailable() throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT * FROM [user] where is_deleted = 0
                     """;
        List<User> list = new ArrayList<>();
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
