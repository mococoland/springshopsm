package sm.Manager;

import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

import java.util.List;

public class CustSelectTest {
    public static void main(String[] args) {
        CustomerService custService = new CustomerService();  // CustService 인스턴스 생성
        List<Customer> customers = null;

        try {
            customers = custService.get();  // CustService에서 get() 메서드를 호출해 Customer 목록을 가져옴
            if (customers != null && !customers.isEmpty()) {
                for (Customer cust : customers) {
                    // 고객 정보 출력
                    System.out.println("고객 ID: " + cust.getCustKey());
                    System.out.println("고객 이름: " + cust.getCustName());
                    System.out.println("성별: " + cust.getGender());
                    System.out.println("전화번호: " + cust.getTel());
                    System.out.println("이메일: " + cust.getEmail());
                    System.out.println("------------------------");
                }
            } else {
                System.out.println("조회된 고객 정보가 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
