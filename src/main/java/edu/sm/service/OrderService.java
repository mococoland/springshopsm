package edu.sm.service;

import edu.sm.dao.OrderDao;
import edu.sm.dto.OrderList;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderService {

    private OrderDao orderDao;
    private ConnectionPool cp;

    public OrderService() {
        orderDao = new OrderDao();
        try {
            cp = ConnectionPool.create();  // ConnectionPool 초기화
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<OrderList> getOrdersByCustKey(int custKey) throws Exception {
        Connection con = cp.getConnection();
        List<OrderList> orders;
        try {
            orders = orderDao.selectOrdersByCustKey(custKey, con);
        } finally {
            cp.releaseConnection(con);  // Connection 자원 해제
        }
        return orders;
    }
}
