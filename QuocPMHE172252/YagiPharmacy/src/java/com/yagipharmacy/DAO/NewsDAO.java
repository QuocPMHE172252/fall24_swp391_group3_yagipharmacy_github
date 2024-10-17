/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.News;
import com.yagipharmacy.entities.NewsCategory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author admin
 */
public class NewsDAO implements RowMapper<News> {

    @Override
    public News mapRow(ResultSet rs) throws SQLException, ClassNotFoundException {
        Long dateTime = CalculatorService.parseLong(rs.getString("created_date"));
        NewsCategoryDAO newsCategoryDAO = new NewsCategoryDAO();
        List<NewsCategory> newsCategorys = new ArrayList<>();
        NewsCategory findingNewsCate = newsCategoryDAO.getById(rs.getLong("news_category_id") + "");
        return News.builder()
                .newsId(rs.getLong("news_id"))
                .newsCategoryId(rs.getLong("news_category_id"))
                .creatorId(rs.getLong("creator_id"))
                .newsTitle(rs.getString("news_title"))
                .newsContent(rs.getString("news_content"))
                .newsImage(rs.getString("news_image"))
                .newsHashtag(rs.getString("news_hashtag"))
                .updatedId(rs.getLong("updated_id"))
                .createdDate(new Date(dateTime))
                .isRejected(rs.getBoolean("is_rejected"))
                .rejectedReason(rs.getString("rejected_reason"))
                .isDeleted(rs.getBoolean("is_deleted"))
                .newsCategory(findingNewsCate)
                .build();
    }

    @Override
    public boolean addNew(News t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [news] (
                         news_category_id,
                         creator_id,
                         news_title,
                         news_content,
                         news_image,
                         news_hashtag,
                         updated_id,
                         created_date,
                         is_rejected,
                         rejected_reason,
                         is_deleted
                     )
                     VALUES (?,?,?,?,?,?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getNewsCategoryId());
            ps.setObject(2, t.getCreatorId());
            ps.setObject(3, t.getNewsTitle());
            ps.setObject(4, t.getNewsContent());
            ps.setObject(5, t.getNewsImage());
            ps.setObject(6, t.getNewsHashtag());
            ps.setObject(7, t.getUpdatedId());
            ps.setObject(8, t.getCreatedDate().getTime() + "");
            ps.setObject(9, t.isRejected());
            ps.setObject(10, t.getRejectedReason());
            ps.setObject(11, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<News> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT *
                     FROM [news]
                     """;
        List<News> list = new ArrayList<>();
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
    public News getById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT *
                     FROM [news] WHERE news_id = ?
                     """;
        News news = News.builder()
                .newsId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                news = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return news;
    }

    @Override
    public boolean updateById(String id, News t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [news]
                     SET
                         news_category_id = ?,
                         creator_id = ?,
                         news_title = ?,
                         news_content = ?,
                         news_image = ?,
                         news_hashtag = ?,
                         updated_id = ?,
                         created_date = ?,
                         is_rejected,
                         rejected_reason,
                         is_deleted = ?
                     WHERE
                         news_id = ?;
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getNewsCategoryId());
            ps.setObject(2, t.getCreatorId());
            ps.setObject(3, t.getNewsTitle());
            ps.setObject(4, t.getNewsContent());
            ps.setObject(5, t.getNewsImage());
            ps.setObject(6, t.getNewsHashtag());
            ps.setObject(7, t.getUpdatedId());
            ps.setObject(8, t.getCreatedDate().getTime() + "");
            ps.setObject(9, t.isRejected());
            ps.setObject(10, t.getRejectedReason());
            ps.setObject(11, t.isDeleted());
            ps.setObject(10, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     DELETE FROM [news]
                     WHERE
                         [news_id] = 1;
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

    public List<News> getAllNewsNotDeleted() throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT *
                     FROM [news] where [is_deleted] = 0
                     """;
        List<News> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs)); // Call the mapRow method to convert ResultSet to Post object
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
