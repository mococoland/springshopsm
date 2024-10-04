//문의 추가
package sm.Cust;
import edu.sm.dto.Prod_Board;
import edu.sm.service.Prod_BoardService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class insertBoard {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");


        Prod_BoardService prodBoardService = new Prod_BoardService();
        Scanner scanner = new Scanner(System.in);

        // 사용자 입력 받기
        System.out.print("고객 키(custKey)를 입력하세요: ");
        int custKey = Integer.parseInt(scanner.nextLine());

        System.out.print("아이템 키(itemKey)를 입력하세요: ");
        int itemKey = Integer.parseInt(scanner.nextLine());

        System.out.print("문의 유형(type)을 입력하세요: ");
        String type = scanner.nextLine();

        System.out.print("제목(title)을 입력하세요: ");
        String title = scanner.nextLine();

        System.out.print("내용(content)을 입력하세요: ");
        String content = scanner.nextLine();

        System.out.print("이미지(img) 경로를 입력하세요: ");
        String img = scanner.nextLine();

        // Prod_Board 객체 생성
        Prod_Board prodBoard = Prod_Board.builder()
                .custKey(custKey)
                .itemKey(itemKey)
                .type(type)
                .title(title)
                .content(content)
                .img(img)
                .build();

        // 문의 추가
        try {
            prodBoardService.add(prodBoard);
            System.out.println("문의가 성공적으로 추가되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("문의 추가 중 오류가 발생했습니다.");
        } finally {
            scanner.close();
        }
    }
}
