package sm.Cust;

import edu.sm.dto.Pay;
import edu.sm.service.PayService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdatePayCom {
    public static void main(String[] args) throws SQLException {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");

        PayService payService = new PayService();
        Scanner scanner = new Scanner(System.in);

        System.out.print("업데이트 할 결제사의 payId를 입력하세요: ");
        int payId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("결제할 카드사/은행을 입력하세요: ");
        String payName = scanner.nextLine();

        System.out.println("카드사/통장의 번호를 입력하세요: ");
        int payNum = scanner.nextInt();

        Pay updatePay = new Pay();
        updatePay.setPayId(payId);
        updatePay.setPayCom(payName);
        updatePay.setPayNum(payNum);

        try{
            payService.modify(updatePay);
            System.out.println("결제사/은행이 성공적으로 업데이트 되었습니다.");
        } catch (Exception e){
            System.out.println("업데이트에 실패하였습니다.");
            e.printStackTrace();
        } finally{
            scanner.close();
        }
    }
}
