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
                .isRejected(rs.getInt("is_rejected") == 0 ? 1 : rs.getInt("is_rejected"))
                .rejectedReason(rs.getString("rejected_reason"))
                .isDeleted(rs.getBoolean("is_deleted"))
                .viewCount(rs.getInt("view_count"))
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
                         view_count,
                         is_deleted
                     )
                     VALUES (?,?,?,?,?,?,?,?,?,?,0,?)
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
            ps.setObject(9, 1);
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

    public List<News> getTop5new() throws SQLException, ClassNotFoundException {
        String sql = """
                      SELECT top (5) *
                      FROM [news] where is_deleted=0 and is_rejected=2 order by [created_date] desc
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

    public List<News> getTop6hot() throws SQLException, ClassNotFoundException {
        String sql = """
                      SELECT top (6) *
                      FROM [news] where is_deleted=0 and is_rejected=2 order by [view_count] desc
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

    public List<News> getList(String search, String category, int currentpage, int pagesize) throws SQLException, ClassNotFoundException {
        String sql = "  select * from [news] where [news_title] like N'%" + search + "%' ";
        if (!category.isEmpty()) {
            sql += "and [news_category_id] = " + category;
        }
        sql += "order by [news_id] OFFSET " + pagesize * (currentpage - 1) + " ROWS FETCH NEXT " + pagesize + " ROWS ONLY";
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

    public int countList(String search, String category) throws SQLException, ClassNotFoundException {
        String sql = "  select count(*) from [news] where [news_title] like N'%" + search + "%' ";
        if (!category.isEmpty()) {
            sql += "and [news_category_id] = " + category;
        }
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
                         is_rejected=?,
                         rejected_reason=?,
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
            ps.setObject(9, t.getIsRejected());
            ps.setObject(10, t.getRejectedReason());
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

    public boolean updateRejectById(String id, News t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [news]
                     SET
                         is_rejected=?,
                         rejected_reason =?
                     WHERE
                         news_id = ?;
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getIsRejected());
            ps.setObject(2, t.getRejectedReason());
            ps.setObject(3, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<News> getAllByCateId(String cateId) throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT *
                     FROM [news] where news_category_id = ?
                     """;
        List<News> list = new ArrayList<>();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(cateId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateByIdConvertProcess(String id, News t) throws SQLException, ClassNotFoundException {
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
                         is_rejected=?,
                         rejected_reason=?,
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
            ps.setObject(9, null);
            ps.setObject(10, t.getRejectedReason());
            ps.setObject(11, t.isDeleted());
            ps.setObject(12, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateByIdNotSetUPI(String id, News t) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [news]
                     SET
                         news_category_id = ?,
                         creator_id = ?,
                         news_title = ?,
                         news_content = ?,
                         news_image = ?,
                         news_hashtag = ?,
                         created_date = ?,
                         is_rejected=?,
                         rejected_reason=?,
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
            ps.setObject(7, t.getCreatedDate().getTime() + "");
            ps.setObject(8, t.getIsRejected());
            ps.setObject(9, t.getRejectedReason());
            ps.setObject(10, t.isDeleted());
            ps.setObject(11, CalculatorService.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean updateViewCount(String newsId, Long view_count) throws SQLException, ClassNotFoundException {
        String sql = """
                     UPDATE [news]
                     SET
                         view_count=?
                     WHERE
                         news_id = ?;
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, view_count);
            ps.setObject(2, CalculatorService.parseLong(newsId));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }
}
