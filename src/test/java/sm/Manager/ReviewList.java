package sm.Manager;

import edu.sm.dto.Review;
import edu.sm.service.ReviewService;

import java.util.List;

public class ReviewList {
    public static void main(String[] args) {
        ReviewService service = new ReviewService();

        try {
            // 전체 리뷰 리스트 조회
            List<Review> reviews = service.getList();

            if (reviews != null && !reviews.isEmpty()) {
                for (Review review : reviews) {
                    System.out.println("리뷰 키: " + review.getReviewKey());
                    System.out.println("리뷰 제목: " + review.getTitle());
                    System.out.println("리뷰 평점: " + review.getRate());
                    System.out.println("작성일: " + review.getRdate());
                    System.out.println("-----------------------------");
                }
            } else {
                System.out.println("리뷰가 존재하지 않습니다.");
            }
        } catch (Exception e) {
            System.out.println("리뷰 리스트 조회 중 오류 발생: " + e.getMessage());
        }
    }
}
