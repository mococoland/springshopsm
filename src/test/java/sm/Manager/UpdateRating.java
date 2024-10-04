package sm.Manager;

import edu.sm.service.CustomerService;

public class UpdateRating {
    public static void main(String[] args) {
        CustomerService custService = new CustomerService();
        int custKey = 1;  // 업데이트할 고객의 custKey
        String newRating = "VIP";  // 변경할 상태 (예: "활동", "휴면")

        try {
            custService.updateCustomerRank(custKey, newRating);
            System.out.println("회원 상태가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}