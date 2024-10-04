package edu.sm.service;

import edu.sm.dao.CartDao;
import edu.sm.dao.ItemDao;
import edu.sm.dto.Cart;
import edu.sm.dto.Item;
import edu.sm.service.ItemService;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CartService implements MService <Integer, Cart> {

    private CartDao dao;
    ItemDao itemDao;
    private ConnectionPool cp;

    public CartService() {
        dao = new CartDao();
        itemDao = new ItemDao();
        try{
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 장바구니에 아이템을 추가하거나 업데이트하는 메서드
    @Override
    public Cart add(Cart cart) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);

            // 상품 ID를 통해 상품 정보를 가져옴
            Item item = itemDao.select(String.valueOf(cart.getItemKey()), con);

            // item이 null인지 체크
            if (item == null) {
                throw new Exception("Item not found for key: " + cart.getItemKey());
            }

            // 카트에 상품 정보 설정
            cart.setItemKey(Integer.parseInt(item.getItem_Name()));
            cart.setPrice(item.getItem_price());

            dao.insert(cart, con);
            con.commit();
            System.out.println("Product added to cart: " + item.getItem_Name());
        } catch (SQLException e) {
            if (con != null) {
                con.rollback(); // SQLException이 발생한 경우에만 롤백
            }
            throw e;
        } catch (Exception e) {
            if (con != null) {
                con.rollback(); // 다른 예외 발생 시에도 롤백
            }
            throw e;
        } finally {
            cp.releaseConnection(con);
            System.out.println("Attempting to find item with key: " + cart.getItemKey());
            Item item = itemDao.select(String.valueOf(cart.getItemKey()), con);
        }
        return cart;
    }

    @Override
    public Cart modify(Cart cart) throws Exception {
        return null;
    }

    @Override
    public Boolean remove(Integer CartId) throws Exception {
        Connection con = cp.getConnection();
        try{
            dao.delete(CartId, con);
            return true;
        } catch (Exception e){
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
    }

    @Override
    public Cart get(Integer integer) throws Exception {
        return null;
    }

    @Override
    public List<Cart> get() throws Exception {
        return List.of();
    }
}
