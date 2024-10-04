package edu.sm.service;

import edu.sm.dao.SalesDao;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class SalesService {
    SalesDao salesDao;
    ConnectionPool cp;

    public SalesService() {
        salesDao = new SalesDao();
        try {
            cp = ConnectionPool.create(); // ConnectionPool 생성
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> getMonthlySales() throws Exception {
        Connection con = cp.getConnection();
        List<Integer> sales = null;
        try {
            sales = salesDao.getMonthlySales(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return sales;
    }

    public List<Integer> getYearlySales() throws Exception {
        Connection con = cp.getConnection();
        List<Integer> sales = null;
        try {
            sales = salesDao.getYearlySales(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return sales;
    }

    public List<Integer> getDailySales() throws Exception {
        Connection con = cp.getConnection();
        List<Integer> sales = null;
        try {
            sales = salesDao.getDailySales(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return sales;
    }

    public List<Object[]> getSalesByGender() throws Exception {
        Connection con = cp.getConnection();
        List<Object[]> sales = null;
        try {
            sales = salesDao.getSalesByGender(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return sales;
    }

    public List<Object[]> getSalesByLocation() throws Exception {
        Connection con = cp.getConnection();
        List<Object[]> sales = null;
        try {
            sales = salesDao.getSalesByLocation(con);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
        return sales;
    }
}
