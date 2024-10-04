package sm.Manager;

import edu.sm.dto.Prod_Board;
import edu.sm.service.Prod_BoardService;

import java.util.List;

public class selectBoard {
    public static void main(String[] args) {
        // Prod_BoardDaoService 인스턴스 생성
        Prod_BoardService prodBoardService = new Prod_BoardService();

        String custKey = "1"; // 조회할 custKey

        try {
            // custKey에 해당하는 모든 문의를 조회
            List<Prod_Board> prodBoards = prodBoardService.getByCustKey(custKey);

            // 조회된 문의를 한 줄씩 출력
            for (Prod_Board prodBoard : prodBoards) {

                System.out.println("Title: " + prodBoard.getTitle());
                System.out.println("Content: " + prodBoard.getContent());
                System.out.println("Date: " + prodBoard.getRdate());
                System.out.println("-----------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
        }
    }
}
