package edu.sm.dao;

import edu.sm.dto.OrderList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import edu.sm.frame.Sql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    public List<OrderList> selectOrdersByCustKey(int custKey, Connection con) throws SQLException {
        List<OrderList> orders = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(Sql.selectOrderList);
            ps.setInt(1, custKey);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderList order = OrderList.builder()
                        .orderId(rs.getInt("orderId"))
                        .name(rs.getString("name"))  // 고객 이름
                        .payId(rs.getInt("payId"))  // 결제 ID
                        .itemCnt(rs.getInt("itemCnt"))  // 아이템 수량
                        .totalPrice(rs.getInt("totalPrice"))  // 총 가격
                        .rdate(rs.getTimestamp("rdate"))  // 주문 날짜
                        .build();
                orders.add(order);
            }
        } finally {
            if (rs != null) rs.close();
            rs.close();
            if (ps != null) ps.close();
            rs.close();
        }
        return orders;
    }
}
