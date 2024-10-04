package edu.sm.dao;

import edu.sm.dto.Prod_Board;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Prod_BoardDao implements Dao<String, Prod_Board> {
    @Override
    public Prod_Board insert(Prod_Board prodBoard, Connection con) throws Exception {
        PreparedStatement ps = null;

        try {
            // SQL 쿼리 실행 준비
            ps = con.prepareStatement(Sql.insertBoard);
            // ?에 값을 설정
            ps.setInt(1, prodBoard.getCustKey());
            ps.setInt(2, prodBoard.getItemKey());
            ps.setString(3, prodBoard.getType());
            ps.setString(4, prodBoard.getTitle());
            ps.setString(5, prodBoard.getContent());
            ps.setString(6, prodBoard.getImg());
            ps.setTimestamp(7, new Timestamp(System.currentTimeMillis())); // rdate 값 설정

            // 쿼리 실행
            ps.executeUpdate();
            return prodBoard; // 추가된 prodBoard 객체 반환
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Override
    public Prod_Board update(Prod_Board prodBoard, Connection con) throws Exception {
        PreparedStatement ps = null;

        try {
            // SQL 쿼리 실행 준비
            ps = con.prepareStatement(Sql.updateBoard);
            // ?에 값을 설정
            ps.setString(1, prodBoard.getTitle());
            ps.setString(2, prodBoard.getContent());
            ps.setString(3, prodBoard.getImg());
            ps.setInt(4, prodBoard.getBoardKey()); // boardKey 설정

            // 쿼리 실행
            ps.executeUpdate();

            return prodBoard; // 업데이트된 prodBoard 반환
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public Boolean updateAnswer(String boardKey, String answer, Connection con) throws Exception {
        PreparedStatement ps = null;
        boolean isUpdated = false;

        try {
            // SQL 쿼리 실행 준비
            ps = con.prepareStatement(Sql.updateBoardAnswer);
            ps.setString(1, answer); // ?에 answer 값을 설정
            ps.setString(2, boardKey); // ?에 boardKey 값을 설정

            // 쿼리 실행
            int rowsAffected = ps.executeUpdate();
            isUpdated = (rowsAffected > 0); // 업데이트가 성공했는지 확인
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }

        return isUpdated; // 업데이트 결과 반환
    }


    @Override
    public Boolean delete(String boardKey, Connection con) throws Exception {
        PreparedStatement ps = null;
        try {
            // SQL 쿼리 실행 준비
            ps = con.prepareStatement(Sql.deleteBoard);
            ps.setString(1, boardKey); // ?에 boardKey 값을 설정

            // 쿼리 실행
            int rowsAffected = ps.executeUpdate(); // 영향받은 행 수 반환
            return rowsAffected > 0; // 삭제 성공 여부 반환
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }


    // custKey에 해당하는 모든 문의를 반환하는 메서드
    public List<Prod_Board> selectByCustKey(String custKey, Connection con) throws Exception {
        List<Prod_Board> prodBoards = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // SQL 쿼리 실행 준비
            ps = con.prepareStatement(Sql.selectBoard);
            ps.setString(1, custKey); // ?에 custKey 값을 설정
            rs = ps.executeQuery();

            // 결과를 List에 추가
            while (rs.next()) {
                Prod_Board prodBoard = new Prod_Board();
                prodBoard.setTitle(rs.getString("title"));
                prodBoard.setContent(rs.getString("content"));
                prodBoard.setRdate(rs.getTimestamp("rdate")); // rdate 값 설정
                prodBoards.add(prodBoard);
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

        return prodBoards; // 조회된 문의 목록 반환
    }

    // 키워드로 문의 검색
    public List<Prod_Board> selectByKeyword(String keyword, Connection con) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Prod_Board> prodBoards = new ArrayList<>();

        try {
            // SQL 쿼리 실행 준비
            ps = con.prepareStatement(Sql.selectFineBoard);
            ps.setString(1, "%" + keyword + "%"); // 제목에 키워드 포함
            ps.setString(2, "%" + keyword + "%"); // 내용에 키워드 포함
            rs = ps.executeQuery();

            // 결과가 존재하면 Prod_Board 객체 생성 및 값 설정
            while (rs.next()) {
                Prod_Board prodBoard = new Prod_Board();
                prodBoard.setTitle(rs.getString("title"));
                prodBoard.setContent(rs.getString("content"));
                prodBoard.setRdate(rs.getTimestamp("rdate")); // rdate 값 설정
                prodBoards.add(prodBoard);
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

        return prodBoards; // 조회된 문의 목록 반환
    }


    @Override
    public Prod_Board select(String boardKey, Connection con) throws Exception {
        return null; // 필요 시 구현
    }

    @Override
    public List<Prod_Board> select(Connection con) throws Exception {
        return List.of();
    }
}
