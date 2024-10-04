package sm.Cust;

import edu.sm.dto.Review;
import edu.sm.service.ItemService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class SelectItemReview {
    public static void main(String[] args) {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");

        Scanner scanner = new Scanner(System.in);
        ItemService itemService = new ItemService();


        System.out.print("\n조회할 리뷰의 ItemKey를 입력하세요.: ");
        int itemKeyReviews = scanner.nextInt();
        try {
            List<Review> reviews = itemService.getItemReviews(itemKeyReviews);
            System.out.println("아이템 리뷰 목록:");
            for (Review review : reviews) {
                System.out.println("제목: " + review.getTitle() + ", 리뷰 내용: " + review.getContent() + ", 평점: " + review.getRate());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
