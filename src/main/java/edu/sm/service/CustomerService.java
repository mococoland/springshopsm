package edu.sm.service;

import edu.sm.dao.CustomerDao;
import edu.sm.dto.Customer;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService implements MService <String, Customer> {

    private ConnectionPool cp; // ConnectionPool 객체를 선언
    private CustomerDao dao; // CustomerDao 객체를 선언

    // 생성자에서 ConnectionPool과 CustomerDao 초기화
    public CustomerService() {
        dao = new CustomerDao();
        try {
            cp = ConnectionPool.create(); // ConnectionPool 생성
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer add(Customer customer) throws Exception {
        return null;
    }

    @Override
    public Customer modify(Customer customer) throws Exception {
        Connection con = cp.getConnection();
        try {
            dao.update(customer, con);
            System.out.println("Send SMS to:" + customer.getCustKey());
        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return customer;    }

    @Override
    public Boolean remove(String s) throws Exception {
        return null;
    }

    @Override
    public Customer get(String s) throws Exception {
        return null;
    }

    @Override
    public List<Customer> get() throws Exception {
        Connection con = cp.getConnection();
        List<Customer> result = null;
        try {
            result = dao.select(con);
        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    // 계정 상태 업데이트
    public void updateCustomerState(int custKey, String custState) throws Exception {
        Connection con = cp.getConnection();  // Connection Pool에서 연결 가져오기
        try {
            dao.updateCustomerState(con, custKey, custState);  // DAO 호출하여 상태 업데이트
        } catch (Exception e) {
            throw e;  // 예외 발생 시 호출 메서드로 예외 전파
        } finally {
            cp.releaseConnection(con);  // 연결 해제
        }
    }

    // 랭크 업데이트
    public void updateCustomerRank(int custKey, String rank) throws Exception {
        Connection con = cp.getConnection();  // Connection Pool에서 연결 가져오기
        try {
            dao.updateCustomerRank(con, custKey, rank);  // DAO 호출하여 랭크 업데이트
        } catch (Exception e) {
            throw e;  // 예외 발생 시 호출 메서드로 예외 전파
        } finally {
            cp.releaseConnection(con);  // 연결 해제
        }
    }

    // 회원 자격 박탈
    public void revokeMembership(int custKey, String memberOut) throws Exception {
        Connection con = cp.getConnection();  // Connection Pool에서 연결 가져오기
        try {
            dao.revokeMembership(con, custKey, memberOut);  // DAO 호출하여 회원 자격 박탈
        } catch (Exception e) {
            throw e;  // 예외 발생 시 호출 메서드로 예외 전파
        } finally {
            cp.releaseConnection(con);  // 연결 해제
        }
    }
}
