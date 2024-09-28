/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.NewsCategory;
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
public class NewsCategoryDAO implements RowMapper<NewsCategory> {

    @Override
    public NewsCategory mapRow(ResultSet rs) throws SQLException {
        return NewsCategory.builder()
                .newsCategoryId(rs.getLong("news_category_id"))
                .newsCategoryParentId(rs.getLong("news_category_parent_id"))
                .newsCategoryLevel(rs.getLong("news_category_level"))
                .newsCategoryName(rs.getString("news_category_name"))
                .newsCategoryDetail(rs.getString("news_category_detail"))
                .isDelete(rs.getBoolean("is_delete"))
                .build();
    }

    @Override
    public boolean addNew(NewsCategory t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO news_category (
                     news_category_parent_id, 
                     news_category_level, 
                     news_category_name, 
                     news_category_detail,
                     is_delete
                     )
                     VALUES (?,?,?,?,?);
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getNewsCategoryParentId());
            ps.setObject(2, t.getNewsCategoryLevel());
            ps.setObject(3, t.getNewsCategoryName());
            ps.setObject(4, t.getNewsCategoryDetail());
            ps.setObject(5, t.isDelete());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<NewsCategory> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from news_category
                     """;
        List<NewsCategory> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            list = new ArrayList<>();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public NewsCategory getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from news_category where news_category_id = ?
                     """;
        NewsCategory newsCategory = NewsCategory.builder().newsCategoryId(0L).build();

        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                newsCategory = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newsCategory;
    }

    @Override
    public boolean updateById(String id, NewsCategory t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE news_category
                     SET 
                         news_category_parent_id = ?,
                         news_category_level = ?,
                         news_category_name = ?,
                         news_category_detail = ?,
                         is_delete = ?
                     WHERE news_category_id = ?;
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getNewsCategoryParentId());
            ps.setObject(2, t.getNewsCategoryLevel());
            ps.setObject(3, t.getNewsCategoryName());
            ps.setObject(4, t.getNewsCategoryDetail());
            ps.setObject(5, t.isDelete());
            ps.setObject(6, t.getNewsCategoryId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     DELETE FROM news_category
                     WHERE news_category_id = ?;
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
    
    public List<NewsCategory> getAllParent() throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from news_category where news_category_parent_id is NULL
                     """;
        List<NewsCategory> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            list = new ArrayList<>();
            e.printStackTrace();
        }
        return list;
    }
    
    public List<NewsCategory> getAllChild() throws SQLException, ClassNotFoundException {
        String sql = """
                     select * from news_category where news_category_parent_id is not NULL
                     """;
        List<NewsCategory> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            list = new ArrayList<>();
            e.printStackTrace();
        }
        return list;
    }

}
