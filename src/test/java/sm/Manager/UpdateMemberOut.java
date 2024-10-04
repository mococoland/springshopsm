package sm.Manager;

import edu.sm.service.CustomerService;

public class UpdateMemberOut {
    public static void main(String[] args) {
        CustomerService custService = new CustomerService();
        String newMemberOut = "활동정지";  // 변경할 상태 (예: "활동", "휴면")
        int custKey = 1;  // 업데이트할 고객의 custKey

        try {
            // revokeMembership 호출 시 매개변수 순서를 맞춰 전달
            custService.revokeMembership(custKey, newMemberOut);
            System.out.println("회원 상태가 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
