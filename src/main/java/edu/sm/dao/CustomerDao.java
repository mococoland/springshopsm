package edu.sm.dao;

import edu.sm.dto.Customer;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements Dao<String, Customer> {
    private Connection connection;

    @Override
    public Customer insert(Customer customer, Connection con) throws Exception {
        return null;
    }

    @Override
    public Customer update(Customer customer, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateCustomer);
            ps.setString(1, customer.getTel());
            ps.setString(2, customer.getEmail());
            ps.setString(3, customer.getPwd());
            ps.setInt(4, customer.getCustKey());
            ps.executeUpdate();
        }catch(Exception e) {
            throw e;
        }finally {
            if(ps != null){
                ps.close();
            }
        }
        return customer;
    }

    @Override
    public Boolean delete(String s, Connection con) throws Exception {
        return null;
    }

    @Override
    public Customer select(String s, Connection con) throws Exception {
        return null;
    }

    @Override
    public List<Customer> select(Connection con) throws Exception {
        List<Customer> custs = new ArrayList<Customer>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectCustomer);
            rs = ps.executeQuery(); //결과를 가져오는
            while(rs.next()) {
                Customer cust = new Customer();
                cust.setCustKey(rs.getInt("custKey"));
                cust.setCustName(rs.getString("custName"));
                cust.setGender(rs.getString("gender"));
                cust.setTel(rs.getString("tel"));
                cust.setEmail(rs.getString("email"));
                custs.add(cust);
            }
        }catch(Exception e) {
            throw e;
        }finally {
            if(ps != null){
                ps.close();
            }
            if(rs != null){
                rs.close();
            }
        }
        return custs;
    }

    // 계정 상태 업데이트
    public void updateCustomerState(Connection con,  int custKey ,  String custState) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateCustState);
            ps.setString(1, custState);
            ps.setInt(2, custKey);
            ps.executeUpdate();
        }finally {
            if(ps != null) ps.close();
        }
    }

    // 랭크 업데이트
    public void updateCustomerRank(Connection con, int custKey, String rating) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateRating);
            ps.setString(1, rating);
            ps.setInt(2, custKey);
            ps.executeUpdate();
        }finally {
            if(ps != null) ps.close();
        }
    }

    // 회원 자격 박탈
    public void revokeMembership(Connection con, int custKey,String memberOut) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.updateMemberOut);
            ps.setString(1, memberOut);
            ps.setInt(2, custKey);
            ps.executeUpdate();
        }finally {
            if(ps != null) ps.close();
        }
    }
}
