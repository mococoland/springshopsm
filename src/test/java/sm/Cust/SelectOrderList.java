package sm.Cust;

import edu.sm.dto.OrderList;
import edu.sm.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class SelectOrderList {
    public static void main(String[] args) {
        ApplicationContext factory =
                new ClassPathXmlApplicationContext("spring.xml");

        Scanner scanner = new Scanner(System.in);
        OrderService orderService = new OrderService();  // OrderService 초기화

        System.out.print("\n조회할 고객의 custKey를 입력하세요: ");
        int custKey = scanner.nextInt();  // Get custKey from user input

        try {
            List<OrderList> orders = orderService.getOrdersByCustKey(custKey);

            if (orders.isEmpty()) {
                System.out.println("해당 고객의 주문이 없습니다.");
            } else {
                System.out.println("주문 목록:");
                for (OrderList order : orders) {
                    System.out.println("주문 번호: " + order.getOrderId() +
                            ", 구매자 이름: " + order.getName() +
                            ", 결제 ID: " + order.getPayId() +
                            ", 구매 수량: " + order.getItemCnt() +
                            ", 총 가격: " + order.getTotalPrice() +
                            ", 주문 날짜: " + order.getRdate()
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();  // Scanner 자원 해제
        }
    }
}
