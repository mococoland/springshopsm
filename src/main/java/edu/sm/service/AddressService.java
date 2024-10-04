package edu.sm.service;

import edu.sm.dao.AddressDao;
import edu.sm.dto.Address;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddressService implements MService<String, Address> {
    AddressDao addressDao;
    ConnectionPool cp;

    public AddressService() {
        addressDao = new AddressDao();
        try {
            cp = ConnectionPool.create(); // ConnectionPool을 생성
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Address add(Address address) throws Exception {
        Connection con = cp.getConnection();
        try {
            con.setAutoCommit(false);
            addressDao.insert(address, con);
            con.commit();
            System.out.println("Address information added to : " + address.getAddressKey());
        } catch (Exception e) {
            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return address;
    }

    @Override
    public Address modify(Address address) throws Exception {
        Connection con = null;  // Connection 변수를 null로 초기화
        try {
            con = cp.getConnection();  // Connection을 얻기
            addressDao.update(address, con);  // 주소 업데이트
            System.out.println("Send SMS to: " + address.getAddressKey());
        } catch (SQLException e) {
            System.err.println("Error while modifying address: " + e.getMessage());
            throw e;  // SQL 예외를 다시 던져 호출한 곳에서 처리
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            throw e;  // 일반 예외도 처리
        } finally {
            if (con != null) {
                cp.releaseConnection(con);  // Connection 반환
            }
        }
        return address;
    }

    @Override
    public Boolean remove(String s) throws Exception {
        Connection con = cp.getConnection();
        Boolean result = false;
        try {
            addressDao.delete(s, con);
            System.out.println("Send SMS to:" + s);
            result = true;  // 삭제가 성공적으로 이뤄지면 true 반환
        } catch (Exception e) {
//            con.rollback();
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public Address get(String s) throws Exception {
        Connection con = cp.getConnection();
        Address result = null;
        try {
            result = addressDao.select(s, con);
        }catch(Exception e) {
            throw e;
        }finally {
            cp.releaseConnection(con);
        }
        return result;
    }

    @Override
    public List get() throws Exception {
        return List.of();
    }
}
