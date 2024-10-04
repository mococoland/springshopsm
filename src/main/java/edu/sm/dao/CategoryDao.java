package edu.sm.dao;

import edu.sm.dto.Category;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public Category update(Category category, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateCategory);
            ps.setString(1, category.getCategoryName()); // categoryName
            ps.setInt(2, category.getSuperCategory());    // superCategory
            ps.setInt(3, category.getCategoryId());       // categoryId

            ps.executeUpdate(); // 쿼리 실행

        } catch (SQLException e) {
            throw new Exception("카테고리 수정 중 오류 발생", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return category;
    }

    // 카테고리 삭제
    public Boolean delete(int categoryId, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteCategory);
            ps.setInt(1, categoryId); // categoryId

            int rowsAffected = ps.executeUpdate(); // 쿼리 실행
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new Exception("카테고리 삭제 중 오류 발생", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    // 카테고리 조회
    public List<Category> selectAll(Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Category> categoryList = new ArrayList<>();

        try {
            ps = con.prepareStatement(Sql.selectCategory);
            rs = ps.executeQuery(); // 쿼리 실행

            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("categoryId")); // categoryId
                category.setCategoryName(rs.getString("categoryName")); // categoryName
                category.setSuperCategory(rs.getInt("superCategory")); // superCategory
                categoryList.add(category);
            }
        } catch (SQLException e) {
            throw new Exception("카테고리 조회 중 오류 발생", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return categoryList;
    }
    // 카테고리 추가
    // 카테고리 추가
    public Category insert(Category category, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertCategory);
            ps.setInt(1, category.getCategoryId());
            ps.setInt(2, category.getSuperCategory());    // superCategory
            ps.setString(3, category.getCategoryName()); // categoryName

            ps.executeUpdate(); // 쿼리 실행

        } catch (SQLException e) {
            throw new Exception("카테고리 추가 중 오류 발생", e);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return category;
    }
}
