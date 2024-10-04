package edu.sm.dao;

import edu.sm.dto.Address;
import edu.sm.exception.DuplicatedIdException;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDao implements Dao<String, Address> {
    @Override
    public Address insert(Address address, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.insertAdd);
            ps.setInt(1, address.getAddressKey());
            ps.setInt(2, address.getCustKey());
            ps.setString(3, address.getZipnum());
            ps.setString(4, address.getAddrD());
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
        return address;
    }



    @Override
    public Address update(Address address, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            // SQL 쿼리가 addressKey에 따라 업데이트하는 형식인지 확인
            ps = con.prepareStatement(Sql.updateAdd);
            ps.setString(1, address.getZipnum());  // zipnum
            ps.setString(2, address.getAddrD());    // addrD
            ps.setInt(3, address.getAddressKey());  // addressKey

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Address updated successfully.");
            } else {
                System.out.println("No address was updated.");
            }
        } catch (SQLException e) {
            System.err.println("Error while updating address: " + e.getMessage());
            throw e;  // 예외를 다시 던져 호출한 곳에서 처리
        } finally {
            if (ps != null) {
                ps.close();  // PreparedStatement 닫기
            }
        }
        return address;
    }

    @Override
    public Boolean delete(String s, Connection con) throws Exception {
        Boolean b = false;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(Sql.deleteAdd);
            ps.setString(1,s);
            int result = ps.executeUpdate();
            if (result == 0) {
                b = true;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return b;
    }

    @Override
    public Address select(String s, Connection con) throws Exception {
        return null;
    }

    @Override
    public List<Address> select(Connection con) throws Exception {
        return List.of();
    }

//    public List<Address> selectPay(Integer custKey, Connection con) throws Exception {
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        List<Address> addresses = new ArrayList<>();
//        try{
//            ps = con.prepareStatement(Sql.selectPay);
//            ps.setInt(1, custKey);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                while (rs.next()) {
//                    Address address = Address.builder()
//                            .addresskey()
//                            .build();
//                }
//            }
//        }
//    }
}
