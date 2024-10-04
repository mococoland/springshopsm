package sm.Cust;

import edu.sm.service.PayService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class SelectPaySum {
    public static void main(String[] args) throws SQLException {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");


        // PayService 인스턴스 생성
        PayService payService = new PayService();

        int custKey = 3; // 조회할 custKey (예: 3)

        try {
            // custKey에 해당하는 총 결제 금액 조회
            double totalAmount = payService.getTotalPayAmount(custKey);

            // 결과 출력
            System.out.println("고객 ID " + custKey + "의 총 결제 금액: " + totalAmount);
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
        }
    }
}
