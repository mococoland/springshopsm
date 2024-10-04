//리뷰 조회
package sm.Cust;

import edu.sm.dto.Review;
import edu.sm.service.ReviewService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ReviewSelect {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");


        ReviewService reviewService = new ReviewService();
        List<Review> reviews = null;

        try {
            // 전체 리뷰 가져오기
            reviews = reviewService.get(); // get() 메서드 호출
            System.out.println(reviews); // 리뷰 출력
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
        }
    }
}
