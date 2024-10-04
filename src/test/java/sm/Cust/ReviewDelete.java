//리뷰 삭제
package sm.Cust;

import edu.sm.service.ReviewService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReviewDelete {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

        ReviewService reviewService = new ReviewService();
        String reviewKey = "1";

        try {
            System.out.println("리뷰 삭제 완료");
            reviewService.remove(reviewKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

