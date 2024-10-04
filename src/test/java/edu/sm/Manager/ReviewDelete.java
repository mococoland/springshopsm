package edu.sm.Manager;

import edu.sm.service.ReviewService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReviewDelete {
    public static void main(String[] args) {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");
        ReviewService reviewService = new ReviewService();
        String reviewKey = "1";

        try {
            reviewService.remove(reviewKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

