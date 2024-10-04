package sm.Cust;

import edu.sm.dto.Pay;
import edu.sm.service.PayService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdatePayNum {
    public static void main(String[] args) throws SQLException {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");
        PayService payService = new PayService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("업데이트 할 payId를 입력하세요: ");
        int payId = scanner.nextInt();
        scanner.nextLine(); // 버퍼 클리어

        System.out.println("결제할 카드/계좌의 번호를 입력하세요: ");
        int payNum = scanner.nextInt();
        scanner.nextLine(); // 버퍼 클리어

        System.out.println("결제할 카드사/은행을 입력하세요: ");
        String payName = scanner.nextLine();

        Pay updatePay = new Pay();
        updatePay.setPayId(payId);
        updatePay.setPayCom(payName);
        updatePay.setPayNum(payNum);

        try {
            payService.modify(updatePay);
            System.out.println("카드/계좌 번호가 성공적으로 업데이트 되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
