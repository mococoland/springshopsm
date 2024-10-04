package edu.sm.dao;

import edu.sm.dto.Cart;
import edu.sm.dto.Item;

import edu.sm.dto.Review;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDao implements Dao<String, Item> {

    @Override
    public Item insert(Item item, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertItem);
            ps.setInt(1, item.getCategoryId());     // name
            ps.setString(2, item.getItem_Name());       // price
            ps.setInt(3, item.getItem_price());
            ps.setInt(4, item.getCnt() );
            ps.setString(5, item.getContent());
            ps.setString(6, item.getImg1());
            ps.setString(7, item.getImg2());
            ps.setString(8, item.getImg3());
            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new DuplicatedIdException("EX0002");
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return item;
    }

    public void insertCart(Cart cart, Connection con) throws SQLException {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(Sql.insertCart);
            ps.setInt(1, cart.getCustKey());
            ps.setInt(2, cart.getItemKey());
            ps.setInt(3, cart.getItemCnt());
            ps.setInt(4, cart.getPrice());
            ps.executeUpdate();  // INSERT 실행
        } finally {
            if (ps != null) ps.close();  // PreparedStatement 닫기
        }
    }


    // selectItem: 특정 카테고리의 아이템 목록 불러오기
    public List<Item> selectByCategory(Integer categoryId, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Item> items = new ArrayList<>();
        try {
            ps = con.prepareStatement(Sql.selectItem);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item item = Item.builder()
                        .itemKey(rs.getInt("itemKey"))
                        .item_Name(rs.getString("item_name"))
                        .item_price(rs.getInt("item_price"))
                        .build();
                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return items;
    }

    // selectItemDetail: 특정 아이템 상세 정보 불러오기
    public Item selectDetail(int itemKey, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Item item = null;
        try {
            ps = con.prepareStatement(Sql.selectItemDetail);
            ps.setInt(1, itemKey);
            rs = ps.executeQuery();
            if (rs.next()) {
                item = Item.builder()
                        .item_Name(rs.getString("item_name"))
                        .item_price(rs.getInt("item_price"))
                        .item_date(rs.getTimestamp("item_date"))
                        .cnt(rs.getInt("cnt"))
                        .content(rs.getString("content"))
                        .build();
                // 이미지 데이터 추가 처리 필요 시 추가 가능
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return item;
    }

    // selectItemList: 특정 아이템 목록 불러오기
    public List<Item> selectItemListByName(String item_Name, Connection con) throws SQLException {
        List<Item> items = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(Sql.selectItemList);
            ps.setString(1, "%" + item_Name + "%");  // Use LIKE for partial matches
            rs = ps.executeQuery();

            while (rs.next()) {
                Item item = Item.builder()
                        .itemKey(rs.getInt("itemKey"))
                        .item_Name(rs.getString("item_name"))
                        .item_price(rs.getInt("item_price"))
                        .build();
                items.add(item);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return items;
    }

    // selectItemReview: 특정 아이템에 대한 리뷰 불러오기
    public List<Review> selectReviews(int itemKey, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Review> reviews = new ArrayList<>();
        try {
            ps = con.prepareStatement(Sql.selectItemReview);
            ps.setInt(1, itemKey);
            rs = ps.executeQuery();
            while (rs.next()) {
                Review review = new Review();
                review.setReviewKey(rs.getInt("reviewKey"));
                review.setCustKey(rs.getInt("custKey"));
                review.setTitle(rs.getString("title"));
                review.setContent(rs.getString("content"));
                review.setRate(rs.getInt("rate"));
                reviews.add(review);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return reviews;
    }



    @Override
    public Item update(Item item, Connection con) throws Exception {
        return null;
    }

    @Override
    public Boolean delete(String s, Connection con) throws Exception {
        return null;
    }

    @Override
    public Item select(String s, Connection con) throws Exception {
        return null;
    }

    @Override
    public List<Item> select(Connection con) throws Exception {
        return List.of();
    }
}