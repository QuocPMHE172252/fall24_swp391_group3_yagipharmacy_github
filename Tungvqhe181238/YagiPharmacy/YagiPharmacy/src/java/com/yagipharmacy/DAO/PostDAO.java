/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.yagipharmacy.DAO;

import com.yagipharmacy.JDBC.RowMapper;
import com.yagipharmacy.JDBC.SQLServerConnection;
import com.yagipharmacy.constant.services.CalculatorService;
import com.yagipharmacy.entities.Post;
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
public class PostDAO implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet rs) throws SQLException {
        Long dateTime = CalculatorService.parseLong(rs.getString("created_date"));
        return Post.builder()
                .postId(rs.getLong("post_id"))
                .creatorId(rs.getLong("creator_id"))
                .postTitle(rs.getString("post_title"))
                .postContent(rs.getString("post_content"))
                .postImage(rs.getString("post_image"))
                .postHashtag(rs.getString("post_hastag"))
                .createdDate(new Date(dateTime))
                .isDeleted(rs.getBoolean("is_deleted"))
                .build();
    }

    @Override
    public boolean addNew(Post t) throws SQLException, ClassNotFoundException {
        String sql = """
                     INSERT INTO [post] (
                         creator_id,
                         post_title,
                         post_content,
                         post_image,
                         post_hastag,
                         created_date,
                         is_deleted
                     )
                     VALUES (?,?,?,?,?,?,?)
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, t.getCreatorId());
            ps.setObject(2, t.getPostTitle());
            ps.setObject(3, t.getPostContent());
            ps.setObject(4, t.getPostImage());
            ps.setObject(5, t.getPostHashtag());
            ps.setObject(6, t.getCreatedDate().getTime() + "");
            ps.setObject(7, t.isDeleted());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public List<Post> getAll() throws SQLException, ClassNotFoundException {
        String sql = """
                     SELECT *
                     FROM [post]
                     """;
        List<Post> list = new ArrayList<>();
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

    @Override
    public Post getById(String id) throws SQLException, ClassNotFoundException {
       String sql = """
                     SELECT *
                     FROM [post] WHERE post_id = ?
                     """;
        Post post = Post.builder()
                .postId(0L)
                .build();
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, CalculatorService.parseLong(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                post = mapRow(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    @Override
    public boolean updateById(String id, Post t) throws SQLException, ClassNotFoundException {
       String sql = """
                     UPDATE [post]
                     SET
                         creator_id = ?,
                         post_title = ?,
                         post_content = ?,
                         post_image = ?,
                         post_hastag = ?,
                         created_date = ?,
                         is_deleted = ?
                     WHERE
                         post_id = ?;
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1,t.getCreatorId());
            ps.setObject(2, t.getPostTitle());
            ps.setObject(3, t.getPostContent());
            ps.setObject(4, t.getPostImage());
            ps.setObject(5, t.getPostHashtag());
            ps.setObject(6, t.getCreatedDate().getTime()+"");
            ps.setObject(7, t.isDeleted());
            ps.setObject(8, Long.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    @Override
    public boolean deleteById(String id) throws SQLException, ClassNotFoundException {
        String sql = """
                     DELETE FROM [post]
                     WHERE
                         [post_id] = 1;
                     """;
        int check = 0;
        try (Connection con = SQLServerConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setObject(1, Long.parseLong(id));
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

}
