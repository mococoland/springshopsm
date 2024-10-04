package sm.Cust;

import edu.sm.dto.Prod_Board;
import edu.sm.service.Prod_BoardService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class updateBoard {
    public static void main(String[] args) {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");

        Prod_BoardService prodBoardService = new Prod_BoardService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("업데이트할 게시물의 boardKey를 입력하세요: ");
        int boardKey = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("새로운 제목을 입력하세요: ");
        String newTitle = scanner.nextLine();

        System.out.print("새로운 내용을 입력하세요: ");
        String newContent = scanner.nextLine();

        System.out.print("새로운 이미지 URL을 입력하세요: ");
        String newImg = scanner.nextLine();

        Prod_Board updatedProdBoard = new Prod_Board();
        updatedProdBoard.setBoardKey(boardKey);
        updatedProdBoard.setTitle(newTitle);
        updatedProdBoard.setContent(newContent);
        updatedProdBoard.setImg(newImg);

        try {
            prodBoardService.modify(updatedProdBoard); // 업데이트 메서드 호출
            System.out.println("게시물이 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            System.out.println("게시물 업데이트에 실패했습니다.");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
