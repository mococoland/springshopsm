package edu.sm.service;

import edu.sm.dao.PayDao;
import edu.sm.dto.Address;
import edu.sm.dto.Pay;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PayService implements MService <Integer, Pay>{

    PayDao dao;
    ConnectionPool cp;

    public PayService() throws SQLException {
        this.cp = ConnectionPool.create();  // 인스턴스를 직접 생성
        this.dao = new PayDao();            // PayDao도 초기화
    }

    @Override
    public Pay add(Pay pay) throws Exception {
        return null;
    }

    @Override
    public Pay modify(Pay pay) throws Exception {
        Connection con = null;

        try {
            con = cp.getConnection();
            con.setAutoCommit(false); // 수동 커밋으로 변경
            dao.update(pay, con);
            con.commit(); // 커밋 호출
            return pay;
        } catch (Exception e) {
            if (con != null) {
                con.rollback(); // 롤백 처리
            }
            throw e;
        } finally {
            if (con != null) {
                cp.releaseConnection(con);
            }
        }
    }

    public Pay update(Pay pay) throws Exception {
        Connection con = null;

        try{
            con = cp.getConnection();
            dao.update(pay, con);
            return pay;
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                cp.releaseConnection(con);
            }
        }
    }



    @Override
    public Boolean remove(Integer integer) throws Exception {
        return null;
    }

    @Override
    public Pay get(Integer integer) throws Exception {
        return null;
    }

    @Override
    public List<Pay> get() throws Exception {
        return List.of();
    }

    public List<Address> getselectPay(Integer custKey) throws Exception {
        Connection con = cp.getConnection();
        List<Address> add = null;
        try{
            add = dao.selectPay(custKey, con);
        } finally {
            cp.releaseConnection(con);
        }
        return add;
    }

    public double getTotalPayAmount(Integer custKey) throws Exception {
        Connection con = cp.getConnection();
        try {
            return dao.PaySum(custKey, con); // 고객의 총 결제 금액 계산
        } finally {
            cp.releaseConnection(con);
        }
    }
}
