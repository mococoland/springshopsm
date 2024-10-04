package edu.sm.service;

import edu.sm.dao.ReviewDao;
import edu.sm.dto.Review;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

public class ReviewService implements MService<String, Review> {
    ReviewDao dao;
    ConnectionPool cp;

    public ReviewService() {
        dao = new ReviewDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Review add(Review review) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false); //커밋 될떄까지 기다려
            dao.insert(review, con);
            System.out.println("insert ok:"+review.getReviewKey());
            con.commit(); //커밋
        }
        catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return review;
    }

    @Override
    public Review modify(Review review) throws Exception {
        Connection con = cp.getConnection();
        try {

            dao.update(review, con);
            System.out.println("modified:" + review.getReviewKey());

        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return review;
    }

    @Override
    public Boolean remove(String s) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {

            result = dao.delete(s, con);
            System.out.println("removed:" + s);

        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Review get(String s) throws Exception {
        Connection con = cp.getConnection();
        Review result = null;

        try {
            result = dao.select(s, con);

        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public List<Review> get() throws Exception {
        Connection con = cp.getConnection();
        List<Review> result = null;

        try {
            result = dao.select(con);

        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;

    }
    // 1. 리뷰 상세 조회 기능
    public Review getDetail(String reviewKey) throws Exception {
        Connection con = cp.getConnection();
        Review result = null;

        try {
            result = dao.select(reviewKey, con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }
    // 2. 리뷰 리스트 조회 기능
    public List<Review> getList() throws Exception {
        Connection con = cp.getConnection();
        List<Review> result = null;

        try {
            result = dao.select(con); // 리뷰 리스트 조회
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }


}

