package sm.Manager;

import edu.sm.service.CustomerService;

public class CustStateUpdate {
    public static void main(String[] args) {
        CustomerService custService = new CustomerService();
        int custKey = 1;  // 업데이트할 고객의 custKey
        String newState = "활동";  // 변경할 상태 (예: "활동", "휴면")

        try {
            custService.updateCustomerState(custKey, newState);
            System.out.println("회원 상태가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}