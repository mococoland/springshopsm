package sm.Manager;

import edu.sm.service.Prod_BoardService;
import java.util.Scanner;

public class deleteBoard {
    public static void main(String[] args) {
        Prod_BoardService prodBoardService = new Prod_BoardService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("삭제할 문의의 boardKey를 입력하세요: ");
        int boardKey = scanner.nextInt();

        try {
            boolean isDeleted = prodBoardService.remove(String.valueOf(boardKey));

            if (isDeleted) {
                System.out.println("문의가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("해당 문의가 존재하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
