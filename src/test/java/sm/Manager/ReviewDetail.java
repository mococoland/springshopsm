package sm.Manager;

import edu.sm.dto.Review;
import edu.sm.service.ReviewService;

import java.util.Scanner;

public class ReviewDetail {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ReviewService service = new ReviewService();

        try {
            System.out.print("리뷰 키를 입력하세요: ");
            String reviewKey = scanner.nextLine();

            // 리뷰 상세 정보 조회
            Review review = service.getDetail(reviewKey);

            if (review != null) {
                System.out.println("리뷰 제목: " + review.getTitle());
                System.out.println("리뷰 내용: " + review.getContent());
                System.out.println("리뷰 평점: " + review.getRate());
                System.out.println("작성일: " + review.getRdate());
            } else {
                System.out.println("해당 키의 리뷰를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            System.out.println("리뷰 조회 중 오류 발생: " + e.getMessage());
        }
    }
}
