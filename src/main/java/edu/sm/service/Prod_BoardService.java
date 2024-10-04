package edu.sm.service;

import edu.sm.dao.Prod_BoardDao;
import edu.sm.dto.Prod_Board;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Prod_BoardService implements MService<String, Prod_Board> {
    private Prod_BoardDao dao;
    private ConnectionPool cp;

    // 생성자를 통해 ConnectionPool과 Dao 초기화
    public Prod_BoardService() {
        dao = new Prod_BoardDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Prod_Board add(Prod_Board prodBoard) throws Exception {
        Connection con = null;

        try {
            con = cp.getConnection();
            dao.insert(prodBoard, con); // DAO를 사용하여 문의 추가
            return prodBoard; // 추가된 prodBoard 반환
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                cp.releaseConnection(con);
            }
        }
    }

    @Override
    public Prod_Board modify(Prod_Board prodBoard) throws Exception {
        Connection con = null;

        try {
            con = cp.getConnection();
            dao.update(prodBoard, con); // DAO를 사용하여 문의 업데이트
            return prodBoard; // 업데이트된 prodBoard 반환
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                cp.releaseConnection(con);
            }
        }
    }


    public Boolean remove(String boardKey) throws Exception {
        Connection con = null;
        Boolean isDeleted = false;

        try {
            con = cp.getConnection();
            isDeleted = dao.delete(boardKey, con); // DAO를 사용하여 문의 삭제
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                cp.releaseConnection(con);
            }
        }

        return isDeleted; // 삭제 성공 여부 반환
    }


    // custKey에 해당하는 모든 문의를 가져오는 메서드
    public List<Prod_Board> getByCustKey(String custKey) throws Exception {
        Connection con = null;
        List<Prod_Board> prodBoards = null;

        try {
            con = cp.getConnection();
            prodBoards = dao.selectByCustKey(custKey, con); // 여러 문의를 가져오는 메서드 호출
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                cp.releaseConnection(con);
            }
        }

        return prodBoards;
    }

    // 키워드로 문의 검색
    public List<Prod_Board> searchByKeyword(String keyword) throws Exception {
        Connection con = null;
        List<Prod_Board> prodBoards = null;

        try {
            con = cp.getConnection();
            prodBoards = dao.selectByKeyword(keyword, con); // 키워드로 문의 정보 조회
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                cp.releaseConnection(con);
            }
        }

        return prodBoards;
    }

    public Boolean updateAnswer(String boardKey, String answer) throws Exception {
        Connection con = null;
        boolean isUpdated = false;

        try {
            con = cp.getConnection();
            isUpdated = dao.updateAnswer(boardKey, answer, con); // DAO를 사용하여 답변 업데이트
        } catch (Exception e) {
            throw e;
        } finally {
            if (con != null) {
                cp.releaseConnection(con);
            }
        }

        return isUpdated; // 업데이트 결과 반환
    }


    @Override
    public Prod_Board get(String boardKey) throws Exception {
        return null; // 이 메서드에 대한 구현은 필요하지 않은 경우
    }

    @Override
    public List<Prod_Board> get() throws Exception {
        return List.of();
    }
}
