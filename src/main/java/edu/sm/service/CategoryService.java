package edu.sm.service;

import edu.sm.dao.CategoryDao;
import edu.sm.dto.Category;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryService implements MService<Integer, Category> {

    private CategoryDao dao;
    private ConnectionPool cp;

    public CategoryService() {
        dao = new CategoryDao();
        try {
            cp = ConnectionPool.create(); // ConnectionPool 생성
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category add(Category category) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.insert(category, con);  // 카테고리 추가
            con.commit();
            System.out.println("Send SMS to :" + category.getCategoryId());

        } catch (Exception e) {
            con.rollback();
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return category;
    }

    @Override
    public Category modify(Category category) throws Exception {
        Connection con = cp.getConnection();
        try {
            dao.update(category, con);  // 카테고리 수정
            return category;
        } catch (Exception e) {
            con.rollback();
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
    }

    @Override
    public Boolean remove(Integer categoryId) throws Exception {
        Connection con = cp.getConnection();
        try {
            dao.delete(categoryId, con);  // 카테고리 삭제
            return true;
        } catch (Exception e) {
            con.rollback();
            throw e;
        }
        finally {
            cp.releaseConnection(con);
        }
    }

    @Override
    public Category get(Integer categoryId) throws Exception {
        Connection con = cp.getConnection();
        try {
            List<Category> categories = dao.selectAll(con); // 모든 카테고리 조회
            for (Category category : categories) {
                if (category.getCategoryId() == categoryId) {
                    return category;  // ID에 해당하는 카테고리 반환
                }
            }
        } finally {
            cp.releaseConnection(con);
        }
        return null;  // 해당하는 카테고리가 없을 경우
    }

    @Override
    public List<Category> get() throws Exception {
        Connection con = cp.getConnection();
        List<Category> categories;
        try {
            categories = dao.selectAll(con); // 모든 카테고리 조회
        } finally {
            cp.releaseConnection(con);
        }
        return categories;
    }
}
