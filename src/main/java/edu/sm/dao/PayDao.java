package edu.sm.dao;

import edu.sm.dto.Address;
import edu.sm.dto.Cart;
import edu.sm.dto.Pay;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayDao implements Dao<Integer, Pay> {

    public List<Address> selectPay(Integer custKey, Connection conn) throws Exception{
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Address> address = new ArrayList<>();
        try{
            ps = conn.prepareStatement(Sql.selectPay);
            ps.setInt(1, custKey);
            rs = ps.executeQuery();
            while (rs.next()){
                Address add = Address.builder()
                        .addressKey(rs.getInt("addressKey"))
                        .zipnum(rs.getString("zipnum"))
                        .addrD(rs.getString("addrD"))
                        .build();
                address.add(add);
            }
        }finally {
            if(ps != null) ps.close();
            if(rs != null) rs.close();
        }
        return address;
    }

    public double PaySum(Integer custKey, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        double totalPrice = 0.0; // 총 금액 초기화

        try {
            ps = conn.prepareStatement(Sql.selectSum);
            ps.setInt(1, custKey);
            rs = ps.executeQuery();

            // 결과가 있는 경우
            if (rs.next()) {
                totalPrice = rs.getDouble("totalPrice");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error while calculating total price", e);
        } finally {
            // 자원 정리
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return totalPrice; // 총 금액 반환
    }

    public Pay updatePayNum(Pay pay, Connection con) throws Exception {
        PreparedStatement ps = null;

        try{
            ps = con.prepareStatement(Sql.updatePayNum);
            ps.setInt(1, pay.getPayNum());
            ps.setInt(2, pay.getPayId());

            ps.executeUpdate();

            return pay;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
    }

    @Override
    public Pay update(Pay pay, Connection con) throws Exception {
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(Sql.updatePay);
            ps.setString(1, pay.getPayCom());
            ps.setInt(2, pay.getPayNum());
            ps.setInt(3, pay.getPayId());

            int rowsAffected = ps.executeUpdate(); // 영향을 받은 행 수를 확인
            if (rowsAffected == 0) {
                throw new Exception("No rows updated, check the provided payId: " + pay.getPayId());
            }

            return pay;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
    }






    @Override
    public Pay insert(Pay pay, Connection con) throws Exception {
        return null;
    }


    @Override
    public Boolean delete(Integer integer, Connection con) throws Exception {
        return null;
    }

    @Override
    public Pay select(Integer integer, Connection con) throws Exception {
        return null;
    }

    @Override
    public List<Pay> select(Connection con) throws Exception {
        return List.of();
    }
}
