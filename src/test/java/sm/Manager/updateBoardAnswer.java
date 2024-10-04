package sm.Manager;

import edu.sm.service.Prod_BoardService;

import java.util.Scanner;

public class updateBoardAnswer {
    public static void main(String[] args) {
        Prod_BoardService prodBoardService = new Prod_BoardService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("답변을 업데이트할 문의의 boardKey를 입력하세요: ");
        String boardKey = scanner.nextLine();

        System.out.print("새로운 답변을 입력하세요: ");
        String answer = scanner.nextLine();

        try {
            boolean isUpdated = prodBoardService.updateAnswer(boardKey, answer);

            if (isUpdated) {
                System.out.println("답변이 성공적으로 업데이트되었습니다.");
            } else {
                System.out.println("답변 업데이트에 실패했습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
