package edu.sm.dao;

import edu.sm.dto.Cart;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.List;

import static edu.sm.frame.Sql.insertCart;

public class CartDao {
    public Cart update(Cart cart, Connection con) {
        return null;
    }

    public Boolean delete(int cartId, Connection con) throws Exception {
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(Sql.deleteCart);
            ps.setInt(1, cartId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new Exception("오류", e);
        } finally {
            if(ps != null) ps.close();
        }
    }


    // 중복된 아이템이 있는지 확인하는 메서드
    public Cart findByCustKeyAndItemKey(int custKey, int itemKey, Connection con) throws Exception {
        Cart cart = null;
        String sql = "SELECT * FROM cart WHERE custKey = ? AND itemKey = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, custKey);
            ps.setInt(2, itemKey);
            rs = ps.executeQuery();
            if (rs.next()) {
                cart = new Cart(
                        rs.getInt("cartKey"),
                        rs.getInt("custKey"),
                        rs.getInt("itemKey"),
                        rs.getInt("itemCnt"),
                        rs.getInt("price")
                );
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return cart;
    }


    public Cart insert(Cart cart, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertCart);
            ps.setInt(1, cart.getCustKey());  // cust_key
            ps.setInt(2, cart.getItemKey());     // item_key
            ps.setInt(3, cart.getItemCnt());       // cnt
            ps.setInt(4, cart.getPrice());    // Price
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
        return cart;
    }
}