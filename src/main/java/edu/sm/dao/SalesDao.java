package edu.sm.dao;

import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalesDao implements Dao<Void, Integer> {

    public List<Integer> getMonthlySales(Connection con) throws Exception {
        List<Integer> sales = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectMonth);
            rs = ps.executeQuery();

            while (rs.next()) {
                sales.add(rs.getInt("sales")); // 판매량 추가
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return sales;
    }

    public List<Integer> getYearlySales(Connection con) throws Exception {
        List<Integer> sales = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectYear);
            rs = ps.executeQuery();

            while (rs.next()) {
                sales.add(rs.getInt("sales")); // 판매량 추가
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return sales;
    }

    public List<Integer> getDailySales(Connection con) throws Exception {
        List<Integer> sales = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectDay);
            rs = ps.executeQuery();

            while (rs.next()) {
                sales.add(rs.getInt("sales")); // 판매량 추가
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return sales;
    }

    public List<Object[]> getSalesByGender(Connection con) throws Exception {
        List<Object[]> sales = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectGender);
            rs = ps.executeQuery();

            while (rs.next()) {
                sales.add(new Object[] { rs.getInt("sales"), rs.getString("gender") }); // 판매량과 성별 추가
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return sales;
    }

    public List<Object[]> getSalesByLocation(Connection con) throws Exception {
        List<Object[]> sales = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(Sql.selectLoc);
            rs = ps.executeQuery();

            while (rs.next()) {
                sales.add(new Object[] { rs.getInt("sales"), rs.getString("zipnum") }); // 판매량과 지역코드 추가
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return sales;
    }

    @Override
    public Integer insert(Integer integer, Connection con) throws Exception {
        return 0;
    }

    @Override
    public Integer update(Integer integer, Connection con) throws Exception {
        return 0;
    }

    @Override
    public Boolean delete(Void unused, Connection con) throws Exception {
        return null;
    }

    @Override
    public Integer select(Void unused, Connection con) throws Exception {
        return 0;
    }

    @Override
    public List<Integer> select(Connection con) throws Exception {
        return List.of();
    }

}
