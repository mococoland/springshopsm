package edu.sm.dao;


import edu.sm.dto.Review;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao implements Dao<String, Review> {

    @Override
    public Review insert(Review review, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertReview);
            ps.setInt(1, review.getReviewKey());
            ps.setInt(2, review.getCustKey());
            ps.setInt(3, review.getItemKey());
            ps.setInt(4, review.getManagerKey());
            ps.setString(5, review.getTitle());
            ps.setTimestamp(6, review.getRdate());
            ps.setString(7, review.getContent());
            ps.setString(8, review.getImg());
            ps.setInt(9, review.getRate());
            ps.setString(10, review.getAnswer());
            ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return review;
    }

    @Override
    public Review update(Review review, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateReview);
            ps.setString(1, review.getTitle());
            ps.setString(2, review.getContent());
            ps.setInt(3, review.getRate());
            ps.setInt(4, review.getReviewKey());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return review;

    }

    @Override
    public Boolean delete(String s, Connection con) throws Exception {
        Boolean b = false;
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(Sql.deleteReview);
            ps.setString(1, s);
            int result = ps.executeUpdate();
            if (result == 1) {
                b = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return b;
    }
    @Override
    public Review select(String reviewKey, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Review review = null;

        try {
            ps = con.prepareStatement(Sql.reviewDetail); // Sql.reviewDetail 쿼리 사용
            ps.setString(1, reviewKey); // reviewKey 바인딩
            rs = ps.executeQuery();

            if (rs.next()) {
                review = new Review();
                review.setTitle(rs.getString("title"));
                review.setContent(rs.getString("content"));
                review.setRate(rs.getInt("rate"));
                review.setRdate(rs.getTimestamp("rdate"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return review;
    }

    public List<Review> select(Connection con) throws Exception {
        List<Review> reviews = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(Sql.reviewList); // Sql.reviewList 쿼리 사용
            rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setReviewKey(rs.getInt("reviewKey"));
                review.setTitle(rs.getString("title"));
                review.setRate(rs.getInt("rate"));
                review.setRdate(rs.getTimestamp("rdate"));
                reviews.add(review);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return reviews;
    }

}