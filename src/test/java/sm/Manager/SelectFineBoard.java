package sm.Manager;

import edu.sm.dto.Prod_Board;
import edu.sm.service.Prod_BoardService;

import java.util.List;
import java.util.Scanner;

public class SelectFineBoard {
    public static void main(String[] args) {
        Prod_BoardService prodBoardService = new Prod_BoardService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("검색할 키워드를 입력하세요: ");
        String keyword = scanner.nextLine();

        try {
            List<Prod_Board> prodBoards = prodBoardService.searchByKeyword(keyword);

            if (prodBoards.isEmpty()) {
                System.out.println("검색 결과가 없습니다.");
            } else {
                for (Prod_Board board : prodBoards) {
                    System.out.println("제목: " + board.getTitle());
                    System.out.println("내용: " + board.getContent());
                    System.out.println("등록일: " + board.getRdate());
                    System.out.println("----------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
