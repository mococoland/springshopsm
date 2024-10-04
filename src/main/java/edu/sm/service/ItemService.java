package edu.sm.service;

import edu.sm.dao.ItemDao;
import edu.sm.dto.Item;
import edu.sm.dto.Review;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements MService<String, Item> {

    ItemDao dao;
    ConnectionPool cp;

    public ItemService() {
        dao = new ItemDao();
        try {
            cp = ConnectionPool.create(); // ConnectionPool을 생성
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Item add(Item item) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            dao.insert(item, con);
            con.commit();
            System.out.println("Send SMS to :" + item.getItem_Name());
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return item;
    }

    @Override
    public Item modify(Item item) throws Exception {
        Connection con = cp.getConnection();
        try {
            dao.update(item, con);
            System.out.println("Send SMS to:" + item.getItem_Name());
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return item;
    }

    @Override
    public Boolean remove(String s) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            dao.delete(s, con);
            System.out.println("Send SMS to:" + s);
            result = true;  // 삭제가 성공적으로 이뤄지면 true 반환
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Item get(String s) throws Exception {
        Connection con = cp.getConnection();
        Item result = null;
        try {
            result = dao.select(s, con);  // ProductDao의 select 메서드 사용
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public List<Item> get() throws Exception {
        Connection con = cp.getConnection();
        List<Item> result = null;
        try {
            result = dao.select(con);  // 모든 제품 목록을 가져옴
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    // 카테고리에 따른 아이템 목록을 불러오는 메서드
    public List<Item> getItemsByCategory(Integer categoryId) throws Exception {
        Connection con = cp.getConnection();
        List<Item> items = null;
        try {
            items = dao.selectByCategory(categoryId, con);
        } finally {
            cp.releaseConnection(con);
        }
        return items;
    }

    // 특정 아이템 상세 정보를 불러오는 메서드
    public Item getItemDetail(int itemKey) throws Exception {
        Connection con = cp.getConnection();
        Item item = null;
        try {
            item = dao.selectDetail(itemKey, con);
        } finally {
            cp.releaseConnection(con);
        }
        return item;
    }

    // 특정 아이템 목록 불러오기
    public List<Item> selectItemListByName(String item_Name) throws SQLException {
        List<Item> items = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = cp.getConnection();

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
            cp.releaseConnection(con);
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return items;
    }

    // 특정 아이템에 대한 리뷰 목록을 불러오는 메서드
    public List<Review> getItemReviews(int itemKey) throws Exception {
        Connection con = cp.getConnection();
        List<Review> reviews = null;
        try {
            reviews = dao.selectReviews(itemKey, con);
        } finally {
            cp.releaseConnection(con);
        }
        return reviews;
    }

}